package com.toadnamedduck.agentsoficecrown.block;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AltarBlock extends Block implements EntityBlock {
    public AltarBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new AltarBlockEntity(blockPos, blockState);
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        //Basically - if the entity somehow isn't an AltarBlockEntity, or if the ItemStack in it isn't empty, or the player hand ain't holding nothin, just do a default - otherwise, add item to altar :D
        if(!(level.getBlockEntity(pos) instanceof AltarBlockEntity altarBlockEntity) || !altarBlockEntity.getItemStack().isEmpty() || stack.isEmpty()){
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        //Now that that's done, add the held item to the altar ON THE SERVER SIDE (and make sure the altar doesn't already have an item >.>)!
        if(!level.isClientSide() && altarBlockEntity.getItemStack().isEmpty()){
            altarBlockEntity.setItemStack(stack.copyWithCount(1)); //Add a copy of whatever in hand
            stack.consume(1, player);//Consume 1 from the player's stack
        }
        return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull BlockHitResult hitResult) {
        if(!(level.getBlockEntity(pos) instanceof AltarBlockEntity altarBlockEntity) || altarBlockEntity.getItemStack().isEmpty() || !player.getMainHandItem().isEmpty()){
            return InteractionResult.PASS;
        }
        if(!level.isClientSide()){
            player.setItemInHand(InteractionHand.MAIN_HAND, altarBlockEntity.getItemStack());
            altarBlockEntity.setItemStack(ItemStack.EMPTY);
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }
}
