package com.joaquintown.mod.world;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SpawnVillageState persistent state management.
 * These tests verify NBT serialization and state management without requiring a full game environment.
 */
@DisplayName("SpawnVillageState Unit Tests")
class SpawnVillageStateUnitTest {

	private SpawnVillageState state;
	private static final String VILLAGE_PLACED_KEY = "VillagePlaced";
	private static final String OUTPOST_PLACED_KEY = "OutpostPlaced";
	private static final String LOOT_PLAZA_PLACED_KEY = "LootPlazaPlaced";
	private static final String VERTICAL_STACK_PLACED_KEY = "VerticalStackPlaced";

	@BeforeEach
	void setUp() {
		// Create a fresh state for each test
		state = new SpawnVillageState();
	}

	@Test
	@DisplayName("Initial state should be false (no village placed)")
	void testInitialState() {
		assertFalse(state.hasVillage(), "Newly created SpawnVillageState should have hasVillage() = false");
	}

	@Test
	@DisplayName("Marking village as placed should update state")
	void testMarkVillagePlaced() {
		// Initially false
		assertFalse(state.hasVillage(), "Initial state should be false");

		// Mark as placed
		state.markVillagePlaced();

		// Now should be true
		assertTrue(state.hasVillage(), "After markVillagePlaced(), hasVillage() should return true");
	}

	@Test
	@DisplayName("State should persist through NBT serialization")
	void testNbtSerialization() {
		// Set state to true
		state.markVillagePlaced();
		assertTrue(state.hasVillage(), "State should be true before serialization");

		// Serialize to NBT
		NbtCompound nbt = new NbtCompound();
		state.writeNbt(nbt, null);

		// Verify NBT contains expected data
		assertTrue(nbt.contains(VILLAGE_PLACED_KEY), "NBT should contain 'VillagePlaced' key");
		assertTrue(nbt.getBoolean(VILLAGE_PLACED_KEY), "VillagePlaced should be true in NBT");
	}

	@Test
	@DisplayName("NBT serialization contains correct boolean value (true)")
	void testNbtSerializationTrue() {
		// Set state to true and serialize
		state.markVillagePlaced();
		NbtCompound nbt = new NbtCompound();
		state.writeNbt(nbt, null);

		// Verify NBT has correct value
		assertTrue(nbt.getBoolean(VILLAGE_PLACED_KEY),
			"NBT should contain VillagePlaced=true after marking village as placed");
	}

	@Test
	@DisplayName("NBT serialization contains correct boolean value (false)")
	void testNbtSerializationFalse() {
		// State is false by default, serialize without marking
		NbtCompound nbt = new NbtCompound();
		state.writeNbt(nbt, null);

		// Verify NBT has correct value
		assertFalse(nbt.getBoolean(VILLAGE_PLACED_KEY),
			"NBT should contain VillagePlaced=false when village not placed");
	}

	@Test
	@DisplayName("NBT contains only expected keys")
	void testNbtDataFormat() {
		// Test that NBT data is in expected format for world save
		state.markVillagePlaced();
		NbtCompound nbt = new NbtCompound();
		state.writeNbt(nbt, null);

		// Verify NBT structure - should contain exactly four keys
		assertEquals(4, nbt.getSize(), "NBT should contain exactly four keys");
		assertTrue(nbt.contains(VILLAGE_PLACED_KEY), "NBT should contain VillagePlaced key");
		assertTrue(nbt.contains(OUTPOST_PLACED_KEY), "NBT should contain OutpostPlaced key");
		assertTrue(nbt.contains(LOOT_PLAZA_PLACED_KEY), "NBT should contain LootPlazaPlaced key");
		assertTrue(nbt.contains(VERTICAL_STACK_PLACED_KEY), "NBT should contain VerticalStackPlaced key");

		// Verify they're stored as booleans
		assertTrue(nbt.getBoolean(VILLAGE_PLACED_KEY), "VillagePlaced should be true when marked");
		assertFalse(nbt.getBoolean(OUTPOST_PLACED_KEY), "OutpostPlaced should be false when not marked");
		assertFalse(nbt.getBoolean(LOOT_PLAZA_PLACED_KEY), "LootPlazaPlaced should be false when not marked");
		assertFalse(nbt.getBoolean(VERTICAL_STACK_PLACED_KEY), "VerticalStackPlaced should be false when not marked");
	}

	@Test
	@DisplayName("Multiple markVillagePlaced calls should remain true")
	void testMultipleMarkCalls() {
		// Mark multiple times
		state.markVillagePlaced();
		state.markVillagePlaced();
		state.markVillagePlaced();

		// Should still be true (idempotent)
		assertTrue(state.hasVillage(), "Multiple markVillagePlaced() calls should keep state as true");
	}

	@Test
	@DisplayName("State cannot be unmarked once placed")
	void testStateImmutability() {
		// Mark as placed
		state.markVillagePlaced();
		assertTrue(state.hasVillage(), "State should be true");

		// There's no way to unmark it - this is by design
		// The API only provides markVillagePlaced(), not unmark
		// Verify state stays true
		assertTrue(state.hasVillage(), "State should remain true, cannot be unmarked");

		// After serialization, the NBT should still contain true
		NbtCompound nbt = new NbtCompound();
		state.writeNbt(nbt, null);
		assertTrue(nbt.getBoolean(VILLAGE_PLACED_KEY),
			"NBT should persist the true state");
	}

	@Test
	@DisplayName("NBT key name should match expected constant")
	void testNbtKeyName() {
		state.markVillagePlaced();
		NbtCompound nbt = new NbtCompound();
		state.writeNbt(nbt, null);

		// Verify the exact key name used
		assertTrue(nbt.contains("VillagePlaced"), "NBT should use key 'VillagePlaced'");
		assertFalse(nbt.contains("villagePlaced"), "Key should not be lowercase");
		assertFalse(nbt.contains("village_placed"), "Key should not use underscores");
	}

	@Test
	@DisplayName("Initial outpost state should be false")
	void testInitialOutpostState() {
		assertFalse(state.hasOutpost(), "Newly created SpawnVillageState should have hasOutpost() = false");
	}

	@Test
	@DisplayName("Marking outpost as placed should update state")
	void testMarkOutpostPlaced() {
		// Initially false
		assertFalse(state.hasOutpost(), "Initial outpost state should be false");

		// Mark as placed
		state.markOutpostPlaced();

		// Now should be true
		assertTrue(state.hasOutpost(), "After markOutpostPlaced(), hasOutpost() should return true");
	}

	@Test
	@DisplayName("Village and outpost states are independent")
	void testIndependentStates() {
		// Mark only village
		state.markVillagePlaced();
		assertTrue(state.hasVillage(), "Village should be marked");
		assertFalse(state.hasOutpost(), "Outpost should still be false");

		// Mark only outpost in a new state
		SpawnVillageState state2 = new SpawnVillageState();
		state2.markOutpostPlaced();
		assertFalse(state2.hasVillage(), "Village should be false");
		assertTrue(state2.hasOutpost(), "Outpost should be marked");
	}

	@Test
	@DisplayName("Initial loot plaza state should be false")
	void testInitialLootPlazaState() {
		assertFalse(state.hasLootPlaza(), "Newly created SpawnVillageState should have hasLootPlaza() = false");
	}

	@Test
	@DisplayName("Marking loot plaza as placed should update state")
	void testMarkLootPlazaPlaced() {
		assertFalse(state.hasLootPlaza(), "Initial loot plaza state should be false");
		state.markLootPlazaPlaced();
		assertTrue(state.hasLootPlaza(), "After markLootPlazaPlaced(), hasLootPlaza() should return true");
	}

	@Test
	@DisplayName("Initial vertical stack state should be false")
	void testInitialVerticalStackState() {
		assertFalse(state.hasVerticalStack(), "Newly created SpawnVillageState should have hasVerticalStack() = false");
	}

	@Test
	@DisplayName("Marking vertical stack as placed should update state")
	void testMarkVerticalStackPlaced() {
		assertFalse(state.hasVerticalStack(), "Initial vertical stack state should be false");
		state.markVerticalStackPlaced();
		assertTrue(state.hasVerticalStack(), "After markVerticalStackPlaced(), hasVerticalStack() should return true");
	}

	@Test
	@DisplayName("All structure states are independent")
	void testAllStatesIndependent() {
		// Mark each state independently and verify others remain false
		state.markVillagePlaced();
		assertTrue(state.hasVillage());
		assertFalse(state.hasOutpost());
		assertFalse(state.hasLootPlaza());
		assertFalse(state.hasVerticalStack());

		SpawnVillageState state2 = new SpawnVillageState();
		state2.markLootPlazaPlaced();
		assertFalse(state2.hasVillage());
		assertFalse(state2.hasOutpost());
		assertTrue(state2.hasLootPlaza());
		assertFalse(state2.hasVerticalStack());

		SpawnVillageState state3 = new SpawnVillageState();
		state3.markVerticalStackPlaced();
		assertFalse(state3.hasVillage());
		assertFalse(state3.hasOutpost());
		assertFalse(state3.hasLootPlaza());
		assertTrue(state3.hasVerticalStack());
	}
}
