package com.toadnamedduck.agentsoficecrown.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RuneforgeBlock extends HorizontalDirectionalBlock {
    public static final MapCodec<RuneforgeBlock> CODEC = simpleCodec(RuneforgeBlock::new);
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    public RuneforgeBlock(Properties properties){
        super(properties);
        registerDefaultState(stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(HALF, DoubleBlockHalf.LOWER)
        );
    }

    @Override
    protected @NotNull MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context){
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        if(pos.getY() < level.getMaxBuildHeight() - 1
                &&level.getBlockState(pos.above()).canBeReplaced(context)){
            return defaultBlockState()
                    .setValue(FACING, context.getHorizontalDirection().getOpposite())
                    .setValue(HALF, DoubleBlockHalf.LOWER);
        }
        return null;
    }

    @Override
    protected BlockState updateShape(BlockState state, @NotNull Direction facing, @NotNull BlockState facingState, @NotNull LevelAccessor level, @NotNull BlockPos currentPos, @NotNull BlockPos facingPos){
        //state = self state, facing is direction update comes from, facingState is the state of the block that sent the update

        DoubleBlockHalf doubleBlockHalf = state.getValue(HALF);

        boolean fromPartner =
                (doubleBlockHalf == DoubleBlockHalf.LOWER && facing == Direction.UP) || (doubleBlockHalf == DoubleBlockHalf.UPPER && facing == Direction.DOWN);

        if(!fromPartner){//if the update didnt come from the partner, don't care
            return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
        }
        if(facingState.is(this) && facingState.getValue(HALF) != doubleBlockHalf) {//came from partner, check that it's still there and is still the opposite half
            return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
        }
        //If the update came from the partner, but it's no longer our other half
        return Blocks.AIR.defaultBlockState();
    }

    @Override
    public @NotNull BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        DoubleBlockHalf half = state.getValue(HALF);
        BlockPos belowPos = pos.below();
        BlockState belowState = level.getBlockState(belowPos);
        int belowStateID = Block.getId(belowState);

        if(!level.isClientSide
                && player.isCreative()
                && half == DoubleBlockHalf.UPPER
                && belowState.is(this)
                && belowState.getValue(HALF) != half

        ){
            level.setBlock(belowPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL | Block.UPDATE_SUPPRESS_DROPS);
            level.levelEvent(null, 2001, belowPos, belowStateID);
        }

        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack){
        level.setBlock(pos.above(), state.setValue(HALF, DoubleBlockHalf.UPPER), 3);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
        builder.add(FACING).add(HALF);
    }
}
