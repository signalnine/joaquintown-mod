# Joaquintown Mod - Complete Documentation

**Version:** 1.0.0
**Minecraft Version:** 1.21.1
**Mod Loader:** Fabric
**Last Updated:** October 16, 2025

---

## Table of Contents

1. [Overview](#overview)
2. [Features](#features)
3. [Technical Architecture](#technical-architecture)
4. [Entities](#entities)
5. [Items](#items)
6. [Sounds](#sounds)
7. [Village System](#village-system)
8. [File Structure](#file-structure)
9. [Building & Running](#building--running)
10. [Known Issues](#known-issues)
11. [Future Improvements](#future-improvements)
12. [Credits](#credits)

---

## Overview

Joaquintown is a comprehensive Minecraft mod that transforms villages into Joaquin-themed settlements defended by kaiju titans. The mod features custom entities (Godzilla and King Kong), a banana-based economy, custom villager appearances and sounds, and complete audio overhauls for various mobs.

### Key Highlights
- **Custom Kaiju Defenders:** Godzilla and King Kong protect villages
- **Banana Economy:** Villagers trade using bananas instead of emeralds
- **Custom Villager System:** Modified appearance (no nose) with Joaquin voice acting
- **Custom Sounds:** Authentic Godzilla and King Kong roars, plus custom sounds for villagers, zombies, iron golems, and ravagers
- **Automatic Village Spawning:** New worlds spawn with a Joaquintown village at spawn

---

## Features

### 1. **Custom Entities**

#### **Godzilla**
- **Type:** Hostile Entity (Village Defender)
- **Health:** 600 HP
- **Attack Damage:** 40
- **Armor:** 15 (with 8 armor toughness)
- **Speed:** 0.25
- **Size:** 2.0 blocks wide × 4.0 blocks tall
- **Special Traits:**
  - Complete knockback immunity
  - Custom GeckoLib 3D model with animations
  - Authentic Godzilla roar sound effects
  - Ambient roar every 20-40 seconds
  - Targets all hostile mobs except creepers
  - Allied with iron golems and King Kong
  - Persistent (won't despawn)
  - 50 XP on death

#### **King Kong**
- **Type:** Hostile Entity (Village Defender)
- **Health:** 500 HP
- **Attack Damage:** 35
- **Armor:** 12 (with 6 armor toughness)
- **Speed:** 0.30
- **Size:** 1.6 blocks wide × 3.2 blocks tall
- **Special Traits:**
  - Complete knockback immunity
  - Uses scaled iron golem model (130% size)
  - Authentic King Kong roar sound effects
  - Ambient roar every 20-40 seconds
  - Targets all hostile mobs except creepers
  - Allied with iron golems and Godzilla
  - Persistent (won't despawn)
  - 40 XP on death

**Combat Comparison:**
Both entities are designed to defeat Wardens (500 HP, 30 damage):
- Godzilla: Superior health and armor
- King Kong: Faster movement, high damage
- Together: Nearly unstoppable village defense

### 2. **Banana Economy System**

#### **Banana Item**
- **Registry ID:** `joaquintown:banana`
- **Food Value:** Restores hunger (specifics in ModFoodComponents)
- **Creative Tabs:** Food & Drink, Ingredients
- **Texture:** Custom banana texture

#### **Trading System Override**
- **Implementation:** Mixin-based (@ModifyVariable in TradeOfferMixin)
- **Behavior:** All emerald trades are replaced with bananas
- **Scope:** Affects all villager professions
- **Technical Details:**
  - Intercepts TradeOffer constructor at HEAD
  - Replaces emerald ItemStack with banana ItemStack
  - Maintains original trade quantities
  - Works for both buy and sell trades

### 3. **Villager Customization**

#### **Appearance Modifications**
- **Nose Removal:** VillagerModelMixin hides nose ModelPart
- **Custom Textures:** Profession-specific textures in `assets/joaquintown/textures/entity/villager/profession/`
- **Supported Professions:**
  - Armorer
  - Butcher
  - Cartographer
  - Cleric
  - Farmer
  - Fisherman
  - Fletcher
  - Leatherworker
  - Librarian
  - Mason
  - Shepherd
  - Toolsmith
  - Weaponsmith
  - Nitwit

#### **Sound Overrides**
- **Implementation:** VillagerSoundMixin
- **Custom Sounds:**
  - Ambient (3 variations with weighted randomization)
  - Hurt (2 variations)
  - Death
  - Trade (yes sounds)
  - No (3 variations)
  - Celebrate
  - Work (armorer)

### 4. **Zombie Sound Overrides**
- **Implementation:** ZombieSoundMixin
- **Custom Sounds:**
  - Ambient (4 variations)
  - Hurt (2 variations)
  - Death

### 5. **Iron Golem Sound Overrides**
- **Implementation:** IronGolemSoundMixin
- **Custom Sounds:** King Kong roars
  - Ambient (3 roar variations)
  - Hurt
  - Death

### 6. **Ravager Sound Overrides**
- **Implementation:** RavagerSoundMixin
- **Custom Sounds:** Godzilla roars
  - Ambient
  - Hurt
  - Death

### 7. **Village Spawning System**

#### **Spawn Village Placement**
- **Trigger:** Server start (after 2-second delay)
- **Location:** World spawn point
- **Structure:** `minecraft:village_plains`
- **Persistence:** Tracked via ServerWorld PersistentState
- **Retry Logic:** Retries every 10 seconds if placement fails

#### **Kaiju Spawning**
When village is placed successfully:
- **Godzilla:** Spawns 15 blocks north of village center
- **King Kong:** Spawns 15 blocks south of village center
- Both are marked as persistent
- Positioned at world surface (Heightmap.WORLD_SURFACE)

---

## Technical Architecture

### **Dependencies**

```gradle
minecraft: 1.21.1
fabric-loader: 0.16.2
fabric-api: 0.102.1+1.21.1
geckolib: 4.6.1 (for Godzilla animations)
```

### **Core Systems**

#### **Mixin System**
The mod uses 7 mixins to modify vanilla behavior:

1. **ExampleMixin** (`com.joaquintown.mod.mixin.ExampleMixin`)
   - Example/template mixin
   - Targets: MinecraftServer

2. **VillagerSoundMixin** (`com.joaquintown.mod.mixin.VillagerSoundMixin`)
   - Overrides villager sounds
   - Methods: getAmbientSound, getHurtSound, getDeathSound

3. **VillagerModelMixin** (`com.joaquintown.mod.mixin.VillagerModelMixin`)
   - Client-side only
   - Hides villager nose
   - Targets: VillagerEntityModel constructor

4. **ZombieSoundMixin** (`com.joaquintown.mod.mixin.ZombieSoundMixin`)
   - Overrides zombie sounds
   - Methods: getAmbientSound, getHurtSound, getDeathSound

5. **IronGolemSoundMixin** (`com.joaquintown.mod.mixin.IronGolemSoundMixin`)
   - Overrides iron golem sounds with King Kong roars
   - Methods: getHurtSound, getDeathSound

6. **RavagerSoundMixin** (`com.joaquintown.mod.mixin.RavagerSoundMixin`)
   - Overrides ravager sounds with Godzilla roars
   - Methods: getAmbientSound, getHurtSound, getDeathSound

7. **TradeOfferMixin** (`com.joaquintown.mod.mixin.TradeOfferMixin`)
   - Replaces emeralds with bananas in trades
   - Uses @ModifyVariable at constructor HEAD
   - Modifies: firstBuyItem, secondBuyItem, sellItem

**Note:** IronGolemEntityMixin exists but is disabled (not in joaquintown.mixins.json) due to targeting issues with the canTarget method.

#### **Event Handlers**

**Server Lifecycle:**
- `ServerLifecycleEvents.SERVER_STARTED`: Schedules village placement
- `ServerLifecycleEvents.SERVER_STOPPING`: Cleanup village placement tracking
- `ServerTickEvents.END_SERVER_TICK`: Handles delayed village placement logic

**Player Events:**
- `ServerPlayConnectionEvents.JOIN`: Sends welcome message

#### **GeckoLib Integration**

Godzilla uses GeckoLib 4.6.1 for animations:
- **Model:** `GodzillaModel.java` (extends GeoModel)
- **Renderer:** `GodzillaRenderer.java` (extends GeoEntityRenderer)
- **Animations:**
  - Idle: Loops continuously
  - Walk: Plays when moving
- **Assets:**
  - Model: `assets/joaquintown/geo/godzilla.geo.json` (14KB)
  - Texture: `assets/joaquintown/textures/entity/godzilla.png` (512×512)
  - Animations: `assets/joaquintown/animations/godzilla.animation.json` (6.2KB)

King Kong uses a custom wrapper model:
- **Model:** `KingKongEntityModel.java` (wraps vanilla iron golem model)
- **Renderer:** `KingKongRenderer.java` (applies 1.3× scale)
- **Texture:** `assets/joaquintown/textures/entity/king_kong.png`

---

## Entities

### **Registration**

Entities are registered in `ModEntities.java`:

```java
public static final EntityType<GodzillaEntity> GODZILLA = Registry.register(
    Registries.ENTITY_TYPE,
    Identifier.of("joaquintown", "godzilla"),
    EntityType.Builder.create(GodzillaEntity::new, SpawnGroup.MONSTER)
        .dimensions(2.0f, 4.0f)
        .maxTrackingRange(80)
        .build()
);

public static final EntityType<KingKongEntity> KING_KONG = Registry.register(
    Registries.ENTITY_TYPE,
    Identifier.of("joaquintown", "king_kong"),
    EntityType.Builder.create(KingKongEntity::new, SpawnGroup.CREATURE)
        .dimensions(1.6f, 3.2f)
        .maxTrackingRange(64)
        .build()
);
```

### **AI Goals**

Both entities share similar AI structure:

**Goal Selectors:**
1. SwimGoal (priority 1)
2. MeleeAttackGoal (priority 2)
3. WanderAroundFarGoal (priority 3)
4. LookAtEntityGoal (priority 4)
5. LookAroundGoal (priority 5)

**Target Selectors:**
1. RevengeGoal with group revenge (priority 1)
2. ActiveTargetGoal for all Monsters except:
   - CreeperEntity (avoid explosions)
   - IronGolemEntity (allies)
   - GodzillaEntity (allies)
   - KingKongEntity (allies)
3. ActiveTargetGoal for SlimeEntity (priority 3)

### **Sound Configuration**

**Godzilla:**
- Ambient: 400-800 ticks (20-40 seconds)
- Volume: 2.0× normal
- Uses `ModSounds.GIANT_AMBIENT`, `GIANT_HURT`, `GIANT_DEATH`

**King Kong:**
- Ambient: 400-800 ticks (20-40 seconds)
- Volume: 1.8× normal
- Uses `ModSounds.IRON_GOLEM_AMBIENT`, `IRON_GOLEM_HURT`, `IRON_GOLEM_DEATH`

---

## Items

### **Banana**

**Registration:**
```java
public static final Item BANANA = registerItem("banana",
    new Item(new Item.Settings().food(ModFoodComponents.BANANA)));
```

**Food Component:**
Defined in `ModFoodComponents.java` (specifics depend on implementation)

**Creative Tabs:**
- `ItemGroups.INGREDIENTS`
- `ItemGroups.FOOD_AND_DRINK`

**Model:** `assets/joaquintown/models/item/banana.json`

### **Spawn Eggs**

**Godzilla Spawn Egg:**
- Colors: 0x2d3e2d (dark gray-green), 0x1a1a1a (charcoal)
- Model: `assets/joaquintown/models/item/godzilla_spawn_egg.json`
- Creative Tab: Spawn Eggs

**King Kong Spawn Egg:**
- Colors: 0x3d2817 (dark brown), 0x6b4423 (lighter brown)
- Model: `assets/joaquintown/models/item/king_kong_spawn_egg.json`
- Creative Tab: Spawn Eggs

---

## Sounds

### **Sound Registry**

All sounds registered in `ModSounds.java`:

```java
// Villager sounds
VILLAGER_AMBIENT
VILLAGER_HURT
VILLAGER_DEATH
VILLAGER_TRADE
VILLAGER_NO
VILLAGER_CELEBRATE
VILLAGER_WORK

// Zombie sounds
ZOMBIE_AMBIENT
ZOMBIE_HURT
ZOMBIE_DEATH

// Iron Golem (King Kong) sounds
IRON_GOLEM_AMBIENT
IRON_GOLEM_HURT
IRON_GOLEM_DEATH

// Ravager (Godzilla) sounds
GIANT_AMBIENT
GIANT_HURT
GIANT_DEATH
```

### **Sound Files**

**Location:** `src/main/resources/assets/joaquintown/sounds/`

**Directory Structure:**
```
sounds/
├── entity/
│   ├── godzilla/
│   │   ├── ambient.ogg (27KB - Godzilla roar)
│   │   ├── hurt.ogg (41KB - Godzilla hurt)
│   │   └── death.ogg (27KB - Godzilla roar)
│   ├── iron_golem/
│   │   ├── roar1.ogg (34KB - King Kong)
│   │   ├── roar2.ogg (34KB - King Kong)
│   │   ├── roar3.ogg (34KB - King Kong)
│   │   └── hurt.ogg (26KB - King Kong)
│   ├── villager/
│   │   ├── ambient1.ogg
│   │   ├── ambient2.ogg
│   │   ├── ambient3.ogg
│   │   ├── hurt1.ogg
│   │   ├── hurt2.ogg
│   │   ├── death.ogg
│   │   ├── yes1.ogg, yes2.ogg, yes3.ogg
│   │   ├── no1.ogg, no2.ogg, no3.ogg
│   │   ├── celebrate.ogg
│   │   └── work.ogg
│   └── zombie/
│       ├── zombie.ogg
│       ├── zombie2.ogg
│       ├── zombie3.ogg
│       └── zombie4.ogg
```

**Sound Definition:** `assets/joaquintown/sounds.json`
- Defines sound events and their associated files
- Includes subtitle keys for accessibility
- Supports weighted randomization (villager ambient)

### **Format Details**

- **File Format:** OGG Vorbis
- **Conversion:** MP3 files converted using FFmpeg with quality 4
- **Bitrate:** Variable (optimized for game use)
- **Sample Rate:** 48000 Hz (Godzilla sounds)

---

## Village System

### **SpawnVillageState**

**Purpose:** Persistent state tracking for spawn village placement

**File Location:** `com.joaquintown.mod.world.SpawnVillageState`

**Storage:** World-specific PersistentState saved to NBT

**Key Methods:**
- `hasVillage()`: Check if village has been placed
- `markVillagePlaced()`: Mark village as placed (irreversible)
- `get(ServerWorld)`: Retrieve state for world

**NBT Structure:**
```json
{
  "joaquintown:has_village": true
}
```

### **Village Placement Logic**

**Timing:**
- Initial delay: 40 ticks (2 seconds) after server start
- Retry delay: 200 ticks (10 seconds) if placement fails

**Process:**
1. Check if village already exists (via SpawnVillageState)
2. Get world spawn position
3. Find surface position at spawn
4. Execute `/place structure minecraft:village_plains X Y Z`
5. If successful:
   - Mark village as placed in state
   - Spawn Godzilla 15 blocks north
   - Spawn King Kong 15 blocks south
6. If failed: Schedule retry

**Kaiju Positioning:**
- Uses `Heightmap.Type.WORLD_SURFACE` for Y positioning
- Centered in block (X + 0.5, Z + 0.5)
- Spawned with 0° rotation
- Immediately marked as persistent

---

## File Structure

```
mc-mod/
├── src/
│   ├── main/
│   │   ├── java/com/joaquintown/mod/
│   │   │   ├── JoaquintownMod.java (Main mod class)
│   │   │   ├── JoaquintownModClient.java (Client-side initialization)
│   │   │   ├── client/
│   │   │   │   ├── model/
│   │   │   │   │   ├── GodzillaModel.java
│   │   │   │   │   └── KingKongEntityModel.java
│   │   │   │   └── renderer/
│   │   │   │       ├── GodzillaRenderer.java
│   │   │   │       └── KingKongRenderer.java
│   │   │   ├── entity/
│   │   │   │   ├── GodzillaEntity.java
│   │   │   │   └── KingKongEntity.java
│   │   │   ├── init/
│   │   │   │   └── ModEntities.java
│   │   │   ├── item/
│   │   │   │   ├── ModItems.java
│   │   │   │   └── ModFoodComponents.java
│   │   │   ├── mixin/
│   │   │   │   ├── ExampleMixin.java
│   │   │   │   ├── IronGolemEntityMixin.java (disabled)
│   │   │   │   ├── IronGolemSoundMixin.java
│   │   │   │   ├── RavagerSoundMixin.java
│   │   │   │   ├── TradeOfferMixin.java
│   │   │   │   ├── VillagerModelMixin.java
│   │   │   │   ├── VillagerSoundMixin.java
│   │   │   │   └── ZombieSoundMixin.java
│   │   │   ├── sound/
│   │   │   │   └── ModSounds.java
│   │   │   └── world/
│   │   │       └── SpawnVillageState.java
│   │   └── resources/
│   │       ├── assets/
│   │       │   ├── joaquintown/
│   │       │   │   ├── animations/
│   │       │   │   │   └── godzilla.animation.json
│   │       │   │   ├── geo/
│   │       │   │   │   └── godzilla.geo.json
│   │       │   │   ├── lang/
│   │       │   │   │   └── en_us.json
│   │       │   │   ├── models/
│   │       │   │   │   └── item/
│   │       │   │   │       ├── banana.json
│   │       │   │   │       ├── godzilla_spawn_egg.json
│   │       │   │   │       └── king_kong_spawn_egg.json
│   │       │   │   ├── sounds/
│   │       │   │   │   └── entity/ (see Sounds section)
│   │       │   │   ├── sounds.json
│   │       │   │   └── textures/
│   │       │   │       └── entity/
│   │       │   │           ├── godzilla.png
│   │       │   │           ├── king_kong.png
│   │       │   │           └── villager/profession/ (13 textures)
│   │       │   └── minecraft/
│   │       │       └── textures/entity/iron_golem/
│   │       │           └── iron_golem.png (King Kong texture override)
│   │       ├── fabric.mod.json
│   │       ├── joaquintown.mixins.json
│   │       └── pack.mcmeta
│   └── test/
│       └── java/com/joaquintown/mod/
│           ├── JoaquintownModTest.java
│           └── world/SpawnVillageStateUnitTest.java
├── build.gradle
└── gradle.properties
```

---

## Building & Running

### **Prerequisites**

- Java 21 (OpenJDK recommended)
- Gradle 8.10 (included via wrapper)

### **Build Commands**

```bash
# Clean build
./gradlew clean build

# Run client (test in-game)
./gradlew runClient

# Run server
./gradlew runServer

# Run tests only
./gradlew test

# Generate sources (for IDE)
./gradlew genSources
```

### **Build Output**

- **Compiled JAR:** `build/libs/joaquintown-1.0.0.jar`
- **Development JAR:** `build/devlibs/joaquintown-1.0.0-dev.jar`
- **Test Results:** `build/reports/tests/test/index.html`

### **Testing**

The mod includes unit tests:

**JoaquintownModTest:**
- Logger initialization
- Mod ID verification
- Village structure ID
- Welcome message format
- Timing constants (delays)

**SpawnVillageStateUnitTest:**
- Initial state (no village)
- Mark village placed
- State persistence
- NBT serialization
- Immutability after marking

**Run Tests:**
```bash
./gradlew test
```

**View Results:**
```bash
open build/reports/tests/test/index.html
```

### **IDE Setup**

**IntelliJ IDEA:**
```bash
./gradlew genSources
# Then: File → Open → select build.gradle
```

**Eclipse:**
```bash
./gradlew eclipse
# Then: File → Import → Existing Projects
```

**VS Code:**
```bash
./gradlew genSources
# Install Java Extension Pack
# Open folder in VS Code
```

---

## Known Issues

### 1. **IronGolemEntityMixin Disabled**

**Issue:** Cannot target iron golem's `canTarget` method
**Error:** `Cannot find target method "canTarget(Lnet/minecraft/entity/LivingEntity;)Z"`
**Impact:** Vanilla iron golems may occasionally attack Godzilla/King Kong
**Workaround:** Custom entities (Godzilla/King Kong) won't attack iron golems, so conflict is one-sided
**Status:** Mixin exists but is commented out of joaquintown.mixins.json

### 2. **Mixin Warning on Build**

**Warning:**
```
Cannot find target method "canTarget(Lnet/minecraft/entity/LivingEntity;)Z"
for @Inject.method="canTarget(Lnet/minecraft/entity/LivingEntity;)Z"
in net.minecraft.entity.passive.IronGolemEntity
```

**Impact:** None (build succeeds, warning only)
**Cause:** IronGolemEntityMixin file exists but isn't registered
**Solution:** Can safely delete IronGolemEntityMixin.java or keep for future attempts

### 3. **Sound Conversion Source**

**Issue:** Original Godzilla/King Kong sounds are copyrighted
**Current State:** Using converted sounds from provided MP3/OGG files
**Legal Note:** For distribution, ensure sound files are properly licensed or use royalty-free alternatives

### 4. **GeckoLib Version Compatibility**

**Issue:** GeckoLib 4.7+ requires Loom 1.8.12+
**Current Version:** GeckoLib 4.6.1 (compatible with Loom 1.7.2)
**Impact:** Using slightly older GeckoLib version
**Future:** May need to update Loom version for GeckoLib updates

### 5. **Kaiju Model Scale**

**Issue:** King Kong uses iron golem model scaled to 1.3×
**Impact:** Some animations may look slightly off at larger scale
**Status:** Acceptable for gameplay, could be improved with custom model

---

## Future Improvements

### **Potential Enhancements**

1. **Additional Kaiju:**
   - Rodan (flying defender)
   - Mothra (peaceful but protective)
   - King Ghidorah (boss enemy)

2. **Enhanced AI:**
   - Patrolling behavior around village boundaries
   - Coordinated attacks between Godzilla and King Kong
   - Special abilities (atomic breath for Godzilla?)

3. **Village Customization:**
   - Joaquin-themed building blocks
   - Custom villager houses
   - Kaiju shrines/monuments

4. **Trading Enhancements:**
   - Banana farming mechanics
   - Banana-specific villager trades
   - Kaiju-related trade goods

5. **Compatibility:**
   - Fix iron golem targeting issue
   - Update to latest GeckoLib
   - Add mod compatibility tags

6. **Performance:**
   - Optimize GeckoLib animations
   - Reduce mixin overhead
   - Add config options for feature toggling

7. **Configuration:**
   - Config file for kaiju stats
   - Toggle village spawning
   - Customize sound volumes
   - Enable/disable specific features

8. **Localization:**
   - Additional language support
   - Proper subtitle translations
   - Accessibility improvements

---

## Credits

### **Development**
- **Primary Developer:** [Your Name/Username]
- **AI Assistant:** Claude (Anthropic)
- **Development Period:** October 2025

### **Dependencies**
- **Fabric API:** FabricMC Team
- **GeckoLib:** Gecko Developers
- **Minecraft:** Mojang Studios

### **Assets**

**Models & Textures:**
- Godzilla model: Extracted from "Extra Kaiju Update" mod (forge-1.20.1)
- Villager textures: Custom Joaquin-themed modifications
- King Kong texture: Modified iron golem texture

**Sounds:**
- Godzilla sounds: Converted from provided MP3 files
- King Kong sounds: Converted from provided OGG files
- Villager sounds: Custom recordings
- Zombie sounds: Custom recordings

**Note:** For public distribution, ensure all assets are properly licensed or replaced with original/royalty-free alternatives.

### **Tools Used**
- **IDE:** IntelliJ IDEA / VS Code
- **Build System:** Gradle 8.10
- **Version Control:** Git
- **Audio Conversion:** FFmpeg
- **Java Version:** OpenJDK 21

---

## Mod Metadata

**fabric.mod.json:**
```json
{
  "schemaVersion": 1,
  "id": "joaquintown",
  "version": "1.0.0",
  "name": "Joaquintown",
  "description": "Villages defended by Godzilla and King Kong with banana economy",
  "authors": ["Your Name"],
  "contact": {
    "sources": "https://github.com/yourusername/joaquintown-mod"
  },
  "license": "MIT",
  "environment": "*",
  "entrypoints": {
    "main": ["com.joaquintown.mod.JoaquintownMod"],
    "client": ["com.joaquintown.mod.JoaquintownModClient"]
  },
  "mixins": ["joaquintown.mixins.json"],
  "depends": {
    "fabricloader": ">=0.16.2",
    "minecraft": "~1.21.1",
    "java": ">=21",
    "fabric-api": "*",
    "geckolib": ">=4.6"
  }
}
```

---

## Version History

### **1.0.0** (October 16, 2025)
- Initial release
- Godzilla entity with GeckoLib animations
- King Kong entity with scaled iron golem model
- Banana economy system
- Custom villager appearance (no nose)
- Custom sounds for villagers, zombies, iron golems, ravagers
- Automatic village spawning with kaiju defenders
- Complete knockback immunity for both kaiju
- Buffed stats to defeat Wardens
- Reduced ambient roar frequency (20-40 seconds)

---

## License

[Specify your license here - MIT, GPL, All Rights Reserved, etc.]

**Important:** Ensure all third-party assets (models, sounds, textures) are properly licensed before public distribution.

---

## Support & Contact

**Issues:** [GitHub Issues URL or contact method]
**Discussion:** [Discord/Forum/etc.]
**Updates:** [Where to find updates]

---

*Documentation last updated: October 16, 2025*
