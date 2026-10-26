package com.toadnamedduck.agentsoficecrown.blockentity;

import com.toadnamedduck.agentsoficecrown.datacomponent.ModDataComponentHelpers;
import com.toadnamedduck.agentsoficecrown.datacomponent.SoulBinding;
import com.toadnamedduck.agentsoficecrown.item.ModItems;
import com.toadnamedduck.agentsoficecrown.menu.SoulCondenserItemHandler;
import com.toadnamedduck.agentsoficecrown.menu.SoulCondenserMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import static com.toadnamedduck.agentsoficecrown.ModTranslatableComponents.SOUL_CONDENSER_TITLE;
import static com.toadnamedduck.agentsoficecrown.datacomponent.ModDataComponentHelpers.SOUL_BINDING.findBinding;
import static com.toadnamedduck.agentsoficecrown.menu.SoulCondenserItemHandler.INPUT;
import static com.toadnamedduck.agentsoficecrown.menu.SoulCondenserItemHandler.OUTPUT;

public class SoulCondenserBlockEntity extends BlockEntity implements MenuProvider {
    private int progress = 0;
    private static final int MAX_PROGRESS = 200;
    private final ContainerData containerData = new ContainerData() {
        @Override
        public int get(int i) {
            if(i == 0){
                return progress;
            }
            return MAX_PROGRESS;
        }

        @Override
        public void set(int i, int i1) {
            if(i == 0){
                progress = i1;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    private final SoulCondenserItemHandler inventory = new SoulCondenserItemHandler(this::setChanged);
    private static final int SOULS_PER_ITEM = 50;

    public SoulCondenserBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.SOUL_CONDENSER_ENTITY.get(), pos, blockState);
    }

    private boolean canAcceptOutput(){
        ItemStack out = inventory.getStackInSlot(OUTPUT);
        if(out.isEmpty()) return true;
        else return out.is(ModItems.COALESCED_SOULS.get()) && out.getCount() < Math.min(out.getMaxStackSize(), inventory.getSlotLimit(OUTPUT));
    }

    @Override
    public @NotNull Component getDisplayName(){
        return SOUL_CONDENSER_TITLE;
    }

    @Override
    public AbstractContainerMenu createMenu(int containerID, @NotNull Inventory playerInv, @NotNull Player player){
        return new SoulCondenserMenu(containerID, playerInv, inventory, containerData, ContainerLevelAccess.create(this.getLevel(), this.getBlockPos()));
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag compoundTag, HolderLookup.@NotNull Provider registries){
        super.saveAdditional(compoundTag, registries);
        compoundTag.put("Inventory", inventory.serializeNBT(registries));
        compoundTag.putInt("Progress", progress);
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag compoundTag, HolderLookup.@NotNull Provider registries){
        super.loadAdditional(compoundTag, registries);
        inventory.deserializeNBT(registries, compoundTag.getCompound("Inventory"));
        progress = compoundTag.getInt("Progress");
    }

    public static void tick(Level level, BlockPos pos, BlockState state, SoulCondenserBlockEntity entity){
        ItemStack input = entity.inventory.getStackInSlot(INPUT);
        SoulBinding binding = findBinding(input);
        boolean canWork = binding != null && binding.souls() >= SOULS_PER_ITEM && entity.canAcceptOutput();

        if(!canWork){
            if(entity.progress != 0){
                entity.progress = 0;
                entity.setChanged();
            }
            return;
        }

        entity.progress++;
        if(entity.progress >= MAX_PROGRESS){
            ModDataComponentHelpers.SOUL_BINDING.setSouls(input, binding.souls() - SOULS_PER_ITEM);
            entity.inventory.setStackInSlot(INPUT, input);

            if(entity.inventory.getStackInSlot(OUTPUT).isEmpty()){
                entity.inventory.setStackInSlot(OUTPUT, new ItemStack(ModItems.COALESCED_SOULS.get()));
            }
            else{
                entity.inventory.getStackInSlot(OUTPUT).grow(1);
            }
        }
        entity.setChanged();
    }
}
