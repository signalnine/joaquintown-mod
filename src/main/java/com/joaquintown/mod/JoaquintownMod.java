package com.joaquintown.mod;

import com.joaquintown.mod.block.ModBlocks;
import com.joaquintown.mod.entity.GodzillaEntity;
import com.joaquintown.mod.entity.KingKongEntity;
import com.joaquintown.mod.init.ModEntities;
import com.joaquintown.mod.item.ModItems;
import com.joaquintown.mod.sound.ModSounds;
import com.joaquintown.mod.world.SpawnVillageState;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.WeakHashMap;

public class JoaquintownMod implements ModInitializer {
	public static final String MOD_ID = "joaquintown";
	private static final Identifier VILLAGE_ID = Identifier.of("minecraft", "village_plains");
	private static final Identifier PILLAGER_OUTPOST_ID = Identifier.of("minecraft", "pillager_outpost");
	private static final Identifier DESERT_PYRAMID_ID = Identifier.of("minecraft", "desert_pyramid");
	private static final Identifier SHIPWRECK_ID = Identifier.of("minecraft", "shipwreck");
	private static final Identifier SWAMP_HUT_ID = Identifier.of("minecraft", "swamp_hut");
	private static final Identifier IGLOO_ID = Identifier.of("minecraft", "igloo");
	private static final Identifier MANSION_ID = Identifier.of("minecraft", "mansion");
	private static final Identifier FORTRESS_ID = Identifier.of("minecraft", "fortress");
	private static final Identifier END_CITY_ID = Identifier.of("minecraft", "end_city");
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static final Text WELCOME_TEXT = Text.literal("Welcome to Joaquintown!").formatted(Formatting.GOLD);
	private static final int INITIAL_VILLAGE_DELAY_TICKS = 40;
	private static final int RETRY_VILLAGE_DELAY_TICKS = 200;
	private static final int OUTPOST_DELAY_TICKS = 100;  // 5 seconds - allow chunks to load
	private static final int LOOT_PLAZA_DELAY_TICKS = 300;  // 15 seconds - more time for distant chunks
	private static final int VERTICAL_STACK_DELAY_TICKS = 600;  // 30 seconds - maximum time for very distant structures
	private static final int SKY_OUTPOST_Y_LEVEL = 150;
	private static final int LOOT_PLAZA_RADIUS = 80;
	private static final int VERTICAL_STACK_DISTANCE = 120;
	private static final Map<MinecraftServer, Integer> VILLAGE_PLACEMENT_SCHEDULE = new WeakHashMap<>();
	private static final Map<MinecraftServer, Integer> OUTPOST_PLACEMENT_SCHEDULE = new WeakHashMap<>();
	private static final Map<MinecraftServer, Integer> LOOT_PLAZA_SCHEDULE = new WeakHashMap<>();
	private static final Map<MinecraftServer, Integer> VERTICAL_STACK_SCHEDULE = new WeakHashMap<>();

	@Override
	public void onInitialize() {
		LOGGER.info("Joaquintown mod initializing.");

		// Register custom blocks
		ModBlocks.registerModBlocks();
		LOGGER.info("Custom blocks registered.");

		// Register custom items
		ModItems.registerModItems();
		LOGGER.info("Custom items registered.");

		// Register custom sounds
		ModSounds.registerSounds();
		LOGGER.info("Custom villager sounds registered.");

		// Register custom entities
		ModEntities.registerEntities();
		LOGGER.info("Custom entities registered.");

		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> handler.player.sendMessage(WELCOME_TEXT, false));
		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			VILLAGE_PLACEMENT_SCHEDULE.put(server, INITIAL_VILLAGE_DELAY_TICKS);
			OUTPOST_PLACEMENT_SCHEDULE.put(server, OUTPOST_DELAY_TICKS);
			LOOT_PLAZA_SCHEDULE.put(server, LOOT_PLAZA_DELAY_TICKS);
			VERTICAL_STACK_SCHEDULE.put(server, VERTICAL_STACK_DELAY_TICKS);
		});
		ServerLifecycleEvents.SERVER_STOPPING.register(server -> {
			VILLAGE_PLACEMENT_SCHEDULE.remove(server);
			OUTPOST_PLACEMENT_SCHEDULE.remove(server);
			LOOT_PLAZA_SCHEDULE.remove(server);
			VERTICAL_STACK_SCHEDULE.remove(server);
		});
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			// Handle village placement
			Integer villageRemaining = VILLAGE_PLACEMENT_SCHEDULE.get(server);
			if (villageRemaining != null) {
				if (villageRemaining > 0) {
					VILLAGE_PLACEMENT_SCHEDULE.put(server, villageRemaining - 1);
				} else {
					if (ensureSpawnVillage(server)) {
						VILLAGE_PLACEMENT_SCHEDULE.remove(server);
					} else {
						VILLAGE_PLACEMENT_SCHEDULE.put(server, RETRY_VILLAGE_DELAY_TICKS);
					}
				}
			}

			// Handle outpost placement
			Integer outpostRemaining = OUTPOST_PLACEMENT_SCHEDULE.get(server);
			if (outpostRemaining != null) {
				if (outpostRemaining > 0) {
					OUTPOST_PLACEMENT_SCHEDULE.put(server, outpostRemaining - 1);
				} else {
					if (ensureSkyOutpost(server)) {
						OUTPOST_PLACEMENT_SCHEDULE.remove(server);
					} else {
						OUTPOST_PLACEMENT_SCHEDULE.put(server, RETRY_VILLAGE_DELAY_TICKS);
					}
				}
			}

			// Handle loot plaza placement
			Integer plazaRemaining = LOOT_PLAZA_SCHEDULE.get(server);
			if (plazaRemaining != null) {
				if (plazaRemaining > 0) {
					LOOT_PLAZA_SCHEDULE.put(server, plazaRemaining - 1);
				} else {
					if (ensureLootPlaza(server)) {
						LOOT_PLAZA_SCHEDULE.remove(server);
					} else {
						LOOT_PLAZA_SCHEDULE.put(server, RETRY_VILLAGE_DELAY_TICKS);
					}
				}
			}

			// Handle vertical stack placement
			Integer stackRemaining = VERTICAL_STACK_SCHEDULE.get(server);
			if (stackRemaining != null) {
				if (stackRemaining > 0) {
					VERTICAL_STACK_SCHEDULE.put(server, stackRemaining - 1);
				} else {
					if (ensureVerticalStack(server)) {
						VERTICAL_STACK_SCHEDULE.remove(server);
					} else {
						VERTICAL_STACK_SCHEDULE.put(server, RETRY_VILLAGE_DELAY_TICKS);
					}
				}
			}
		});
	}

	private boolean ensureSpawnVillage(MinecraftServer server) {
		ServerWorld world = server.getOverworld();
		if (world == null) {
			LOGGER.warn("No overworld available to place Joaquintown village.");
			return false;
		}

		SpawnVillageState state = SpawnVillageState.get(world);
		if (state.hasVillage()) {
			LOGGER.debug("Spawn village already present; skipping placement.");
			return true;
		}

		BlockPos spawnPos = world.getSpawnPos();
		BlockPos placementPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, spawnPos);
		world.getChunk(placementPos.getX() >> 4, placementPos.getZ() >> 4); // ensure chunk loaded

		String command = String.format("place structure %s %d %d %d", VILLAGE_ID, placementPos.getX(), placementPos.getY(), placementPos.getZ());
		try {
			int result = server.getCommandManager().getDispatcher().execute(
				command,
				server.getCommandSource()
					.withWorld(world)
					.withPosition(Vec3d.ofCenter(placementPos))
					.withRotation(Vec2f.ZERO)
			);
			if (result > 0) {
				state.markVillagePlaced();
				LOGGER.info("Placed Joaquintown village at {}", placementPos);

				// Spawn village defenders: Godzilla and King Kong
				spawnVillageDefenders(world, placementPos);

				return true;
			}

			LOGGER.warn("Command returned {} while attempting to place Joaquintown village at {}", result, placementPos);
		} catch (CommandSyntaxException e) {
			LOGGER.warn("Failed to place Joaquintown village at {}", placementPos, e);
		}
		return false;
	}

	private void spawnVillageDefenders(ServerWorld world, BlockPos villageCenter) {
		// Spawn Godzilla to the north of village center
		BlockPos godzillaPos = villageCenter.north(15);
		godzillaPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, godzillaPos);
		GodzillaEntity godzilla = new GodzillaEntity(ModEntities.GODZILLA, world);
		godzilla.refreshPositionAndAngles(godzillaPos.getX() + 0.5, godzillaPos.getY(), godzillaPos.getZ() + 0.5, 0.0f, 0.0f);
		godzilla.setPersistent();
		world.spawnEntity(godzilla);
		LOGGER.info("Spawned Godzilla at {}", godzillaPos);

		// Spawn King Kong to the south of village center
		BlockPos kingKongPos = villageCenter.south(15);
		kingKongPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, kingKongPos);
		KingKongEntity kingKong = new KingKongEntity(ModEntities.KING_KONG, world);
		kingKong.refreshPositionAndAngles(kingKongPos.getX() + 0.5, kingKongPos.getY(), kingKongPos.getZ() + 0.5, 0.0f, 0.0f);
		kingKong.setPersistent();
		world.spawnEntity(kingKong);
		LOGGER.info("Spawned King Kong at {}", kingKongPos);
	}

	private boolean ensureSkyOutpost(MinecraftServer server) {
		ServerWorld world = server.getOverworld();
		if (world == null) {
			LOGGER.warn("No overworld available to place sky pillager outpost.");
			return false;
		}

		SpawnVillageState state = SpawnVillageState.get(world);
		if (state.hasOutpost()) {
			LOGGER.debug("Sky outpost already present; skipping placement.");
			return true;
		}

		BlockPos spawnPos = world.getSpawnPos();
		// Place outpost in the sky at a fixed Y level, offset to the east
		BlockPos placementPos = new BlockPos(spawnPos.getX() + 30, SKY_OUTPOST_Y_LEVEL, spawnPos.getZ());
		world.getChunk(placementPos.getX() >> 4, placementPos.getZ() >> 4); // ensure chunk loaded

		String command = String.format("place structure %s %d %d %d", PILLAGER_OUTPOST_ID, placementPos.getX(), placementPos.getY(), placementPos.getZ());
		try {
			int result = server.getCommandManager().getDispatcher().execute(
				command,
				server.getCommandSource()
					.withWorld(world)
					.withPosition(Vec3d.ofCenter(placementPos))
					.withRotation(Vec2f.ZERO)
			);
			if (result > 0) {
				state.markOutpostPlaced();
				LOGGER.info("Placed sky pillager outpost at {}", placementPos);
				return true;
			}

			LOGGER.warn("Command returned {} while attempting to place sky outpost at {}", result, placementPos);
		} catch (CommandSyntaxException e) {
			LOGGER.warn("Failed to place sky pillager outpost at {}", placementPos, e);
		}
		return false;
	}

	private boolean ensureLootPlaza(MinecraftServer server) {
		ServerWorld world = server.getOverworld();
		if (world == null) {
			LOGGER.warn("No overworld available to place loot plaza.");
			return false;
		}

		SpawnVillageState state = SpawnVillageState.get(world);
		if (state.hasLootPlaza()) {
			LOGGER.debug("Loot plaza already present; skipping placement.");
			return true;
		}

		// Mark as placed immediately to prevent infinite retries
		state.markLootPlazaPlaced();

		BlockPos spawnPos = world.getSpawnPos();

		// Place structures in a circle around spawn at ground level
		// Desert Pyramid - North
		BlockPos northPos = spawnPos.north(LOOT_PLAZA_RADIUS);
		northPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, northPos);
		placeStructure(server, world, DESERT_PYRAMID_ID, northPos, "Desert Pyramid (North)");

		// Shipwreck - South
		BlockPos southPos = spawnPos.south(LOOT_PLAZA_RADIUS);
		southPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, southPos);
		placeStructure(server, world, SHIPWRECK_ID, southPos, "Shipwreck (South)");

		// Swamp Hut - East
		BlockPos eastPos = spawnPos.east(LOOT_PLAZA_RADIUS);
		eastPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, eastPos);
		placeStructure(server, world, SWAMP_HUT_ID, eastPos, "Swamp Hut (East)");

		// Igloo - West
		BlockPos westPos = spawnPos.west(LOOT_PLAZA_RADIUS);
		westPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, westPos);
		placeStructure(server, world, IGLOO_ID, westPos, "Igloo (West)");

		LOGGER.info("Completed loot plaza placement around spawn");
		return true;
	}

	private boolean ensureVerticalStack(MinecraftServer server) {
		ServerWorld world = server.getOverworld();
		if (world == null) {
			LOGGER.warn("No overworld available to place vertical stack.");
			return false;
		}

		SpawnVillageState state = SpawnVillageState.get(world);
		if (state.hasVerticalStack()) {
			LOGGER.debug("Vertical stack already present; skipping placement.");
			return true;
		}

		// Mark as placed immediately to prevent infinite retries
		state.markVerticalStackPlaced();

		BlockPos spawnPos = world.getSpawnPos();

		// Place structures vertically stacked to the northwest
		BlockPos stackBase = spawnPos.north(VERTICAL_STACK_DISTANCE).west(VERTICAL_STACK_DISTANCE);

		// Fortress - Bottom (Y=80)
		BlockPos fortressPos = new BlockPos(stackBase.getX(), 80, stackBase.getZ());
		placeStructure(server, world, FORTRESS_ID, fortressPos, "Fortress (Y=80)");

		// Woodland Mansion - Level 2 (Y=120)
		BlockPos mansionPos = new BlockPos(stackBase.getX(), 120, stackBase.getZ());
		placeStructure(server, world, MANSION_ID, mansionPos, "Woodland Mansion (Y=120)");

		// End City - Top (Y=200)
		BlockPos cityPos = new BlockPos(stackBase.getX(), 200, stackBase.getZ());
		placeStructure(server, world, END_CITY_ID, cityPos, "End City (Y=200)");

		LOGGER.info("Completed vertical stack placement");
		return true;
	}

	private boolean placeStructure(MinecraftServer server, ServerWorld world, Identifier structureId, BlockPos pos, String name) {
		// Load a 5x5 chunk area around the structure position to ensure it can place
		int centerChunkX = pos.getX() >> 4;
		int centerChunkZ = pos.getZ() >> 4;
		for (int dx = -2; dx <= 2; dx++) {
			for (int dz = -2; dz <= 2; dz++) {
				world.getChunk(centerChunkX + dx, centerChunkZ + dz);
			}
		}

		String command = String.format("place structure %s %d %d %d", structureId, pos.getX(), pos.getY(), pos.getZ());
		try {
			int result = server.getCommandManager().getDispatcher().execute(
				command,
				server.getCommandSource()
					.withWorld(world)
					.withPosition(Vec3d.ofCenter(pos))
					.withRotation(Vec2f.ZERO)
			);
			if (result > 0) {
				LOGGER.info("Placed {} at {}", name, pos);
				return true;
			}

			LOGGER.warn("Command returned {} while attempting to place {} at {}", result, name, pos);
		} catch (CommandSyntaxException e) {
			LOGGER.warn("Failed to place {} at {}", name, pos, e);
		}
		return false;
	}
}
