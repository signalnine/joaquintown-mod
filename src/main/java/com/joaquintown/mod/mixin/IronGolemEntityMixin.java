package com.joaquintown.mod.mixin;

import com.joaquintown.mod.entity.GodzillaEntity;
import com.joaquintown.mod.entity.KingKongEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin to prevent iron golems from attacking village defender entities.
 * Godzilla, King Kong, and iron golems should be allies, all defending villages.
 */
@Mixin(IronGolemEntity.class)
public class IronGolemEntityMixin {

    /**
     * Prevent iron golems from targeting village defenders as enemies.
     */
    @Inject(method = "canTarget(Lnet/minecraft/entity/LivingEntity;)Z", at = @At("HEAD"), cancellable = true)
    private void dontTargetVillageDefenders(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof GodzillaEntity || entity instanceof KingKongEntity) {
            cir.setReturnValue(false);
        }
    }
}
