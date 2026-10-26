package com.toadnamedduck.agentsoficecrown.menu;

import com.toadnamedduck.agentsoficecrown.block.ModBlocks;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

import static com.toadnamedduck.agentsoficecrown.menu.SoulCondenserItemHandler.INPUT;
import static com.toadnamedduck.agentsoficecrown.menu.SoulCondenserItemHandler.OUTPUT;

public class SoulCondenserMenu extends AbstractContainerMenu {
    private final ContainerData data;
    private final ContainerLevelAccess access;
    private static final int MACHINE_SLOTS = 2;
    private static final int INV_START_INDEX = MACHINE_SLOTS;
    private static final int HOTBAR_START_INDEX = 27 + MACHINE_SLOTS;
    private static final int SLOTS_END_EXCLUSIVE = 36 + MACHINE_SLOTS;



    public SoulCondenserMenu(int id, Inventory playerInv){
        this(id, playerInv, new SoulCondenserItemHandler(() -> {}), new SimpleContainerData(MACHINE_SLOTS), ContainerLevelAccess.NULL);
    }

    public SoulCondenserMenu(int id, Inventory playerInv, IItemHandler itemHandler, ContainerData containerData, ContainerLevelAccess access){
        super(ModMenuTypes.SOUL_CONDENSER_MENU_TYPE.get(), id);
        this.data = containerData;
        this.access = access;


        // I/O have first two indices
        this.addSlot(new SlotItemHandler(itemHandler, INPUT, 80, 17));
        this.addSlot(new SlotItemHandler(itemHandler, OUTPUT, 80, 53));

        //Inventory
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 9; c++){
                this.addSlot(new Slot(playerInv, c + 9*r + 9, 8 + c*18, 84 + r*18));
            }
        }
        //Hotbar
        for(int h = 0; h <9; h++){
            this.addSlot(new Slot(playerInv, h, 8+h*18, 142));
        }


        this.addDataSlots(data);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index){
        Slot slot = this.slots.get(index);
        if(!slot.hasItem()) return ItemStack.EMPTY;

        ItemStack slotItem = slot.getItem();
        ItemStack copy = slotItem.copy();

        if(index < MACHINE_SLOTS){
            //Machine to player inv
            if(!moveItemStackTo(slotItem, INV_START_INDEX, SLOTS_END_EXCLUSIVE, true)){
                return ItemStack.EMPTY;
            }
        }
        //Player inv to machine
        else {
            //Checks the mayplace rule for the first slot, which only accepts items with a SoulBinding data component
            if (!moveItemStackTo(slotItem, INPUT, INPUT + 1, false)) {
                //If it wasn't accepted into the first slot, we can't insert into the output, so we need to keep the bounce between hotbar and inv functionality lol
                if (index < HOTBAR_START_INDEX) {
                    if (!moveItemStackTo(slotItem, HOTBAR_START_INDEX, SLOTS_END_EXCLUSIVE, false))
                        return ItemStack.EMPTY;
                } else {
                    if (!moveItemStackTo(slotItem, INV_START_INDEX, HOTBAR_START_INDEX, false)) return ItemStack.EMPTY;
                }
            }
        }

        if(slotItem.isEmpty()){
            slot.setByPlayer(ItemStack.EMPTY);
        }
        else{
            slot.setChanged();
        }

        if(slotItem.getCount() == copy.getCount()) return ItemStack.EMPTY;
        slot.onTake(player, slotItem);
        return copy;
    }

    @Override
    public boolean stillValid(Player player){
        return stillValid(access, player, ModBlocks.SOUL_CONDENSER.get());
    }

    public int getScaledProgress(int size) {
        int prog = data.get(0), maxProg = data.get(1);
        if(maxProg != 0 && prog != 0) return (prog*size)/maxProg;
        else return 0;
    }
}
