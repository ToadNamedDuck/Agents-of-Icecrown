package com.toadnamedduck.agentsoficecrown.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.toadnamedduck.agentsoficecrown.Constants;

import static com.toadnamedduck.agentsoficecrown.block.Blocks.*;

public class BlockItems {
    public static DeferredRegister.Items BLOCKITEMS = DeferredRegister.createItems(Constants.MODID);

    public static DeferredItem<BlockItem> TEST_STONE_ITEM = BLOCKITEMS.registerSimpleBlockItem(
            "test_stone",
            TEST_STONE,
            new Item.Properties()
            );
}
