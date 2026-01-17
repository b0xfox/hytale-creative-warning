package io.github.b0xfox.creativewarning.components;

import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;

public class CreativeWarningComponent implements Component<EntityStore> {

    @Nonnull
    public static final String COMPONENT_ID = "Boxfox:CreativeWarningHud";
    @Nonnull
    public static final BuilderCodec<CreativeWarningComponent> CODEC;
    @Nonnull
    private static ComponentType<EntityStore, CreativeWarningComponent> COMPONENT_TYPE;

    public CreativeWarningComponent() {}

    public static void setComponentType(@Nonnull ComponentType<EntityStore, CreativeWarningComponent> componentType) {
        COMPONENT_TYPE = componentType;
    }

    @Override
    public String toString() {
        return "CreativeWarningComponent{}";
    }

    @Override
    public Component<EntityStore> clone() {
        return new CreativeWarningComponent();
    }

    @Nonnull
    public static ComponentType<EntityStore, CreativeWarningComponent> getComponentType() {
        if (COMPONENT_TYPE == null) {
            throw new IllegalStateException("CreativeWarningComponent not registered yet!");
        } else {
            return COMPONENT_TYPE;
        }
    }

    static {
        CODEC = BuilderCodec
                .builder(CreativeWarningComponent.class, CreativeWarningComponent::new)
                .build();
    }
}
