package com.joaquintown.mod.client.renderer;

import com.joaquintown.mod.client.model.GodzillaModel;
import com.joaquintown.mod.entity.GodzillaEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
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

    @Override
    public void preRender(MatrixStack poseStack, GodzillaEntity animatable, BakedGeoModel model,
                          @Nullable VertexConsumerProvider bufferSource, @Nullable VertexConsumer buffer,
                          boolean isReRender, float partialTick, int packedLight, int packedOverlay, int color) {
        // Scale down baby Godzillas to 50% size
        if (animatable.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
            this.shadowRadius = 1.0f; // Smaller shadow for babies
        } else {
            this.shadowRadius = 2.0f; // Large shadow for adults
        }

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, color);
    }
}
