package io.github.b0xfox.creativewarning;

import io.github.b0xfox.creativewarning.events.OnPlayerReadyEvent;
import io.github.b0xfox.creativewarning.hud.HudProvider;
import io.github.b0xfox.creativewarning.hud.MultiHudProvider;
import io.github.b0xfox.creativewarning.hud.VanillaHudProvider;
import io.github.b0xfox.creativewarning.systems.ChangedGamemodeSystem;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.plugin.PluginBase;
import com.hypixel.hytale.server.core.plugin.PluginManager;
import com.hypixel.hytale.common.plugin.PluginIdentifier;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;

import javax.annotation.Nonnull;

public class CreativeWarningPlugin extends JavaPlugin {

    private static final PluginIdentifier MULTIHUD_PLUGIN_IDENTIFIER = PluginIdentifier.fromString("Buuz135:MultipleHUD");
    public static HudProvider provider = new VanillaHudProvider();

    public CreativeWarningPlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    protected void setup() {

        PluginBase plugin = PluginManager.get().getPlugin(MULTIHUD_PLUGIN_IDENTIFIER);

        if (plugin != null) {
            provider = new MultiHudProvider();
        }

        this.registerCommands();
        this.registerListeners();
        this.registerSystems();
    }

    private void registerCommands() {
        // this.getCommandRegistry().registerCommand(new TalentCommand());
    }

    private void registerListeners() {
        this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, OnPlayerReadyEvent::onEvent);
    }

    private void registerSystems() {
        this.getEntityStoreRegistry().registerSystem(new ChangedGamemodeSystem());
    }
}
