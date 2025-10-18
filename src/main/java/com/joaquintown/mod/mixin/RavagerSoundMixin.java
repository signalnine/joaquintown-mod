package com.joaquintown.mod.mixin;

import com.joaquintown.mod.sound.ModSounds;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin to override ravager sounds with Godzilla sounds.
 * This transforms the ravager into Godzilla with custom roars.
 */
@Mixin(RavagerEntity.class)
public abstract class RavagerSoundMixin {

    /**
     * Override the ambient (idle) sound with Godzilla roar.
     */
    @Inject(method = "getAmbientSound", at = @At("HEAD"), cancellable = true)
    private void getCustomAmbientSound(CallbackInfoReturnable<SoundEvent> cir) {
        cir.setReturnValue(ModSounds.GIANT_AMBIENT);
    }

    /**
     * Override the hurt sound with Godzilla hurt sound.
     */
    @Inject(method = "getHurtSound", at = @At("HEAD"), cancellable = true)
    private void getCustomHurtSound(DamageSource source, CallbackInfoReturnable<SoundEvent> cir) {
        cir.setReturnValue(ModSounds.GIANT_HURT);
    }

    /**
     * Override the death sound with Godzilla death sound.
     */
    @Inject(method = "getDeathSound", at = @At("HEAD"), cancellable = true)
    private void getCustomDeathSound(CallbackInfoReturnable<SoundEvent> cir) {
        cir.setReturnValue(ModSounds.GIANT_DEATH);
    }
}
