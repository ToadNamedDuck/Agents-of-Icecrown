package com.toadnamedduck.agentsoficecrown.block;

import com.toadnamedduck.agentsoficecrown.blockentity.ModBlockEntities;
import com.toadnamedduck.agentsoficecrown.blockentity.SoulCondenserBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SoulCondenser extends Block implements EntityBlock {
    public SoulCondenser(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new SoulCondenserBlockEntity(blockPos, blockState);
    }

    @Nullable
    @SuppressWarnings("unchecked")
    private static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> serverType, BlockEntityType<E> clientType, BlockEntityTicker<? super E> ticker) {
        return clientType == serverType ? (BlockEntityTicker<A>) ticker : null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type){
        if(level.isClientSide()) return null;
        return createTickerHelper(type, ModBlockEntities.SOUL_CONDENSER_ENTITY.get(), SoulCondenserBlockEntity::tick);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult){
        if(level.isClientSide()) return InteractionResult.sidedSuccess(true);
        if(level.getBlockEntity(pos) instanceof SoulCondenserBlockEntity entity){
            player.openMenu(entity);
        }
        return InteractionResult.CONSUME;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston){
        if(level.getBlockEntity(pos) instanceof SoulCondenserBlockEntity entity && !state.is(newState.getBlock())){
            entity.dropContents(level, pos);
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }
}
