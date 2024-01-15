package com.mrmelon54.ArcTech.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.pathfinder.PathComputationType;
import org.jetbrains.annotations.Nullable;

public class WireBlock extends PipeBlock {
    public WireBlock(Properties properties) {
        super(1 / 16f, properties);
        registerDefaultState(getStateDefinition().any().setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false).setValue(UP, false).setValue(DOWN, false));
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        return blockState.setValue(PROPERTY_BY_DIRECTION.get(direction), this.connectsTo(blockState2));
    }

    private boolean connectsTo(BlockState blockState) {
        return this.isSameWire(blockState);
    }

    private boolean isSameWire(BlockState blockState) {
        return blockState.is(this);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return this.getStateForPlacement(blockPlaceContext.getLevel(), blockPlaceContext.getClickedPos());
    }

    private BlockState getStateForPlacement(BlockGetter blockGetter, BlockPos blockPos) {
        BlockState b = this.defaultBlockState();
        b = b.setValue(DOWN, blockGetter.getBlockState(blockPos.below()).is(this));
        b = b.setValue(UP, blockGetter.getBlockState(blockPos.above()).is(this));
        b = b.setValue(NORTH, blockGetter.getBlockState(blockPos.north()).is(this));
        b = b.setValue(EAST, blockGetter.getBlockState(blockPos.east()).is(this));
        b = b.setValue(SOUTH, blockGetter.getBlockState(blockPos.south()).is(this));
        b = b.setValue(WEST, blockGetter.getBlockState(blockPos.west()).is(this));
        return b;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
    }

    @Override
    public boolean isPathfindable(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, PathComputationType pathComputationType) {
        return false;
    }
}
