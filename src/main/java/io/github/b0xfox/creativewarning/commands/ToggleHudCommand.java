package io.github.b0xfox.creativewarning.commands;

import io.github.b0xfox.creativewarning.CreativeWarningPlugin;
import io.github.b0xfox.creativewarning.components.CreativeWarningComponent;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.command.system.CommandSender;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractAsyncCommand;

import java.awt.Color;
import java.util.concurrent.CompletableFuture;

import javax.annotation.Nonnull;

public class ToggleHudCommand extends AbstractAsyncCommand {

    private static final String LOG_PREFIX = "[Creative Warning] ";
    private static final Message MESSAGE_PREFIX = Message.raw("[Creative Warning] ").color(Color.YELLOW);

    public ToggleHudCommand() {

        super("creativewarning", "Enable or disables the Creative Warning HUD");
        addAliases("cwarn");

        setPermissionGroup(GameMode.Creative);
    }

    @Nonnull
    protected CompletableFuture<Void> executeAsync(CommandContext ctx) {

        CommandSender sender = ctx.sender();

        if (sender instanceof Player player) {

            World world = player.getWorld();

            if (world == null) {

                HytaleLogger.getLogger().atWarning().log(LOG_PREFIX + "ERROR: World was null");

            } else {

                world.execute(() -> {

                    Store<EntityStore> store = world.getEntityStore().getStore();
                    Ref<EntityStore> storeRef = player.getReference();
                    PlayerRef playerRef = store.getComponent(storeRef, PlayerRef.getComponentType());

                    CreativeWarningComponent component = store.getComponent(storeRef, CreativeWarningComponent.getComponentType());

                    if (component == null) {

                        sender.sendMessage(Message.join(MESSAGE_PREFIX, Message.raw("Disabled Warning HUD").color(Color.RED)));
                        store.addComponent(storeRef, CreativeWarningComponent.getComponentType());

                    } else {

                        sender.sendMessage(Message.join(MESSAGE_PREFIX, Message.raw("Enabled Warning HUD").color(Color.GREEN)));
                        store.removeComponent(storeRef, CreativeWarningComponent.getComponentType());
                    }

                    world.execute(() -> {
                        CreativeWarningPlugin.provider.rebuildHud(playerRef);
                    });

                });
            }

        } else {

            sender.sendMessage(Message.raw("Only players can use this command").color(Color.RED));
        }

        return CompletableFuture.completedFuture(null);
    }

}
