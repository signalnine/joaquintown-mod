package com.joaquintown.mod.client.renderer;

import com.joaquintown.mod.client.model.GodzillaModel;
import com.joaquintown.mod.entity.GodzillaEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

/**
 * Renderer for Godzilla entity.
 * Handles rendering the 3D model in-game using GeckoLib.
 */
public class GodzillaRenderer extends GeoEntityRenderer<GodzillaEntity> {

    public GodzillaRenderer(EntityRendererFactory.Context context) {
        super(context, new GodzillaModel());
        this.shadowRadius = 2.0f; // Large shadow for massive entity
    }
}
