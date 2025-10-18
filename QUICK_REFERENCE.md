# Joaquintown Mod - Quick Reference Guide

## Quick Stats

### Godzilla
```
Health:    ████████████████████ 600 HP
Attack:    ████████████████     40 damage
Armor:     ███████████████      15 + 8 toughness
Speed:     ████████             0.25
Knockback: ███████████████████  1.0 (immune)
```

### King Kong
```
Health:    ████████████████     500 HP
Attack:    ██████████████       35 damage
Armor:     ████████████         12 + 6 toughness
Speed:     █████████████        0.30
Knockback: ███████████████████  1.0 (immune)
```

### Warden (for comparison)
```
Health:    ████████████████     500 HP
Attack:    ████████████         30 damage
Speed:     █████████            0.30
```

---

## Command Quick Reference

### Testing Commands

```bash
# Spawn Godzilla
/summon joaquintown:godzilla ~ ~ ~

# Spawn King Kong
/summon joaquintown:king_kong ~ ~ ~

# Give banana
/give @p joaquintown:banana 64

# Give Godzilla spawn egg
/give @p joaquintown:godzilla_spawn_egg

# Give King Kong spawn egg
/give @p joaquintown:king_kong_spawn_egg

# Locate nearest village
/locate structure minecraft:village_plains

# Check entity data
/data get entity @e[type=joaquintown:godzilla,limit=1]
```

---

## File Locations Quick List

### Source Code
```
Entity Classes:
  - src/main/java/com/joaquintown/mod/entity/GodzillaEntity.java
  - src/main/java/com/joaquintown/mod/entity/KingKongEntity.java

Models:
  - src/main/java/com/joaquintown/mod/client/model/GodzillaModel.java
  - src/main/java/com/joaquintown/mod/client/model/KingKongEntityModel.java

Renderers:
  - src/main/java/com/joaquintown/mod/client/renderer/GodzillaRenderer.java
  - src/main/java/com/joaquintown/mod/client/renderer/KingKongRenderer.java

Mixins:
  - src/main/java/com/joaquintown/mod/mixin/*.java
```

### Assets
```
Godzilla Model:
  - src/main/resources/assets/joaquintown/geo/godzilla.geo.json
  - src/main/resources/assets/joaquintown/animations/godzilla.animation.json
  - src/main/resources/assets/joaquintown/textures/entity/godzilla.png

King Kong:
  - src/main/resources/assets/joaquintown/textures/entity/king_kong.png

Sounds:
  - src/main/resources/assets/joaquintown/sounds/entity/godzilla/
  - src/main/resources/assets/joaquintown/sounds/entity/iron_golem/
  - src/main/resources/assets/joaquintown/sounds.json
```

---

## Build Commands

```bash
# Full rebuild
./gradlew clean build

# Quick build (incremental)
./gradlew build

# Test in-game
./gradlew runClient

# Run server
./gradlew runServer

# Tests only
./gradlew test

# Clean all build artifacts
./gradlew clean
```

---

## Common Issues & Solutions

### Issue: Game crashes on load
**Solution:** Check that GeckoLib 4.6.1 is properly included in dependencies

### Issue: Entities don't spawn
**Solution:** Verify ModEntities.registerEntities() is called in mod initialization

### Issue: Sounds don't play
**Solution:** Ensure sounds.json references correct file paths and files are .ogg format

### Issue: Villagers still use emeralds
**Solution:** TradeOfferMixin must be registered in joaquintown.mixins.json

### Issue: Build warnings about IronGolemEntityMixin
**Solution:** This is expected - the mixin is disabled. Warning is harmless.

---

## Mod ID Reference

```
Mod ID: joaquintown

Entity IDs:
  - joaquintown:godzilla
  - joaquintown:king_kong

Item IDs:
  - joaquintown:banana
  - joaquintown:godzilla_spawn_egg
  - joaquintown:king_kong_spawn_egg

Sound Events:
  - joaquintown:entity.villager.ambient
  - joaquintown:entity.villager.hurt
  - joaquintown:entity.villager.death
  - joaquintown:entity.zombie.ambient
  - joaquintown:entity.zombie.hurt
  - joaquintown:entity.zombie.death
  - joaquintown:entity.iron_golem.ambient
  - joaquintown:entity.iron_golem.hurt
  - joaquintown:entity.iron_golem.death
  - joaquintown:entity.ravager.ambient
  - joaquintown:entity.ravager.hurt
  - joaquintown:entity.ravager.death
```

---

## Testing Checklist

- [ ] Build completes without errors
- [ ] All tests pass (./gradlew test)
- [ ] Game loads without crashes
- [ ] New world spawns village at spawn
- [ ] Godzilla spawns north of village
- [ ] King Kong spawns south of village
- [ ] Both kaiju are persistent
- [ ] Kaiju attack hostile mobs
- [ ] Kaiju don't attack each other or iron golems
- [ ] Kaiju don't attack creepers
- [ ] Godzilla plays custom roar sounds
- [ ] King Kong plays custom roar sounds
- [ ] Villagers have no nose
- [ ] Villagers play custom sounds
- [ ] Villagers trade with bananas instead of emeralds
- [ ] Banana item exists and is edible
- [ ] Spawn eggs work correctly
- [ ] Kaiju can defeat Wardens

---

## Performance Tips

1. **Ambient Sound Frequency:** Already set to 20-40 seconds
2. **Entity Limits:** Kaiju are persistent; limit spawning via spawn eggs
3. **GeckoLib Animations:** Optimized for Godzilla only
4. **Mixin Count:** 6 active mixins (keep minimal)

---

## Useful NBT Data

### Check Godzilla Health
```bash
/data get entity @e[type=joaquintown:godzilla,limit=1] Health
```

### Check King Kong Target
```bash
/data get entity @e[type=joaquintown:king_kong,limit=1] Brain.memories."minecraft:attack_target"
```

### Make Kaiju Invulnerable (testing)
```bash
/data merge entity @e[type=joaquintown:godzilla,limit=1] {Invulnerable:1b}
```

---

## Dependency Versions

```
Minecraft: 1.21.1
Fabric Loader: 0.16.2
Fabric API: 0.102.1+1.21.1
GeckoLib: 4.6.1
Loom: 1.7.2
Gradle: 8.10
Java: 21
```

---

## Key Timings

- Village spawn delay: 2 seconds after server start
- Village retry delay: 10 seconds
- Godzilla ambient roar: 20-40 seconds
- King Kong ambient roar: 20-40 seconds
- Entity tracking range: Godzilla 80 blocks, King Kong 64 blocks

---

*For complete documentation, see MOD_DOCUMENTATION.md*
