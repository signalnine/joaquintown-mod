# Joaquintown Mod - Design Document & Development Plan

## Project Overview

**Mod Name:** Joaquintown  
**Platform:** Fabric Mod Loader  
**Minecraft Version:** 1.21.1  
**Target Audience:** 10-year-old player (family-friendly content)  
**Core Concept:** A custom village experience featuring villagers styled after Joaquin, with King Kong and Godzilla as friendly protectors

---

## 1. Technical Specifications

### 1.1 Development Environment
- **Java Version:** 21 (required for Minecraft 1.21.1)
- **Mod Loader:** Fabric 0.16.5+
- **Fabric API:** Latest compatible version
- **Development Tools:**
  - IntelliJ IDEA or Eclipse
  - Gradle 8.x
  - Fabric Loom (Gradle plugin)

### 1.2 Project Structure
```
joaquintown-mod/
├── src/main/
│   ├── java/com/joaquintown/
│   │   ├── JoaquintownMod.java (Main mod class)
│   │   ├── entity/
│   │   │   ├── custom/
│   │   │   │   ├── KingKongEntity.java
│   │   │   │   └── GodzillaEntity.java
│   │   │   └── villager/JoaquinVillagerRenderer.java
│   │   ├── init/
│   │   │   ├── ModEntities.java
│   │   │   └── ModVillagers.java
│   │   └── util/
│   └── resources/
│       ├── assets/joaquintown/
│       │   ├── textures/
│       │   │   ├── entity/
│       │   │   │   ├── villager/joaquin_villager.png
│       │   │   │   ├── king_kong.png
│       │   │   │   └── godzilla.png
│       │   └── models/
│       └── fabric.mod.json
├── build.gradle
└── gradle.properties
```

---

## 2. Feature Specifications

### 2.1 Custom Villagers - "Joaquin Villagers"

#### Visual Design
- **Base Model:** Standard Minecraft villager model
- **Custom Texture Features:**
  - Curly dark brown/black hair texture overlay
  - Accessories rendered on face layer
  - Maintain profession-based clothing variations
  - Optional: Slight skin tone adjustments

#### Behavior
- Retain standard villager AI and trading mechanics
- Same professions (Farmer, Librarian, etc.)
- Compatible with existing village structures
- Can breed and produce more Joaquin villagers

#### Implementation Approach
**Option A - Texture Override (Recommended for simplicity):**
- Replace default villager textures via resource pack integration
- Simpler to maintain, works with existing villager systems

**Option B - Custom Entity:**
- Create new villager entity type
- Full control over appearance and behavior
- More complex, requires custom spawn logic

### 2.2 King Kong - Friendly Guardian

#### Design Specifications
- **Size:** 4-5 blocks tall (larger than player, smaller than Iron Golem)
- **Model:** Custom bipedal ape model
- **Texture:** Dark brown/black fur, distinctive face
- **Health:** 200 HP (higher than Iron Golem's 100 HP)
- **Damage:** 15-20 HP per hit
- **Speed:** Moderate movement speed

#### Behavior
- **Spawn Conditions:**
  - Spawns in Joaquintown villages (custom structure)
  - 1-2 per village
  - Can be summoned with special item (banana totem?)
  
- **AI Behaviors:**
  - Patrols village perimeter
  - Attacks hostile mobs within 32 blocks of village
  - Protects villagers (prioritizes threats near villagers)
  - Neutral to players unless attacked
  - Can be fed bananas to heal
  - Chest-beating animation when spotting enemies

- **Special Abilities:**
  - Ground pound attack (AoE damage + knockback)
  - Can climb blocks (like spiders)
  - Strong knockback on melee hits

### 2.3 Godzilla - Friendly Guardian

#### Design Specifications
- **Size:** 6-8 blocks tall (imposing presence)
- **Model:** Custom bipedal reptilian model with dorsal plates
- **Texture:** Dark scales, glowing blue dorsal plates
- **Health:** 300 HP
- **Damage:** 20-25 HP per hit
- **Speed:** Slow movement speed

#### Behavior
- **Spawn Conditions:**
  - Spawns in Joaquintown villages near water
  - 1 per village (rare)
  - Can be summoned with special item (nuclear core?)

- **AI Behaviors:**
  - Guards village from distance
  - Attacks hostile mobs within 48 blocks
  - Prefers to stay near water sources
  - Neutral to players unless attacked
  - Can be fed raw fish to heal
  - Roar animation when detecting threats

- **Special Abilities:**
  - Atomic breath attack (blue particle beam, ranged damage)
  - 10-second cooldown on breath attack
  - Tail swipe (melee AoE behind)
  - Immune to fire and lava damage

### 2.4 Joaquintown Village Structure

#### Custom Village Generation
- **Biome:** Plains, Savanna (suitable for both entities)
- **Unique Features:**
  - Special shrine/statue in village center
  - Kong's climbing structures (wooden towers)
  - Godzilla's pool/pond
  - Custom village buildings with themed decorations

---

## 3. Development Plan

### Phase 1: Project Setup & Core Infrastructure (Week 1)
**Tasks:**
1. Initialize Fabric mod project
   - Set up Gradle build configuration
   - Configure fabric.mod.json with mod metadata
   - Test basic mod loading in Minecraft

2. Create base package structure
   - Set up proper Java package hierarchy
   - Create initialization classes
   - Implement basic logging

3. Set up development workspace
   - Configure IDE with Minecraft source
   - Test hot-reloading capabilities

**Deliverables:**
- Functional mod skeleton that loads in Minecraft
- Basic console logging working

---

### Phase 2: Custom Villager Implementation (Week 2)

**Option A Implementation (Recommended):**

**Tasks:**
1. Create custom villager textures
   - Design curly hair overlay for male/female variants
   - Design accessories overlay
   - Create textures for all professions

2. Implement texture replacement system
   - Use Fabric's resource loader API
   - Replace default villager textures programmatically

3. Testing
   - Verify textures appear correctly
   - Test with all villager professions
   - Verify breeding produces Joaquin villagers

**Option B Implementation (If more customization needed):**

**Tasks:**
1. Create custom villager entity class
2. Implement custom renderer
3. Register entity type
4. Create spawn egg and spawn logic
5. Implement trading GUI compatibility

**Deliverables:**
- Joaquin-styled villagers appearing in game
- Compatible with existing village mechanics

---

### Phase 3: King Kong Entity (Week 3)

**Tasks:**
1. Model Creation
   - Design 3D model in Blockbench
   - Create texture file
   - Export to JSON format
   - Rig animations (walk, idle, attack, climb, chest-beat)

2. Entity Class Implementation
   - Extend MobEntity class
   - Implement custom AI goals:
     - PatrolVillageGoal
     - DefendVillageGoal
     - MeleeAttackGoal
     - FollowPlayerWithBananaGoal
   - Add attribute modifiers (health, damage, speed)

3. Special Abilities
   - Implement ground pound attack
   - Add climbing capability
   - Create knockback effects

4. Spawn Logic
   - Create village spawn conditions
   - Implement spawn egg
   - Add banana feeding mechanics

**Deliverables:**
- Functional King Kong entity
- Animations working properly
- Defending villagers from hostile mobs

---

### Phase 4: Godzilla Entity (Week 4)

**Tasks:**
1. Model Creation
   - Design 3D model with dorsal plates
   - Create texture with glowing blue details
   - Rig animations (walk, idle, attack, roar, breath)

2. Entity Class Implementation
   - Extend MobEntity class
   - Implement custom AI goals:
     - GuardVillageGoal (ranged)
     - RangedAttackGoal (atomic breath)
     - StayNearWaterGoal
     - DefendVillageGoal
   - Add special attributes (fire immunity, high health)

3. Atomic Breath Attack
   - Create particle effects system
   - Implement raycast damage
   - Add cooldown management
   - Create visual and sound effects

4. Spawn Logic
   - Create village spawn conditions (near water)
   - Implement spawn egg
   - Add fish feeding mechanics

**Deliverables:**
- Functional Godzilla entity
- Atomic breath attack working
- Proper AI behavior

---

### Phase 5: Village Structure Generation (Week 5)

**Tasks:**
1. Structure Design
   - Create village building templates (.nbt files)
   - Design Kong tower structures
   - Design Godzilla pond area
   - Create central shrine

2. Structure Generation
   - Implement custom village type
   - Configure structure spawning rules
   - Add to world generation

3. Integration
   - Ensure Kong/Godzilla spawn in custom villages
   - Verify Joaquin villagers populate structures

**Deliverables:**
- Custom Joaquintown villages generating in world
- All entities spawning correctly in villages

---

### Phase 6: Polish & Balance (Week 6)

**Tasks:**
1. Balancing
   - Adjust entity health/damage values
   - Fine-tune spawn rates
   - Balance ability cooldowns

2. Sound Effects
   - Add Kong roar/chest-beat sounds
   - Add Godzilla roar and atomic breath sounds
   - Add custom villager sounds (optional)

3. Particle Effects
   - Polish atomic breath particles
   - Add ground pound particles
   - Add ambient particles for both entities

4. Configuration
   - Create config file for adjustable values
   - Allow spawn rate customization
   - Toggle features on/off

**Deliverables:**
- Balanced gameplay experience
- Audio-visual polish complete
- Configurable mod settings

---

### Phase 7: Testing & Bug Fixes (Week 7)

**Tasks:**
1. Comprehensive Testing
   - Test all entity interactions
   - Verify multiplayer compatibility
   - Test with other mods (basic compatibility)
   - Performance testing

2. Bug Fixing
   - Address crashes
   - Fix rendering issues
   - Resolve AI pathfinding problems

3. Documentation
   - Write user guide
   - Create configuration guide
   - Document known issues

**Deliverables:**
- Stable, tested mod
- User documentation

---

### Phase 8: Release Preparation (Week 8)

**Tasks:**
1. Final Build
   - Clean up code
   - Remove debug logging
   - Optimize performance

2. Release Assets
   - Create mod icon
   - Write CurseForge/Modrinth description
   - Prepare screenshots and video

3. Publishing
   - Upload to CurseForge
   - Upload to Modrinth
   - Create GitHub repository

**Deliverables:**
- Published mod version 1.0.0

---

## 4. Technical Challenges & Solutions

### Challenge 1: Custom Large Entity Models
**Problem:** Minecraft's default entity system is optimized for smaller models.  
**Solution:** Use scaled-down models with hitbox adjustments, or implement multi-part entities for very large creatures.

### Challenge 2: Atomic Breath Projectile
**Problem:** Creating a beam-like projectile instead of standard projectile arc.  
**Solution:** Use rapid-fire invisible projectiles with particle effects, or implement raycast damage with particle trail.

### Challenge 3: Village Structure Generation
**Problem:** Integrating custom structures into Minecraft's village system.  
**Solution:** Create new village type using Fabric's structure API, or use existing village logic with custom building pieces.

### Challenge 4: Entity Pathfinding
**Problem:** Large entities may struggle with pathfinding in villages.  
**Solution:** Implement custom pathfinding goals, increase pathfinding range, or restrict patrol areas.

---

## 5. Asset Creation Requirements

### Textures Needed
- [ ] Joaquin villager base texture (male)
- [ ] Joaquin villager base texture (female)
- [ ] Villager profession overlays (if needed)
- [ ] King Kong texture (512x512 recommended)
- [ ] Godzilla texture (1024x1024 recommended)
- [ ] Particle textures (atomic breath, ground pound)
- [ ] GUI elements (spawn eggs)

### Models Needed
- [ ] King Kong model (.json)
- [ ] Godzilla model (.json)
- [ ] Village structure templates (.nbt)

### Sound Effects Needed
- [ ] Kong roar (1-2 variations)
- [ ] Kong chest beat
- [ ] Kong footsteps
- [ ] Godzilla roar (2-3 variations)
- [ ] Atomic breath charge and fire
- [ ] Godzilla footsteps

### Tools
- **Blockbench:** For 3D model creation and animation
- **Paint.NET / GIMP:** For texture creation
- **Audacity:** For sound editing
- **Minecraft Structure Planner:** For village building design

---

## 6. Testing Plan

### Unit Testing
- Entity spawning mechanics
- AI goal behaviors
- Attack damage calculations
- Ability cooldown timers

### Integration Testing
- Entity interaction with villagers
- Village structure generation
- Multiplayer synchronization
- Mod compatibility (Fabric API, common mods)

### User Acceptance Testing
- Play testing with target user (Joaquin)
- Gather feedback on entity appearance
- Adjust difficulty/balance based on play experience
- Verify fun factor and engagement

---

## 7. Configuration Options

Create a config file (`joaquintown-config.json`) with the following options:

```json
{
  "entitySpawning": {
    "kingKongSpawnRate": 0.5,
    "godzillaSpawnRate": 0.3,
    "maxKongPerVillage": 2,
    "maxGodzillaPerVillage": 1
  },
  "entityStats": {
    "kingKong": {
      "health": 200,
      "damage": 17.5,
      "speed": 0.3,
      "groundPoundCooldown": 15
    },
    "godzilla": {
      "health": 300,
      "damage": 22.5,
      "speed": 0.25,
      "atomicBreathCooldown": 10,
      "atomicBreathDamage": 15
    }
  },
  "villageGeneration": {
    "enabled": true,
    "generationWeight": 5,
    "biomes": ["plains", "savanna"]
  },
  "villagersUseJoaquinSkin": true
}
```

---

## 8. Future Enhancement Ideas

### Version 1.1+
- [ ] Additional guardian entities (Mothra, Rodan as flying defenders?)
- [ ] King Kong vs Godzilla friendly sparring animations
- [ ] Rideable Kong/Godzilla (with special saddle)
- [ ] Custom trades for Joaquin villagers
- [ ] Achievement system for befriending guardians
- [ ] Kong can throw boulders at distant enemies
- [ ] Godzilla tail can destroy blocks when swinging
- [ ] Custom village quests or events

---

## 9. Resources & References

### Fabric Development
- Official Fabric Wiki: https://fabricmc.net/wiki/
- Fabric API Documentation: https://github.com/FabricMC/fabric
- Example Mods: https://github.com/FabricMC/fabric-example-mod

### Minecraft Modding
- Minecraft Wiki (Entity Data): https://minecraft.wiki/
- Blockbench: https://www.blockbench.net/
- Minecraft Commands Generator: https://mcstacker.net/

### Community Resources
- Fabric Discord: https://discord.gg/v6v4pMv
- r/fabricmc: Reddit community for support
- r/Minecraft_Modding: General modding help

---

## 10. Development Timeline Summary

| Phase | Duration | Focus Area | Key Deliverable |
|-------|----------|------------|-----------------|
| 1 | Week 1 | Setup | Mod skeleton |
| 2 | Week 2 | Villagers | Custom villager appearance |
| 3 | Week 3 | King Kong | Functional Kong entity |
| 4 | Week 4 | Godzilla | Functional Godzilla entity |
| 5 | Week 5 | Villages | Custom village generation |
| 6 | Week 6 | Polish | Balanced, polished gameplay |
| 7 | Week 7 | Testing | Stable release candidate |
| 8 | Week 8 | Release | Published mod |

**Total Estimated Time:** 8 weeks (assuming part-time development, ~10-15 hours per week)

---

## 11. Getting Started - First Steps

1. **Install Development Environment**
   ```bash
   # Install Java 21
   # Download IntelliJ IDEA Community Edition
   # Install Git
   ```

2. **Create Project from Template**
   ```bash
   git clone https://github.com/FabricMC/fabric-example-mod
   cd fabric-example-mod
   # Update gradle.properties to Minecraft 1.21.1
   ```

3. **Test Build**
   ```bash
   ./gradlew build
   ./gradlew runClient
   ```

4. **Start with Phase 1 tasks**
   - Rename package structure
   - Update mod metadata
   - Create basic mod initializer

---

## Notes

- Keep code well-documented for maintainability
- Regular testing throughout development is crucial
- Consider creating a GitHub repository for version control
- Back up work frequently
- Start simple - implement core features before advanced abilities
- Get feedback from Joaquin early and often to ensure he enjoys it!

---

**Document Version:** 1.0  
**Last Updated:** October 2025  
**Next Review:** After Phase 2 completion
