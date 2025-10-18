package com.joaquintown.mod.mixin;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.VillagerResemblingModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin to remove/flatten the villager nose from the 3D model.
 *
 * This targets the VillagerResemblingModel class and modifies the nose ModelPart
 * to make it invisible or flat.
 */
@Mixin(VillagerResemblingModel.class)
public abstract class VillagerModelMixin {

    @Shadow
    protected ModelPart nose;

    /**
     * Inject into the constructor to modify the nose after the model is built.
     * We make the nose invisible by setting its visibility to false.
     */
    @Inject(method = "<init>", at = @At("RETURN"))
    private void removeNose(CallbackInfo ci) {
        // Make the nose invisible
        if (this.nose != null) {
            this.nose.visible = false;
        }
    }
}
