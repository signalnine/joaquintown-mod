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

/**
 * King Kong entity - a mighty gorilla that protects villages like an iron golem.
 * Uses the vanilla iron golem model but scaled up.
 */
public class KingKongEntity extends HostileEntity {

    public KingKongEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 40;
    }

    /**
     * Define King Kong's attributes - health, speed, attack damage, etc.
     * King Kong is a mighty titan, powerful enough to defeat a Warden.
     */
    public static DefaultAttributeContainer.Builder createKingKongAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 500.0)  // Warden-level health
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30)  // Slightly faster
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 35.0)  // Strong melee attacks
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)  // Complete knockback immunity
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40.0)
                .add(EntityAttributes.GENERIC_ARMOR, 12.0)  // Doubled armor
                .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, 6.0);  // Added toughness
    }

    /**
     * Set up AI goals - what King Kong does when idle, attacking, etc.
     * Behaves like an iron golem: defends villages from hostile mobs.
     */
    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.6));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 20.0f));
        this.goalSelector.add(5, new LookAroundGoal(this));

        // Target hostile mobs like an iron golem does
        // Don't target iron golems, Godzillas, or other King Kongs (village defenders are allies)
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
     * Override sounds to use custom King Kong roars.
     */
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.IRON_GOLEM_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.IRON_GOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.IRON_GOLEM_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 1.8f; // Louder than normal mobs
    }

    @Override
    public int getMinAmbientSoundDelay() {
        return 400; // 20 seconds minimum (default is 80 ticks / 4 seconds)
    }

    /**
     * Drop Kong's Axe with powerful enchantments when defeated.
     */
    @Override
    protected void dropLoot(DamageSource damageSource, boolean causedByPlayer) {
        super.dropLoot(damageSource, causedByPlayer);

        // Create Kong's Axe with enchantments
        ItemStack axe = new ItemStack(ModItems.KONGS_AXE);

        // Add enchantments
        ItemEnchantmentsComponent.Builder enchantments = new ItemEnchantmentsComponent.Builder(ItemEnchantmentsComponent.DEFAULT);

        // Get enchantment registry
        var enchantmentRegistry = this.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT);

        // Add powerful enchantments
        addEnchantment(enchantments, enchantmentRegistry, Enchantments.SHARPNESS, 5);
        addEnchantment(enchantments, enchantmentRegistry, Enchantments.EFFICIENCY, 5);
        addEnchantment(enchantments, enchantmentRegistry, Enchantments.UNBREAKING, 3);
        addEnchantment(enchantments, enchantmentRegistry, Enchantments.MENDING, 1);
        addEnchantment(enchantments, enchantmentRegistry, Enchantments.SWEEPING_EDGE, 3);

        // Apply enchantments to the axe
        axe.set(DataComponentTypes.ENCHANTMENTS, enchantments.build());

        // Drop the enchanted axe
        this.dropStack(axe);
    }

    private void addEnchantment(ItemEnchantmentsComponent.Builder builder,
                                 net.minecraft.registry.Registry<Enchantment> registry,
                                 RegistryKey<Enchantment> enchantmentKey,
                                 int level) {
        registry.getEntry(enchantmentKey).ifPresent(entry -> builder.add(entry, level));
    }
}
