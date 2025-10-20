package com.joaquintown.mod.mixin;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin to make wolves have corgi-style short legs.
 *
 * This targets the WolfEntityModel class and scales down the leg parts
 * to create a corgi appearance with shorter, stubbier legs.
 */
@Mixin(WolfEntityModel.class)
public abstract class WolfModelMixin {

    @Shadow
    private ModelPart rightHindLeg;

    @Shadow
    private ModelPart leftHindLeg;

    @Shadow
    private ModelPart rightFrontLeg;

    @Shadow
    private ModelPart leftFrontLeg;

    /**
     * Inject into the constructor to modify the leg scale after the model is built.
     * We scale the legs to 60% of their original height to create the corgi effect.
     */
    @Inject(method = "<init>", at = @At("RETURN"))
    private void makeCorgiLegs(CallbackInfo ci) {
        // Scale down the legs to 60% height for that cute corgi look
        float legScale = 0.6f;

        if (this.rightHindLeg != null) {
            scaleModelPart(this.rightHindLeg, 1.0f, legScale, 1.0f);
        }

        if (this.leftHindLeg != null) {
            scaleModelPart(this.leftHindLeg, 1.0f, legScale, 1.0f);
        }

        if (this.rightFrontLeg != null) {
            scaleModelPart(this.rightFrontLeg, 1.0f, legScale, 1.0f);
        }

        if (this.leftFrontLeg != null) {
            scaleModelPart(this.leftFrontLeg, 1.0f, legScale, 1.0f);
        }
    }

    /**
     * Helper method to scale a ModelPart.
     */
    private void scaleModelPart(ModelPart part, float xScale, float yScale, float zScale) {
        part.xScale = xScale;
        part.yScale = yScale;
        part.zScale = zScale;
    }
}
