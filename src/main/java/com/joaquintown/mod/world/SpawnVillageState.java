package com.joaquintown.mod.world;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentState.Type;
import net.minecraft.world.PersistentStateManager;

public final class SpawnVillageState extends PersistentState {
	private static final String VILLAGE_KEY = "VillagePlaced";
	private static final String OUTPOST_KEY = "OutpostPlaced";
	private static final String LOOT_PLAZA_KEY = "LootPlazaPlaced";
	private static final String VERTICAL_STACK_KEY = "VerticalStackPlaced";
	private boolean villagePlaced;
	private boolean outpostPlaced;
	private boolean lootPlazaPlaced;
	private boolean verticalStackPlaced;
	public static final Type<SpawnVillageState> TYPE = new Type<>(SpawnVillageState::new, SpawnVillageState::readNbt, null);

	public static SpawnVillageState get(ServerWorld world) {
		PersistentStateManager manager = world.getPersistentStateManager();
		return manager.getOrCreate(TYPE, "joaquintown_spawn_village");
	}

	private static SpawnVillageState readNbt(NbtCompound tag, WrapperLookup lookup) {
		SpawnVillageState state = new SpawnVillageState();
		state.villagePlaced = tag.getBoolean(VILLAGE_KEY);
		state.outpostPlaced = tag.getBoolean(OUTPOST_KEY);
		state.lootPlazaPlaced = tag.getBoolean(LOOT_PLAZA_KEY);
		state.verticalStackPlaced = tag.getBoolean(VERTICAL_STACK_KEY);
		return state;
	}

	@Override
	public NbtCompound writeNbt(NbtCompound nbt, WrapperLookup lookup) {
		nbt.putBoolean(VILLAGE_KEY, villagePlaced);
		nbt.putBoolean(OUTPOST_KEY, outpostPlaced);
		nbt.putBoolean(LOOT_PLAZA_KEY, lootPlazaPlaced);
		nbt.putBoolean(VERTICAL_STACK_KEY, verticalStackPlaced);
		return nbt;
	}

	public boolean hasVillage() {
		return villagePlaced;
	}

	public void markVillagePlaced() {
		villagePlaced = true;
		markDirty();
	}

	public boolean hasOutpost() {
		return outpostPlaced;
	}

	public void markOutpostPlaced() {
		outpostPlaced = true;
		markDirty();
	}

	public boolean hasLootPlaza() {
		return lootPlazaPlaced;
	}

	public void markLootPlazaPlaced() {
		lootPlazaPlaced = true;
		markDirty();
	}

	public boolean hasVerticalStack() {
		return verticalStackPlaced;
	}

	public void markVerticalStackPlaced() {
		verticalStackPlaced = true;
		markDirty();
	}
}
