package com.joaquintown.mod.mixin;

import com.joaquintown.mod.sound.ModSounds;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin to override zombie sounds with custom Joaquintown zombie sounds.
 */
@Mixin(ZombieEntity.class)
public abstract class ZombieSoundMixin {

    /**
     * Override the ambient (idle) sound.
     */
    @Inject(method = "getAmbientSound", at = @At("HEAD"), cancellable = true)
    private void getCustomAmbientSound(CallbackInfoReturnable<SoundEvent> cir) {
        cir.setReturnValue(ModSounds.ZOMBIE_AMBIENT);
    }

    /**
     * Override the hurt sound.
     */
    @Inject(method = "getHurtSound", at = @At("HEAD"), cancellable = true)
    private void getCustomHurtSound(DamageSource source, CallbackInfoReturnable<SoundEvent> cir) {
        cir.setReturnValue(ModSounds.ZOMBIE_HURT);
    }

    /**
     * Override the death sound.
     */
    @Inject(method = "getDeathSound", at = @At("HEAD"), cancellable = true)
    private void getCustomDeathSound(CallbackInfoReturnable<SoundEvent> cir) {
        cir.setReturnValue(ModSounds.ZOMBIE_DEATH);
    }
}
