package com.joaquintown.mod.mixin;

import com.joaquintown.mod.sound.ModSounds;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin to override villager sounds with custom Joaquintown sounds.
 *
 * Note: This mixin only overrides the basic sounds (ambient, hurt, death).
 * Additional villager sounds like trading and celebration sounds are played
 * through different systems and may require additional mixins or event handlers.
 */
@Mixin(VillagerEntity.class)
public abstract class VillagerSoundMixin {

    /**
     * Override the ambient (idle) sound.
     */
    @Inject(method = "getAmbientSound", at = @At("HEAD"), cancellable = true)
    private void getCustomAmbientSound(CallbackInfoReturnable<SoundEvent> cir) {
        cir.setReturnValue(ModSounds.VILLAGER_AMBIENT);
    }

    /**
     * Override the hurt sound.
     */
    @Inject(method = "getHurtSound", at = @At("HEAD"), cancellable = true)
    private void getCustomHurtSound(DamageSource source, CallbackInfoReturnable<SoundEvent> cir) {
        cir.setReturnValue(ModSounds.VILLAGER_HURT);
    }

    /**
     * Override the death sound.
     */
    @Inject(method = "getDeathSound", at = @At("HEAD"), cancellable = true)
    private void getCustomDeathSound(CallbackInfoReturnable<SoundEvent> cir) {
        cir.setReturnValue(ModSounds.VILLAGER_DEATH);
    }
}
