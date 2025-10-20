package com.joaquintown.mod.block;

import com.joaquintown.mod.JoaquintownMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

/**
 * Registers custom blocks for the Joaquintown mod.
 */
public class ModBlocks {

    /**
     * Banana block - grows on jungle logs like cocoa pods.
     */
    public static final Block BANANAS = registerBlock("bananas",
            new BananaBlock(AbstractBlock.Settings.create()
                    .ticksRandomly()
                    .strength(0.2f, 3.0f)
                    .sounds(BlockSoundGroup.WOOD)
                    .nonOpaque()));

    /**
     * Banana sapling - grows into a banana tree.
     */
    public static final Block BANANA_SAPLING = registerBlock("banana_sapling",
            new BananaSaplingBlock(AbstractBlock.Settings.create()
                    .ticksRandomly()
                    .strength(0.0f)
                    .sounds(BlockSoundGroup.GRASS)
                    .nonOpaque()
                    .noCollision()));

    /**
     * Banana leaves - decorative leaves for banana trees.
     */
    public static final Block BANANA_LEAVES = registerBlock("banana_leaves",
            new BananaLeavesBlock(AbstractBlock.Settings.create()
                    .strength(0.2f)
                    .sounds(BlockSoundGroup.GRASS)
                    .nonOpaque()
                    .allowsSpawning((state, world, pos, type) -> false)
                    .suffocates((state, world, pos) -> false)
                    .blockVision((state, world, pos) -> false)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(JoaquintownMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(JoaquintownMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    /**
     * Register all custom blocks and add them to creative inventory.
     */
    public static void registerModBlocks() {
        JoaquintownMod.LOGGER.info("Registering mod blocks for " + JoaquintownMod.MOD_ID);

        // Add bananas to the Natural Blocks creative tab
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.add(BANANA_SAPLING);
            entries.add(BANANA_LEAVES);
            entries.add(BANANAS);
        });
    }
}
