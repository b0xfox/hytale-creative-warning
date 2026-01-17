package io.github.b0xfox.creativewarning.hud;

import javax.annotation.Nonnull;

import com.buuz135.mhud.MultipleHUD;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

public class MultiHudProvider extends HudProvider{

    private static final String HUD_ID = "CreativeWarning_HUD";

    public void showHud(@Nonnull PlayerRef playerRef, CreativeWarningHud hud) {
        
        Ref<EntityStore> ref = playerRef.getReference();
        Store<EntityStore> store = ref.getStore();
        Player player = store.getComponent(ref, Player.getComponentType());

        if (player != null && playerRef.isValid()) {

            MultipleHUD.getInstance().setCustomHud(player, playerRef, HUD_ID, hud);
        }
    }
}
