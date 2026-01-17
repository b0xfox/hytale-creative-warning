package io.github.b0xfox.creativewarning.hud;

import javax.annotation.Nonnull;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.hud.CustomUIHud;
import com.hypixel.hytale.server.core.entity.entities.player.hud.HudManager;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

public class VanillaHudProvider extends HudProvider{

    public void showHud(@Nonnull PlayerRef playerRef, CreativeWarningHud hud) {
        
        Ref<EntityStore> ref = playerRef.getReference();
        Store<EntityStore> store = ref.getStore();
        Player player = store.getComponent(ref, Player.getComponentType());

        if (player != null && playerRef.isValid()) {

            HudManager hudManager = player.getHudManager();

            if (hudManager == null) return;

            CustomUIHud customHud = hudManager.getCustomHud();

            if (customHud instanceof CreativeWarningHud) {
                hud.show();
            } else {
                hudManager.setCustomHud(playerRef, hud);
            }
        }

    }
}

