package com.joaquintown.mod.block;

import com.joaquintown.mod.world.tree.BananaSaplingGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;

/**
 * Banana sapling - grows into a banana tree when planted.
 */
public class BananaSaplingBlock extends SaplingBlock {

    public BananaSaplingBlock(Settings settings) {
        super(BananaSaplingGenerator.INSTANCE, settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getLightLevel(pos.up()) >= 9 && random.nextInt(7) == 0) {
            this.generate(world, pos, state, random);
        }
    }

    @Override
    public boolean isFertilizable(net.minecraft.world.WorldView world, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean canGrow(net.minecraft.world.World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        this.generate(world, pos, state, random);
    }

    @Override
    public void generate(ServerWorld world, BlockPos pos, BlockState state, Random random) {
        // Simple banana tree: 4-6 blocks tall trunk with leaves on top
        int height = 4 + random.nextInt(3);

        // Check if we can place the tree
        if (pos.getY() < world.getBottomY() + 1 || pos.getY() + height >= world.getTopY()) {
            return;
        }

        // Check ground block
        BlockPos below = pos.down();
        BlockState groundState = world.getBlockState(below);
        if (!groundState.isIn(BlockTags.DIRT) && !groundState.isOf(Blocks.GRASS_BLOCK)) {
            return;
        }

        // Remove sapling
        world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);

        // Generate trunk using jungle logs
        for (int i = 0; i < height; i++) {
            BlockPos trunkPos = pos.up(i);
            world.setBlockState(trunkPos, Blocks.JUNGLE_LOG.getDefaultState(), Block.NOTIFY_ALL);
        }

        // Generate leaves (3x3x2 on top)
        BlockPos topPos = pos.up(height);
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                for (int y = 0; y <= 1; y++) {
                    BlockPos leafPos = topPos.add(x, y, z);
                    // Skip center of top layer
                    if (y == 1 && x == 0 && z == 0) continue;

                    if (world.getBlockState(leafPos).isAir() || world.getBlockState(leafPos).isOf(this)) {
                        world.setBlockState(leafPos, ModBlocks.BANANA_LEAVES.getDefaultState(), Block.NOTIFY_ALL);
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
                            .with(Properties.AGE_2, 2), Block.NOTIFY_ALL);
                }
            }
        }
    }
}
