package com.toadnamedduck.agentsoficecrown.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.toadnamedduck.agentsoficecrown.Constants;

import static com.toadnamedduck.agentsoficecrown.block.ModBlocks.*;

public class ModBlockItems {
    public static DeferredRegister.Items BLOCK_ITEMS = DeferredRegister.createItems(Constants.MODID);

    public static DeferredItem<BlockItem> TEST_STONE_ITEM = BLOCK_ITEMS.registerSimpleBlockItem(
            "test_stone",
            TEST_STONE,
            new Item.Properties()
            );

    public static void register(IEventBus modEventBus){
        BLOCK_ITEMS.register(modEventBus);
    }
}
