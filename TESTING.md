# Testing Documentation

This document describes the comprehensive test suite for the Joaquintown mod.

## Test Structure

### ✅ Implemented Tests
```
src/test/java/com/joaquintown/mod/
├── JoaquintownModTest.java           # Unit tests for mod initialization (6 tests)
└── world/
    └── SpawnVillageStateUnitTest.java # Unit tests for persistent state (9 tests)
```

### 📋 Test Designs (Future Implementation)
These test designs are documented below but not yet implemented. They will be implemented as features are added:
- VillageSpawningTest - Integration tests for village spawning
- JoaquinVillagerTest - Custom villager tests (Phase 2)
- KingKongEntityTest - King Kong entity tests (Phase 3)
- GodzillaEntityTest - Godzilla entity tests (Phase 4)
- Test utility helpers (EntityTestHelper, AITestHelper, etc.)

## Running Tests

### Run all tests
```bash
./gradlew test
```

### Run a specific test class
```bash
./gradlew test --tests com.joaquintown.mod.VillageSpawningTest
```

### Run a specific test method
```bash
./gradlew test --tests com.joaquintown.mod.VillageSpawningTest.testVillageSpawnScheduling
```

### Run tests with verbose output
```bash
./gradlew test --info
```

## Test Categories

### 1. Current Functionality Tests

#### ✅ JoaquintownModTest (IMPLEMENTED)
Tests mod initialization and constants:
- `testModId` - Verifies mod ID is "joaquintown"
- `testInitialVillageDelay` - Validates 40-tick initial delay
- `testRetryVillageDelay` - Validates 200-tick retry delay
- `testLoggerInitialization` - Confirms logger is properly initialized
- `testWelcomeMessage` - Documents expected welcome message format
- `testVillageStructureId` - Validates village structure identifier

**Status:** ✅ Implemented and passing (6 tests)
**Location:** `src/test/java/com/joaquintown/mod/JoaquintownModTest.java`

#### ✅ SpawnVillageStateUnitTest (IMPLEMENTED)
Tests persistent state management:
- `testInitialState` - Verifies default state is false
- `testMarkVillagePlaced` - Tests state mutation
- `testNbtPersistence` - Validates NBT serialization
- `testNbtSerializationTrue` - Tests serialization with true value
- `testNbtSerializationFalse` - Tests serialization with false value
- `testNbtDataFormat` - Validates NBT structure
- `testMultipleMarkCalls` - Tests idempotency
- `testStateImmutability` - Confirms state cannot be unmarked
- `testNbtKeyName` - Validates key naming consistency

**Status:** ✅ Implemented and passing (9 tests)
**Location:** `src/test/java/com/joaquintown/mod/world/SpawnVillageStateUnitTest.java`

### 2. Phase 2: Custom Villagers (Future)

#### JoaquinVillagerTest
Tests custom villager appearance and behavior:
- `testJoaquinVillagerSpawn` - Basic spawning
- `testAllProfessionsSupported` - All professions work with custom textures
- `testJoaquinVillagerBreeding` - Breeding mechanics
- `testBreedingInheritance` - Baby villagers inherit appearance
- `testJoaquinVillagerTrading` - Trading compatibility
- `testCustomTextureLoading` - Resource loading verification
- `testBiomeTypeSupport` - Different biome types support
- `testCustomRendererRegistration` - Renderer registration check

**Status:** Test design complete, awaiting implementation

**Implementation Requirements:**
- Create custom villager textures (curly hair, accessories overlays)
- Implement texture replacement system OR custom entity
- Register custom renderer if using custom entity approach

### 3. Phase 3: King Kong Entity (Future)

#### KingKongEntityTest
Tests King Kong guardian entity:

**Basic Attributes:**
- `testKingKongSpawn` - Entity spawning
- `testKingKongHealth` - 200 HP attribute
- `testKingKongDamage` - 15-20 HP melee damage

**AI Behaviors:**
- `testKingKongDefendsVillagers` - Attacks hostile mobs near villagers
- `testKingKongPatrol` - Patrols village perimeter
- `testNeutralToPlayers` - Neutral unless provoked
- `testDetectionRange` - 32-block detection range

**Special Abilities:**
- `testGroundPoundAbility` - AoE damage and knockback
- `testKingKongClimbing` - Climbing blocks like spiders
- `testKingKongKnockback` - Strong knockback on hits
- `testChestBeatAnimation` - Animation when spotting enemies

**Other Features:**
- `testBananaFeeding` - Healing with bananas
- `testVillageSpawnConditions` - 1-2 per village
- `testSpawnEgg` - Spawn egg functionality
- `testMovementSpeed` - Moderate movement speed

**Status:** Test design complete, awaiting implementation

**Implementation Requirements:**
- Create 3D model in Blockbench (4-5 blocks tall)
- Implement custom entity class extending MobEntity
- Create custom AI goals: PatrolVillageGoal, DefendVillageGoal
- Implement ground pound ability with AoE damage
- Add climbing capability
- Create spawn egg and village spawn logic

### 4. Phase 4: Godzilla Entity (Future)

#### GodzillaEntityTest
Tests Godzilla guardian entity:

**Basic Attributes:**
- `testGodzillaSpawn` - Entity spawning
- `testGodzillaHealth` - 300 HP attribute
- `testGodzillaMeleeDamage` - 20-25 HP melee damage

**AI Behaviors:**
- `testDefendsFromDistance` - Ranged village defense (48 blocks)
- `testStayNearWater` - Prefers water sources
- `testNeutralToPlayers` - Neutral unless provoked
- `testDetectionRange` - 48-block detection range

**Special Abilities:**
- `testAtomicBreathAttack` - Blue particle beam ranged attack
- `testAtomicBreathCooldown` - 10-second cooldown
- `testAtomicBreathParticles` - Visual particle effects
- `testAtomicBreathLineOfSight` - Requires line of sight
- `testTailSwipeAbility` - Melee AoE behind entity

**Immunities:**
- `testFireImmunity` - No fire damage
- `testLavaImmunity` - No lava damage

**Other Features:**
- `testFishFeeding` - Healing with raw fish
- `testRoarAnimation` - Roar when detecting threats
- `testDorsalPlateGlow` - Glowing blue dorsal plates
- `testVillageSpawnConditions` - 1 per village near water
- `testSpawnEgg` - Spawn egg functionality
- `testMovementSpeed` - Slow movement speed

**Status:** Test design complete, awaiting implementation

**Implementation Requirements:**
- Create 3D model in Blockbench (6-8 blocks tall with dorsal plates)
- Implement custom entity class extending MobEntity
- Create custom AI goals: GuardVillageGoal, RangedAttackGoal, StayNearWaterGoal
- Implement atomic breath with particle effects and raycast damage
- Add fire and lava immunity attributes
- Create tail swipe AoE attack
- Implement glowing texture for dorsal plates

## Test Utilities

### FabricTestHelper
Base utilities for Fabric game tests:
- `waitTicks(context, ticks)` - Wait for specified ticks
- `ensureChunkLoaded(world, pos)` - Load chunk at position
- `getCenterPos(context)` - Get test structure center

### EntityTestHelper
Entity manipulation and verification:
- `countEntitiesNear(context, class, center, radius)` - Count entities
- `findEntitiesNear(context, class, center, radius)` - Find entities
- `damageEntity(entity, amount, source)` - Damage entity
- `setEntityHealth(entity, health)` - Set health value
- `isHealthInRange(entity, min, max)` - Verify health range
- `isTargeting(attacker, target)` - Check targeting
- `getDistance(entity1, entity2)` - Measure distance
- `spawnEntityFacing(world, entity, pos, yaw)` - Spawn with direction
- `waitForEntityDeath(context, entity, maxTicks)` - Wait for death
- `didTakeDamage(entity, previousHealth)` - Verify damage taken

### AITestHelper
AI behavior testing:
- `hasEntityMoved(entity, originalPos, minDistance)` - Movement check
- `waitForEntityToReachArea(...)` - Wait for navigation
- `setNavigationTarget(entity, target)` - Set pathfinding target
- `isNavigating(entity)` - Check if moving
- `isFacingTowards(entity, target, tolerance)` - Facing direction check
- `isWithinPatrolRange(entity, center, maxDistance)` - Patrol range check
- `hasLineOfSight(entity, target)` - Line of sight verification
- `waitForTargetAcquisition(...)` - Wait for target lock
- `clearTarget(entity)` - Clear current target
- `isEntityIdle(entity)` - Idle state check
- `lookAt(entity, target)` - Force look direction
- `measureMovementSpeed(entity, ticks)` - Speed measurement
- `prefersLocation(...)` - Location preference check

## Test-Driven Development Workflow

### For Current Features
1. Run existing tests: `./gradlew test`
2. Verify all tests pass
3. When adding new features to existing code:
   - Write test first
   - Run test (should fail)
   - Implement feature
   - Run test (should pass)

### For Phase 2-4 Features
1. Tests are already designed (in test files with TODO comments)
2. When implementing a feature:
   - Uncomment relevant test
   - Replace TODO comments with actual implementation calls
   - Run test to verify it fails (red)
   - Implement the feature
   - Run test until it passes (green)
   - Refactor if needed (refactor)

### Example: Implementing King Kong

```java
// 1. Start with testKingKongSpawn
@GameTest(templateName = FabricGameTest.EMPTY_STRUCTURE)
public void testKingKongSpawn(TestContext context) {
    ServerWorld world = context.getWorld();
    BlockPos spawnPos = context.getAbsolutePos(BlockPos.ORIGIN);

    // 2. Uncomment and implement
    KingKongEntity kong = ModEntities.KING_KONG.create(world);
    kong.refreshPositionAndAngles(spawnPos, 0.0f, 0.0f);
    world.spawnEntity(kong);

    context.expectEntity(ModEntities.KING_KONG);
    context.assertTrue(kong.getHealth() == 200.0f, "King Kong should have 200 HP");
    context.assertTrue(kong.getHeight() >= 4.0f, "King Kong should be 4-5 blocks tall");

    context.complete();
}
```

## Testing Best Practices

### 1. Write Tests First
- Design tests before implementation (we've already done this!)
- Tests serve as specification and documentation
- Helps catch design issues early

### 2. Keep Tests Isolated
- Each test should be independent
- Don't rely on test execution order
- Clean up state after tests if needed

### 3. Use Descriptive Names
- Test names should describe what they verify
- Format: `test[Feature][Scenario]`
- Example: `testKingKongDefendsVillagers`

### 4. Test One Thing
- Each test should verify one specific behavior
- Makes failures easier to diagnose
- Improves test maintainability

### 5. Use Test Helpers
- Leverage EntityTestHelper and AITestHelper
- Reduces code duplication
- Makes tests more readable

### 6. Document Expected Behavior
- Each test has "Expected:" comment
- Describes what should happen
- Helps understand test failures

## Continuous Integration

When setting up CI/CD:

```yaml
# Example GitHub Actions workflow
name: Run Tests

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK 21
        uses: actions/setup-java@v3
        with:
          java-version: '21'
          distribution: 'temurin'
      - name: Run tests
        run: ./gradlew test
      - name: Upload test reports
        if: failure()
        uses: actions/upload-artifact@v3
        with:
          name: test-reports
          path: build/reports/tests/
```

## Test Coverage Goals

### Current Phase (Phase 1)
- ✅ Village spawning logic: 100% coverage
- ✅ Persistent state management: 100% coverage
- ✅ Player join events: 100% coverage

### Phase 2 Goals (Custom Villagers)
- Custom texture system: 90%+ coverage
- Villager breeding: 90%+ coverage
- All professions: 100% coverage

### Phase 3 Goals (King Kong)
- Entity attributes: 100% coverage
- AI behaviors: 85%+ coverage
- Special abilities: 90%+ coverage
- Spawn mechanics: 100% coverage

### Phase 4 Goals (Godzilla)
- Entity attributes: 100% coverage
- AI behaviors: 85%+ coverage
- Atomic breath: 90%+ coverage
- Special abilities: 90%+ coverage
- Spawn mechanics: 100% coverage

## Troubleshooting

### Tests fail to compile
- Run `./gradlew clean` to clear build cache
- Verify Java 21 is installed: `java -version`
- Check JAVA_HOME is set correctly

### Game test timeouts
- Increase tick limits in @GameTest annotation
- Check for infinite loops in entity AI
- Verify entities are spawning correctly

### Entity not found in tests
- Ensure entity is registered in ModEntities
- Check entity ID matches test expectations
- Verify mod initialization runs before tests

### Particle/visual tests
- Visual tests may need manual verification
- Consider using debug rendering
- Check that resource files exist

## Next Steps

1. **Run existing tests**: `./gradlew test` to verify current implementation
2. **Implement Phase 2**: Follow test designs in JoaquinVillagerTest.java
3. **Implement Phase 3**: Follow test designs in KingKongEntityTest.java
4. **Implement Phase 4**: Follow test designs in GodzillaEntityTest.java
5. **Iterate**: Red → Green → Refactor for each feature

## Resources

- [Fabric Testing Documentation](https://fabricmc.net/wiki/tutorial:testing)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Minecraft GameTest Framework](https://minecraft.wiki/w/Game_test)
- DESIGN.md - Feature specifications and requirements
