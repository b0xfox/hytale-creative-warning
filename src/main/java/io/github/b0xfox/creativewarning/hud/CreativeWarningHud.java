package io.github.b0xfox.creativewarning.hud;

import javax.annotation.Nonnull;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.hud.CustomUIHud;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

public class CreativeWarningHud extends CustomUIHud {

    private boolean hidden;
    private static String HUD_TEXT = "IN CREATIVE MODE";

    public CreativeWarningHud(@Nonnull PlayerRef playerRef) {
        super(playerRef);
    }

    public void updateHud(@Nonnull PlayerRef playerRef) {

        Ref<EntityStore> ref = playerRef.getReference();
        Store<EntityStore> store = ref.getStore();
        Player player = store.getComponent(ref, Player.getComponentType());

        if (player != null && playerRef.isValid()) {
            hidden = player.getGameMode() != GameMode.Creative;
        } else {
            hidden = false;
        }
    }

    @Override
    protected void build(@Nonnull UICommandBuilder builder) {
        if (!hidden) {
            builder.append("Hud/CreativeWarningHud.ui");
            builder.set("#CreativeWarningText.Text", HUD_TEXT);
        }
    }

}
