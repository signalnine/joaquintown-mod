package com.joaquintown.mod.client.model;

import com.joaquintown.mod.entity.GodzillaEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

/**
 * Model loader for Godzilla entity.
 * Tells GeckoLib where to find the 3D model, texture, and animation files.
 */
public class GodzillaModel extends GeoModel<GodzillaEntity> {

    @Override
    public Identifier getModelResource(GodzillaEntity entity) {
        return Identifier.of("joaquintown", "geo/godzilla.geo.json");
    }

    @Override
    public Identifier getTextureResource(GodzillaEntity entity) {
        return Identifier.of("joaquintown", "textures/entity/godzilla.png");
    }

    @Override
    public Identifier getAnimationResource(GodzillaEntity entity) {
        return Identifier.of("joaquintown", "animations/godzilla.animation.json");
    }
}
