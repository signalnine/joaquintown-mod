package com.joaquintown.mod.mixin;

import com.joaquintown.mod.sound.ModSounds;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin to override iron golem sounds with custom King Kong sounds.
 *
 * Note: Iron golems don't have ambient sounds in vanilla, only hurt/death/step sounds.
 */
@Mixin(IronGolemEntity.class)
public abstract class IronGolemSoundMixin {

    /**
     * Override the hurt sound - King Kong roars when hurt!
     */
    @Inject(method = "getHurtSound", at = @At("HEAD"), cancellable = true)
    private void getCustomHurtSound(DamageSource source, CallbackInfoReturnable<SoundEvent> cir) {
        cir.setReturnValue(ModSounds.IRON_GOLEM_HURT);
    }

    /**
     * Override the death sound - Final King Kong roar!
     */
    @Inject(method = "getDeathSound", at = @At("HEAD"), cancellable = true)
    private void getCustomDeathSound(CallbackInfoReturnable<SoundEvent> cir) {
        cir.setReturnValue(ModSounds.IRON_GOLEM_DEATH);
    }
}
