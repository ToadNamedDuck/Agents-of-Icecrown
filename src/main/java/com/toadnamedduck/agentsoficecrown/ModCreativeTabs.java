package com.toadnamedduck.agentsoficecrown;

import com.toadnamedduck.agentsoficecrown.item.ModBlockItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Constants.MODID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TEST_TAB = CREATIVE_MODE_TABS.register("test_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab.agentsoficecrown.items"))
                    .icon(() -> new ItemStack(ModBlockItems.TEST_STONE_ITEM.get()))
                    .displayItems((params, output) ->
                            output.accept(ModBlockItems.TEST_STONE_ITEM))
                    .build()
            );

    public static void register(IEventBus modEventBus){
        CREATIVE_MODE_TABS.register(modEventBus);
    }
}
