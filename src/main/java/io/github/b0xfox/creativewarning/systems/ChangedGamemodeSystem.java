package io.github.b0xfox.creativewarning.systems;

import io.github.b0xfox.creativewarning.CreativeWarningPlugin;

import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.Archetype;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Holder;
import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.entity.EntityUtils;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.component.system.EntityEventSystem;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.event.events.ecs.ChangeGameModeEvent;

import javax.annotation.Nonnull;

public class ChangedGamemodeSystem extends EntityEventSystem<EntityStore, ChangeGameModeEvent> {

    public ChangedGamemodeSystem() {
        super(ChangeGameModeEvent.class);
    }

    @Override
    public void handle(int index, @Nonnull ArchetypeChunk<EntityStore> archetypeChunk, @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer, @Nonnull ChangeGameModeEvent event) {

        Holder<EntityStore> holder = EntityUtils.toHolder(index, archetypeChunk);
        Player player = holder.getComponent(Player.getComponentType());
        PlayerRef playerRef = holder.getComponent(PlayerRef.getComponentType());

        if (player != null && playerRef != null) {

            World world = player.getWorld();

            if (world.isAlive()) {

                world.execute(() -> {
                    CreativeWarningPlugin.provider.rebuildHud(playerRef);
                });
            }
        }
    }

    @Override
    public Query<EntityStore> getQuery() {
        return Archetype.empty();
    }
}
