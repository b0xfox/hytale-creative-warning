package io.github.b0xfox.creativewarning;

import io.github.b0xfox.creativewarning.commands.ToggleHudCommand;
import io.github.b0xfox.creativewarning.components.CreativeWarningComponent;
import io.github.b0xfox.creativewarning.events.OnPlayerReadyEvent;
import io.github.b0xfox.creativewarning.hud.HudProvider;
import io.github.b0xfox.creativewarning.hud.MultiHudProvider;
import io.github.b0xfox.creativewarning.hud.VanillaHudProvider;
import io.github.b0xfox.creativewarning.systems.ChangedGamemodeSystem;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.plugin.PluginBase;
import com.hypixel.hytale.server.core.plugin.PluginManager;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.common.plugin.PluginIdentifier;
import com.hypixel.hytale.component.ComponentRegistry;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;

import javax.annotation.Nonnull;

public class CreativeWarningPlugin extends JavaPlugin {

    public static HudProvider provider = new VanillaHudProvider();
    public PluginIdentifier PLUGIN_IDENTIFIER;
    private static final PluginIdentifier MULTIHUD_PLUGIN_IDENTIFIER = PluginIdentifier.fromString("Buuz135:MultipleHUD");

    public CreativeWarningPlugin(@Nonnull JavaPluginInit init) {
        super(init);
        this.PLUGIN_IDENTIFIER = this.getIdentifier();
    }

    protected void setup() {

        PluginBase plugin = PluginManager.get().getPlugin(MULTIHUD_PLUGIN_IDENTIFIER);

        if (plugin != null) {
            provider = new MultiHudProvider();
        }

        this.registerSystems();
        this.registerCommands();
        this.registerListeners();
        this.registerComponents();
    }

    private void registerComponents() {
        ComponentRegistry<EntityStore> registry = EntityStore.REGISTRY;
        ComponentType<EntityStore, CreativeWarningComponent> hudConfigComponent = registry.registerComponent(CreativeWarningComponent.class, CreativeWarningComponent.COMPONENT_ID, CreativeWarningComponent.CODEC);
        CreativeWarningComponent.setComponentType(hudConfigComponent);
    }

    private void registerCommands() {
        this.getCommandRegistry().registerCommand(new ToggleHudCommand());
    }

    private void registerListeners() {
        this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, OnPlayerReadyEvent::onEvent);
    }

    private void registerSystems() {
        this.getEntityStoreRegistry().registerSystem(new ChangedGamemodeSystem());
    }
}
