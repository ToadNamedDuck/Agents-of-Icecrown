package com.toadnamedduck.agentsoficecrown.block;

import com.toadnamedduck.agentsoficecrown.blockentity.AltarBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AltarBlock extends Block implements EntityBlock {
    public AltarBlock(Properties properties) {
        super(properties);
    }
    public static final VoxelShape BASEPLATE_0 = Block.box(0, 0, 0, 16, 2, 16);
    public static final VoxelShape BASEPLATE_1 = Block.box(2,2,2,14,4,14);
    public static final VoxelShape POST = Block.box(4,4,4,12,7,12);
    public static final VoxelShape BOWL_BOTTOM_0 = Block.box(3,7,3,13,9,4);
    public static final VoxelShape BOWL_BOTTOM_1 = Block.box(3,7,12,13,9,13);
    public static final VoxelShape BOWL_BOTTOM_2 = Block.box(3,7,4,4,9,12);
    public static final VoxelShape BOWL_BOTTOM_3 = Block.box(12,7,4,13,9,12);
    public static final VoxelShape BOWL_TOP_0 = Block.box(2,9,3,3,10,13);
    public static final VoxelShape BOWL_TOP_1 = Block.box(13,9,3,14,10,13);
    public static final VoxelShape BOWL_TOP_2 = Block.box(2,9,13,14,10,14);
    public static final VoxelShape BOWL_TOP_3 = Block.box(2,9,2,14,10,3);
    public static final VoxelShape UNIFIED_ALTAR = Shapes.or(
            BASEPLATE_0,
            BASEPLATE_1,
            POST,
            BOWL_BOTTOM_0,
            BOWL_BOTTOM_1,
            BOWL_BOTTOM_2,
            BOWL_BOTTOM_3,
            BOWL_TOP_0,
            BOWL_TOP_1,
            BOWL_TOP_2,
            BOWL_TOP_3);

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new AltarBlockEntity(blockPos, blockState);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return UNIFIED_ALTAR;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return UNIFIED_ALTAR;
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        //Basically - if the entity somehow isn't an AltarBlockEntity, or the player hand ain't holding nothin, just do a default - otherwise, add item to altar :D
        if(!(level.getBlockEntity(pos) instanceof AltarBlockEntity altarBlockEntity) || stack.isEmpty()){
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        //If the altar is full and player is holding an item - arm still swings, but nothing happens :)
        if(!altarBlockEntity.getItemStack().isEmpty()){
            return ItemInteractionResult.sidedSuccess(level.isClientSide());
        }
        //Now that that's done, add the held item to the altar ON THE SERVER SIDE (and make sure the altar doesn't already have an item >.>)!
        if(!level.isClientSide()){
            altarBlockEntity.setItemStack(stack.copyWithCount(1), level); //Add a copy of whatever in hand
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
            altarBlockEntity.setItemStack(ItemStack.EMPTY, level);
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }


    @Override
    protected void onRemove(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean movedByPiston) {
        if(level.getBlockEntity(pos) instanceof AltarBlockEntity altarBlockEntity){
            altarBlockEntity.dropContents(level, pos);
            level.updateNeighborsAt(pos, this);
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }
}
