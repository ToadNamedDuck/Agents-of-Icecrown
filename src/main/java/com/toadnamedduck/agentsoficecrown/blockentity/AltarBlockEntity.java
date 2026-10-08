package com.toadnamedduck.agentsoficecrown.blockentity;

import com.toadnamedduck.agentsoficecrown.block.AltarBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
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

    public void dropContents(Level level, BlockPos pos){
        if(!level.isClientSide() && !this.getItemStack().isEmpty()){
            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), this.heldItem);
        }
    }

    public void setItemStack(ItemStack itemStack, Level level){
            heldItem = itemStack;
            this.setChanged();
            if(!level.isClientSide()){
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), AltarBlock.UPDATE_CLIENTS);
            }
    }
    @Override
    protected void saveAdditional(@NotNull CompoundTag compoundTag, HolderLookup.@NotNull Provider registries){
        super.saveAdditional(compoundTag, registries);
        compoundTag.put("Item", this.heldItem.saveOptional(registries));
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

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider registries) {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag, registries);
        return tag;
    }

    @Override
    public void handleUpdateTag(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.handleUpdateTag(tag, registries);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection connection, ClientboundBlockEntityDataPacket packet, HolderLookup.Provider registries) {
        //Super forwards to loadAdditional
        super.onDataPacket(connection, packet, registries);
    }
}
