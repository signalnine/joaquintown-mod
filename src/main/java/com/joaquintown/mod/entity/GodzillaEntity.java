package com.joaquintown.mod.entity;

import com.joaquintown.mod.item.ModItems;
import com.joaquintown.mod.sound.ModSounds;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

/**
 * Godzilla entity - a massive kaiju that protects villages like an iron golem.
 * Uses GeckoLib for custom 3D model and animations.
 */
public class GodzillaEntity extends HostileEntity implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public GodzillaEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 50;
    }

    /**
     * Define Godzilla's attributes - health, speed, attack damage, etc.
     * Godzilla is a titan-level kaiju, powerful enough to defeat a Warden.
     */
    public static DefaultAttributeContainer.Builder createGodzillaAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 600.0)  // 3x original, more than Warden
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 40.0)  // Stronger than Warden's 30
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)  // Complete knockback immunity
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 48.0)
                .add(EntityAttributes.GENERIC_ARMOR, 15.0)  // Nearly doubled armor
                .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, 8.0);  // Added toughness
    }

    /**
     * Set up AI goals - what Godzilla does when idle, attacking, etc.
     * Behaves like an iron golem: defends villages from hostile mobs.
     */
    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.6));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 24.0f));
        this.goalSelector.add(5, new LookAroundGoal(this));

        // Target hostile mobs like an iron golem does
        // Don't target iron golems, King Kong, or other Godzillas (village defenders should be allies)
        // Aggressively target Wardens as a priority threat
        this.targetSelector.add(1, new RevengeGoal(this).setGroupRevenge());
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, MobEntity.class, 10, true, false,
            entity -> entity instanceof Monster
                && !(entity instanceof CreeperEntity)
                && !(entity instanceof IronGolemEntity)
                && !(entity instanceof GodzillaEntity)
                && !(entity instanceof KingKongEntity)));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, SlimeEntity.class, true));
    }

    /**
     * Override sounds to use custom Godzilla roars.
     */
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.GIANT_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.GIANT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.GIANT_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 2.0f; // Louder than normal mobs
    }

    @Override
    public int getMinAmbientSoundDelay() {
        return 400; // 20 seconds minimum (default is 80 ticks / 4 seconds)
    }

    /**
     * GeckoLib animation controller.
     * Defines which animations to play when.
     */
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<GodzillaEntity> state) {
        if (state.isMoving()) {
            state.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
        } else {
            state.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    /**
     * Drop Godzilla's Blade with ultimate enchantments when defeated.
     * This is an ultra-rare legendary weapon befitting the King of Monsters.
     */
    @Override
    protected void dropLoot(DamageSource damageSource, boolean causedByPlayer) {
        super.dropLoot(damageSource, causedByPlayer);

        // Create Godzilla's Blade with enchantments
        ItemStack sword = new ItemStack(ModItems.GODZILLAS_BLADE);

        // Add enchantments
        ItemEnchantmentsComponent.Builder enchantments = new ItemEnchantmentsComponent.Builder(ItemEnchantmentsComponent.DEFAULT);

        // Get enchantment registry
        var enchantmentRegistry = this.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT);

        // Add ultimate enchantments
        addEnchantment(enchantments, enchantmentRegistry, Enchantments.SHARPNESS, 5);
        addEnchantment(enchantments, enchantmentRegistry, Enchantments.FIRE_ASPECT, 2);
        addEnchantment(enchantments, enchantmentRegistry, Enchantments.LOOTING, 3);
        addEnchantment(enchantments, enchantmentRegistry, Enchantments.UNBREAKING, 3);
        addEnchantment(enchantments, enchantmentRegistry, Enchantments.MENDING, 1);
        addEnchantment(enchantments, enchantmentRegistry, Enchantments.SWEEPING_EDGE, 3);

        // Apply enchantments to the sword
        sword.set(DataComponentTypes.ENCHANTMENTS, enchantments.build());

        // Drop the enchanted sword
        this.dropStack(sword);
    }

    private void addEnchantment(ItemEnchantmentsComponent.Builder builder,
                                 net.minecraft.registry.Registry<Enchantment> registry,
                                 RegistryKey<Enchantment> enchantmentKey,
                                 int level) {
        registry.getEntry(enchantmentKey).ifPresent(entry -> builder.add(entry, level));
    }
}
