package com.toadnamedduck.agentsoficecrown.menu;

import com.toadnamedduck.agentsoficecrown.ModTags;
import com.toadnamedduck.agentsoficecrown.block.ModBlocks;
import com.toadnamedduck.agentsoficecrown.item.ModItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.util.FakePlayer;
import org.jetbrains.annotations.NotNull;

import static com.toadnamedduck.agentsoficecrown.datacomponent.ModDataComponentHelpers.SOUL_BINDING.awaken;
import static com.toadnamedduck.agentsoficecrown.datacomponent.ModDataComponentHelpers.SOUL_BINDING.findBinding;

public class RuneforgeMenu extends AbstractContainerMenu {
    private final Container container = new SimpleContainer(2);
    private final ContainerLevelAccess access;
    public static final int WEAPON_SLOT = 0;
    public static final int SOUL_SLOT = 1;
    public static final int INV_START = 2;
    public static final int HOTBAR_START = 27 + INV_START;
    public static final int HOTBAR_END_EXCL = 36 + INV_START;
    public static final int AWAKEN_BUTTON = 0;
    public static final int REPAIR_BUTTON = 1;
    public static final int RUNEFORGES_START = 2;
    public static final int SOUL_COST = 1;

    public RuneforgeMenu(int id, Inventory playerInv){
        this(id, playerInv, ContainerLevelAccess.NULL);
    }

    public RuneforgeMenu(int id, Inventory playerInv, ContainerLevelAccess access){
        super(ModMenuTypes.RUNEFORGE_MENU_TYPE.get(), id);
        this.access = access;

        //As always, first 2 slots are for the machine. We can inline the rules since we don't have a block entity for the runeforge and we don't need a full itemhandler
        this.addSlot(new Slot(container, WEAPON_SLOT, 80, 17){
            @Override public boolean mayPlace(@NotNull ItemStack stack){return stack.is(ModTags.ITEMS.MELEE_WEAPON);}
        });
        this.addSlot(new Slot(container, SOUL_SLOT, 80, 53){
            @Override public boolean mayPlace(@NotNull ItemStack stack){return stack.is(ModItems.COALESCED_SOULS);}
        });

        //Pretty much a copy and paste from the Soul condenser menu to get the inventory and hotbar of the player
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 9; c++){
                this.addSlot(new Slot(playerInv, c + 9*r + 9, 8 + c*18, 84 + r*18));
            }
        }
        for(int h = 0; h <9; h++){
            this.addSlot(new Slot(playerInv, h, 8+h*18, 142));
        }
    }

    public boolean canAwaken(){
        ItemStack weapon = getSlot(WEAPON_SLOT).getItem();
        ItemStack soul = getSlot(SOUL_SLOT).getItem();

        if(weapon.isEmpty()) return false;
        return findBinding(weapon) == null && soul.getCount() >= SOUL_COST;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        Slot slot = this.slots.get(index);
        if(!slot.hasItem()) return ItemStack.EMPTY;

        ItemStack slotItem = slot.getItem();
        ItemStack copy = slotItem.copy();

        if(index < INV_START){
            if(!moveItemStackTo(slotItem, INV_START, HOTBAR_END_EXCL, true)) return ItemStack.EMPTY;
        }
        else{
            if(!moveItemStackTo(slotItem, WEAPON_SLOT, INV_START, false)) {
                if(index < HOTBAR_START){
                    if(!moveItemStackTo(slotItem, HOTBAR_START, HOTBAR_END_EXCL, false)) return ItemStack.EMPTY;
                }
                else{
                    if(!moveItemStackTo(slotItem, INV_START, HOTBAR_START, false)) return ItemStack.EMPTY;
                }
            }
        }

        if(slotItem.isEmpty()) slot.setByPlayer(ItemStack.EMPTY);
        else slot.setChanged();

        if(slotItem.getCount() == copy.getCount()) return ItemStack.EMPTY;
        slot.onTake(player, slotItem);
        return copy;
    }

    @Override
    public boolean stillValid(@NotNull Player player){
        return stillValid(access, player, ModBlocks.RUNEFORGE.get());
    }

    @Override
    public void removed(@NotNull Player player){
        super.removed(player);
        access.execute(((level, pos) -> clearContainer(player, container)));
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        if(!(player instanceof ServerPlayer) || player instanceof FakePlayer) return false;

        if(id >= RUNEFORGES_START) return false;//NYI

        return switch(id){
            case AWAKEN_BUTTON -> awakenWeapon(player);
            default -> false;
        };
    }

    private boolean awakenWeapon(Player player){
        if(!canAwaken()) return false;
        if(!awaken(this.slots.get(WEAPON_SLOT).getItem(), player)) return false;
        this.slots.get(SOUL_SLOT).remove(SOUL_COST);
        return true;
    }
}
