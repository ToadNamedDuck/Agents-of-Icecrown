package com.toadnamedduck.agentsoficecrown.menu;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import static com.toadnamedduck.agentsoficecrown.datacomponent.ModDataComponentHelpers.SOUL_BINDING.findBinding;

public class SoulCondenserItemHandler extends ItemStackHandler {
    private final Runnable onChanged;

    public SoulCondenserItemHandler(Runnable onChanged){
        super(2);
        this.onChanged = onChanged;
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack item){
        return switch (slot) {
            case 0 -> findBinding(item) != null;
            case 1 -> false;
            default -> false;
        };
    }

    @Override
    protected void onContentsChanged(int slot){
        onChanged.run();
    }
}
