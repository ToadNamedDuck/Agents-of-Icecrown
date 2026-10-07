package com.toadnamedduck.agentsoficecrown.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class AltarBlockEntity extends BlockEntity {
    public AltarBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.ALTAR_BLOCK_ENTITY.get(), pos, blockState);
    }
    private ItemStack heldItem = ItemStack.EMPTY;

    public ItemStack getItemStack(){
        return this.heldItem;
    }

    public void setItemStack(ItemStack itemStack){
            heldItem = itemStack;
            this.setChanged();

    }
    @Override
    protected void saveAdditional(@NotNull CompoundTag compoundTag, HolderLookup.@NotNull Provider registries){
        super.saveAdditional(compoundTag, registries);
        if(!this.heldItem.isEmpty()){
            compoundTag.put("Item", this.heldItem.save(registries));
        }
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag compoundTag, HolderLookup.@NotNull Provider registries){
        super.loadAdditional(compoundTag, registries);
        if(compoundTag.contains("Item")){
            this.heldItem = ItemStack.parseOptional(registries, compoundTag.getCompound("Item"));
        }
        else{
            this.heldItem = ItemStack.EMPTY;
        }
    }
}
