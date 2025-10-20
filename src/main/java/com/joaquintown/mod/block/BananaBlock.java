package com.joaquintown.mod.block;

import com.joaquintown.mod.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

/**
 * Banana block that grows on jungle tree logs, similar to cocoa pods.
 * Has three growth stages: small (0), medium (1), and large/mature (2).
 */
public class BananaBlock extends Block {
    public static final int MAX_AGE = 2;
    public static final IntProperty AGE = Properties.AGE_2;
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    // Collision boxes for each growth stage
    private static final VoxelShape[] SHAPES_BY_AGE = new VoxelShape[]{
            Block.createCuboidShape(6.0, 7.0, 6.0, 10.0, 12.0, 10.0),  // Small
            Block.createCuboidShape(5.0, 5.0, 5.0, 11.0, 12.0, 11.0),  // Medium
            Block.createCuboidShape(4.0, 3.0, 4.0, 12.0, 12.0, 12.0)   // Large/Mature
    };

    public BananaBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(AGE, 0)
                .with(FACING, Direction.NORTH));
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        // Grow bananas over time, similar to crops
        if (random.nextInt(5) == 0) {
            int age = state.get(AGE);
            if (age < MAX_AGE) {
                world.setBlockState(pos, state.with(AGE, age + 1), Block.NOTIFY_LISTENERS);
            }
        }
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState attachedState = world.getBlockState(pos.offset(state.get(FACING)));
        // Can only be placed on jungle logs
        return attachedState.isIn(BlockTags.JUNGLE_LOGS);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES_BY_AGE[state.get(AGE)];
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = this.getDefaultState();
        WorldView worldView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();

        // Try to attach to each horizontal direction
        for (Direction direction : ctx.getPlacementDirections()) {
            if (direction.getAxis().isHorizontal()) {
                blockState = blockState.with(FACING, direction);
                if (blockState.canPlaceAt(worldView, blockPos)) {
                    return blockState;
                }
            }
        }

        return null;
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE, FACING);
    }

    @Override
    protected boolean hasRandomTicks(BlockState state) {
        return state.get(AGE) < MAX_AGE;
    }
}
