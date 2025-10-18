# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Joaquintown is a Fabric mod for Minecraft 1.21.1 that welcomes players to "Joaquintown" and spawns a plains village at world origin on first server load. The project is in early development with plans to add custom villagers, King Kong and Godzilla guardian entities, and custom village structures.

## Build and Development Commands

**Environment Setup:**
- Set `JAVA_HOME` to Java 21 before running Gradle: `export JAVA_HOME=/opt/homebrew/opt/openjdk@21/libexec/openjdk.jdk/Contents/Home` (macOS example)
- After updating Yarn mappings or Loom versions: `./gradlew genSources`

**Essential Commands:**
- Build mod JAR: `./gradlew build` (output: `build/libs/joaquintown-1.0.0.jar`)
- Launch dev client: `./gradlew runClient`
- Launch dev server: `./gradlew runServer`
- Clean build artifacts: `./gradlew clean`
- Run tests: `./gradlew test`

**Testing in Minecraft:**
- The mod auto-spawns a plains village at world spawn after 40 ticks (2 seconds)
- To regenerate the village: delete `run/saves/<world>/data/joaquintown_spawn_village.dat` and reload the world
- New players receive a gold "Welcome to Joaquintown!" message on join

## Code Architecture

**Initialization Flow:**
The mod entry point is `JoaquintownMod` (implements `ModInitializer`). On initialization:
1. Registers player join handler to send welcome message
2. Schedules village placement via `SERVER_STARTED` event (40-tick delay)
3. Uses `END_SERVER_TICK` to count down and execute placement
4. Retries every 200 ticks if placement fails

**Village Placement System:**
- `JoaquintownMod.ensureSpawnVillage()` drives the placement logic
- Uses Minecraft's command system: executes `place structure minecraft:village_plains` at spawn coordinates
- `SpawnVillageState` (extends `PersistentState`) tracks whether village has been placed, persisted to world save data
- Placement only happens once per world; idempotent on server restarts

**Persistent State Pattern:**
`SpawnVillageState` demonstrates the standard pattern for world-scoped persistent data:
- Stored in world save directory as `joaquintown_spawn_village.dat`
- Retrieved via `PersistentStateManager` with a `Type<T>` codec
- Changes trigger `markDirty()` to schedule save to disk

**Mixin Usage:**
`ExampleMixin` injects into `MinecraftServer.loadWorld` as a development diagnostic. Mixins are declared in `joaquintown.mixins.json` and applied by Fabric's mixin subsystem.

**Namespace and Identifiers:**
- Mod ID: `joaquintown` (defined in `JoaquintownMod.MOD_ID`)
- Java package: `com.joaquintown.mod`
- Resource paths use `joaquintown` namespace for custom assets and data

## Configuration Files

**fabric.mod.json:**
- Sets mod metadata, entrypoints, and dependencies
- Main entrypoint: `com.joaquintown.mod.JoaquintownMod`
- Requires Fabric Loader 0.15.0+, Minecraft 1.21.1, Java 21, Fabric API

**joaquintown.accesswidener:**
- Referenced in `build.gradle` via `accessWidenerPath`
- Update this when accessing private/protected Minecraft fields or methods

**gradle.properties:**
- Minecraft 1.21.1, Yarn mappings 1.21.1+build.3, Fabric Loader 0.15.11, Fabric API 0.102.1+1.21.1
- Mod version: 1.0.0, Maven group: com.joaquintown.mod

## Key Implementation Notes

**When adding new entities:**
- Create entity classes in `com.joaquintown.mod.entity` (plan includes `KingKongEntity`, `GodzillaEntity`)
- Register in a new `ModEntities` initialization class
- Use Blockbench for 3D models/animations, export as JSON
- Entity AI goals should extend standard Minecraft goals (e.g., `MeleeAttackGoal`, custom `DefendVillageGoal`)

**When adding custom village structures:**
- Create `.nbt` structure templates using structure blocks in-game
- Place in `src/main/resources/data/joaquintown/structures/`
- Register via Fabric's structure API
- Update village spawn logic to reference custom structure type

**When adding custom villager textures:**
- Texture replacement: override via `src/main/resources/assets/minecraft/textures/entity/villager/`
- Custom entity: create new entity type in `entity/villager/` and register separately
- Curly hair and accessories overlays should be on separate texture layers

**Event-driven architecture:**
- Prefer Fabric API events over mixins when possible
- Current events: `ServerPlayConnectionEvents.JOIN`, `ServerLifecycleEvents`, `ServerTickEvents`
- Schedule delayed tasks via tick counters stored in `WeakHashMap` (see `VILLAGE_PLACEMENT_SCHEDULE`)

## Development Workflow

**Adding new features:**
1. Read DESIGN.md for planned architecture and phased development plan
2. Create feature branches if using Git (not yet initialized)
3. Update both `fabric.mod.json` and access widener together if changing visibility
4. Test in dev client/server before building release JAR
5. Add unit tests in `src/test/java` mirroring package structure

**Debugging:**
- Logs output to console and `run/logs/latest.log`
- Use `JoaquintownMod.LOGGER` for mod-specific logging
- Loom enables hot-reload for some changes; restart client/server for class structure changes

**Code style:**
- Java 21, tab indentation, same-line braces
- PascalCase classes, camelCase methods, UPPER_SNAKE_CASE constants
- Keep imports explicit, remove unused imports

## Future Development Context

DESIGN.md outlines an 8-phase plan (currently in Phase 1):
- Phase 2: Custom Joaquin-styled villagers (curly hair, accessories)
- Phase 3: King Kong entity (4-5 blocks tall, ground pound, climbing, chest-beat)
- Phase 4: Godzilla entity (6-8 blocks tall, atomic breath, tail swipe)
- Phase 5: Custom village structures with Kong towers and Godzilla pools
- Phases 6-8: Polish, testing, release

Refer to DESIGN.md for detailed specifications on entity stats, AI behaviors, textures, models, and configuration options.
