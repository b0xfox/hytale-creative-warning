package io.github.b0xfox.creativewarning.events;

import io.github.b0xfox.creativewarning.CreativeWarningPlugin;

import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;

public final class OnPlayerReadyEvent {

    public static void onEvent(PlayerReadyEvent event) {

        Player player = event.getPlayer();
        Store<EntityStore> store = player.getReference().getStore();
        PlayerRef playerRef = store.getComponent(player.getReference(), PlayerRef.getComponentType());

        if (player != null && playerRef != null) {

            World world = player.getWorld();

            if (world.isAlive()) {

                world.execute(() -> {
                    CreativeWarningPlugin.provider.rebuildHud(playerRef);
                });
            }
        }
    }
}
