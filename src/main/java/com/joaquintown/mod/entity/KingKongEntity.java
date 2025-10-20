package com.joaquintown.mod.entity;

import com.joaquintown.mod.init.ModEntities;
import com.joaquintown.mod.item.ModItems;
import com.joaquintown.mod.sound.ModSounds;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * King Kong entity - a mighty gorilla that protects villages like an iron golem.
 * Uses the vanilla iron golem model but scaled up.
 */
public class KingKongEntity extends HostileEntity {

    private static final TrackedData<Boolean> BABY = DataTracker.registerData(KingKongEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final Ingredient BREEDING_INGREDIENT = Ingredient.ofItems(ModItems.BANANA);

    private int loveTicks;
    private int breedingAge;

    public KingKongEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 40;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(BABY, false);
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
    public float getSoundPitch() {
        // Baby King Kongs have higher pitched sounds (1.5x higher)
        return this.isBaby() ? super.getSoundPitch() * 1.5f : super.getSoundPitch();
    }

    @Override
    public int getMinAmbientSoundDelay() {
        return 400; // 20 seconds minimum (default is 80 ticks / 4 seconds)
    }

    /**
     * Check if the given item can be used to breed King Kong.
     */
    public boolean isBreedingItem(ItemStack stack) {
        return BREEDING_INGREDIENT.test(stack);
    }

    /**
     * Handle player interaction - feeding bananas to breed.
     */
    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (this.isBreedingItem(itemStack)) {
            // Can't breed babies or if already in love mode
            if (this.isBaby() || this.isInLove()) {
                return ActionResult.PASS;
            }

            // Consume item in survival mode
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }

            // Enter love mode
            this.lovePlayer(player);

            // Spawn heart particles
            if (!this.getWorld().isClient) {
                for (int i = 0; i < 7; i++) {
                    double d = this.random.nextGaussian() * 0.02;
                    double e = this.random.nextGaussian() * 0.02;
                    double f = this.random.nextGaussian() * 0.02;
                    ((ServerWorld)this.getWorld()).spawnParticles(
                        ParticleTypes.HEART,
                        this.getParticleX(1.0),
                        this.getRandomBodyY() + 0.5,
                        this.getParticleZ(1.0),
                        1, d, e, f, 0.0
                    );
                }
            }

            return ActionResult.success(this.getWorld().isClient);
        }

        return super.interactMob(player, hand);
    }

    /**
     * Set King Kong into love mode (ready to breed).
     */
    public void lovePlayer(@Nullable PlayerEntity player) {
        this.loveTicks = 600; // 30 seconds
    }

    /**
     * Check if King Kong is in love mode.
     */
    public boolean isInLove() {
        return this.loveTicks > 0;
    }

    /**
     * Tick method - handles breeding logic.
     */
    @Override
    public void tick() {
        super.tick();

        if (this.isInLove()) {
            this.loveTicks--;

            // Spawn heart particles while in love
            if (this.loveTicks % 10 == 0 && !this.getWorld().isClient) {
                double d = this.random.nextGaussian() * 0.02;
                double e = this.random.nextGaussian() * 0.02;
                double f = this.random.nextGaussian() * 0.02;
                ((ServerWorld)this.getWorld()).spawnParticles(
                    ParticleTypes.HEART,
                    this.getParticleX(1.0),
                    this.getRandomBodyY() + 0.5,
                    this.getParticleZ(1.0),
                    1, d, e, f, 0.0
                );
            }

            // Try to breed with nearby King Kong in love mode
            if (!this.getWorld().isClient && this.loveTicks % 20 == 0) {
                this.tryToBreed();
            }
        }
    }

    /**
     * Try to find another King Kong in love mode and breed.
     */
    private void tryToBreed() {
        if (!(this.getWorld() instanceof ServerWorld serverWorld)) {
            return;
        }

        // Find nearby King Kongs in love mode
        var nearbyKongs = serverWorld.getEntitiesByClass(
            KingKongEntity.class,
            this.getBoundingBox().expand(8.0),
            other -> other != this && other.isInLove() && !other.isBaby()
        );

        if (!nearbyKongs.isEmpty()) {
            KingKongEntity mate = nearbyKongs.get(0);

            // Create baby King Kong
            this.breed(serverWorld, mate);

            // Reset love mode for both parents
            this.loveTicks = 0;
            mate.loveTicks = 0;

            // Set breeding cooldown (5 minutes)
            this.breedingAge = 6000;
            mate.breedingAge = 6000;
        }
    }

    /**
     * Breed with another King Kong and create a baby.
     */
    private void breed(ServerWorld world, KingKongEntity mate) {
        KingKongEntity baby = ModEntities.KING_KONG.create(world);
        if (baby != null) {
            baby.setBaby(true);
            baby.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), 0.0f, 0.0f);
            world.spawnEntity(baby);

            // Spawn lots of hearts for successful breeding
            for (int i = 0; i < 20; i++) {
                double d = this.random.nextGaussian() * 0.02;
                double e = this.random.nextGaussian() * 0.02;
                double f = this.random.nextGaussian() * 0.02;
                world.spawnParticles(
                    ParticleTypes.HEART,
                    this.getParticleX(1.0),
                    this.getRandomBodyY() + 0.5,
                    this.getParticleZ(1.0),
                    1, d, e, f, 0.0
                );
            }
        }
    }

    /**
     * Check if this King Kong is a baby.
     */
    public boolean isBaby() {
        return this.dataTracker.get(BABY);
    }

    /**
     * Set whether this King Kong is a baby.
     */
    public void setBaby(boolean baby) {
        this.dataTracker.set(BABY, baby);
        this.calculateDimensions();
    }

    @Override
    public void onTrackedDataSet(TrackedData<?> data) {
        if (BABY.equals(data)) {
            this.calculateDimensions();
        }
        super.onTrackedDataSet(data);
    }

    @Override
    public void calculateDimensions() {
        double d = this.getX();
        double e = this.getY();
        double f = this.getZ();
        super.calculateDimensions();
        this.setPosition(d, e, f);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("IsBaby", this.isBaby());
        nbt.putInt("LoveTicks", this.loveTicks);
        nbt.putInt("BreedingAge", this.breedingAge);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setBaby(nbt.getBoolean("IsBaby"));
        this.loveTicks = nbt.getInt("LoveTicks");
        this.breedingAge = nbt.getInt("BreedingAge");
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
