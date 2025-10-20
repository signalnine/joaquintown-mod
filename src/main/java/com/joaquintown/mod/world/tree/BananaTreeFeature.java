package com.joaquintown.mod.world.tree;

import com.joaquintown.mod.block.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

/**
 * Custom tree feature that generates banana trees with bananas on the trunk.
 */
public class BananaTreeFeature extends Feature<TreeFeatureConfig> {

    public BananaTreeFeature(Codec<TreeFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<TreeFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = context.getOrigin();
        Random random = context.getRandom();

        // Simple banana tree: 4-6 blocks tall trunk with leaves on top
        int height = 4 + random.nextInt(3);

        // Check if we can place the tree
        if (pos.getY() < world.getBottomY() + 1 || pos.getY() + height >= world.getTopY()) {
            return false;
        }

        // Check ground block
        BlockPos below = pos.down();
        BlockState groundState = world.getBlockState(below);
        if (!groundState.isIn(BlockTags.DIRT) && !groundState.isOf(Blocks.GRASS_BLOCK)) {
            return false;
        }

        // Generate trunk using jungle logs
        for (int i = 0; i < height; i++) {
            BlockPos trunkPos = pos.up(i);
            world.setBlockState(trunkPos, Blocks.JUNGLE_LOG.getDefaultState(), 3);
        }

        // Generate leaves (3x3x2 on top)
        BlockPos topPos = pos.up(height);
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                for (int y = 0; y <= 1; y++) {
                    BlockPos leafPos = topPos.add(x, y, z);
                    // Skip center of top layer
                    if (y == 1 && x == 0 && z == 0) continue;

                    if (world.getBlockState(leafPos).isAir()) {
                        world.setBlockState(leafPos, ModBlocks.BANANA_LEAVES.getDefaultState(), 3);
                    }
                }
            }
        }

        // Add bananas to the trunk (random placement)
        for (int i = 1; i < height - 1; i++) {
            if (random.nextFloat() < 0.6f) { // 60% chance per trunk block
                BlockPos trunkPos = pos.up(i);
                Direction direction = Direction.Type.HORIZONTAL.random(random);
                BlockPos bananaPos = trunkPos.offset(direction);

                if (world.getBlockState(bananaPos).isAir()) {
                    // Place mature bananas
                    world.setBlockState(bananaPos, ModBlocks.BANANAS.getDefaultState()
                            .with(Properties.HORIZONTAL_FACING, direction)
                            .with(Properties.AGE_2, 2), 3);
                }
            }
        }

        return true;
    }
}
