package io.github.b0xfox.creativewarning.hud;

import java.util.HashMap;
import java.util.Map;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

public abstract class HudProvider {

    private final Map<PlayerRef, CreativeWarningHud> huds = new HashMap<>();

    public final void rebuildHud(PlayerRef playerRef) {

        Ref<EntityStore> ref = playerRef.getReference();
        Store<EntityStore> store = ref.getStore();
        Player player = store.getComponent(ref, Player.getComponentType());

        if (player != null && playerRef.isValid()) {
        
            CreativeWarningHud hud;

            if (!this.huds.containsKey(playerRef)) {
                hud = new CreativeWarningHud(playerRef);
                this.huds.put(playerRef, hud);
                hud.updateHud(playerRef);
            } else {
                hud = this.huds.get(playerRef);
                hud.updateHud(playerRef);
            }

            showHud(playerRef, hud);
        }
    }

    abstract void showHud(PlayerRef playerRef, CreativeWarningHud hud);
}
