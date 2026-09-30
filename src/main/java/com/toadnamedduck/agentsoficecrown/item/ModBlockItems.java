package com.toadnamedduck.agentsoficecrown.item;

import com.toadnamedduck.agentsoficecrown.block.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.toadnamedduck.agentsoficecrown.Constants;

import static com.toadnamedduck.agentsoficecrown.block.ModBlocks.*;

public class ModBlockItems {
    public static DeferredRegister.Items BLOCK_ITEMS = DeferredRegister.createItems(Constants.MODID);

    public static DeferredItem<BlockItem> LICHSTONE = BLOCK_ITEMS.registerSimpleBlockItem(
            "lichstone",
            ModBlocks.LICHSTONE,
            new Item.Properties()
            );

    public static DeferredItem<BlockItem> BLOCK_OF_SARONITE = BLOCK_ITEMS.registerSimpleBlockItem(
            "block_of_saronite",
            ModBlocks.BLOCK_OF_SARONITE,
            new Item.Properties().fireResistant().rarity(Rarity.RARE)
    );

    public static DeferredItem<BlockItem> RUNEFORGE = BLOCK_ITEMS.registerSimpleBlockItem(
            "runeforge",
            ModBlocks.RUNEFORGE,
            new Item.Properties().rarity(Rarity.EPIC)
    );

    public static DeferredItem<BlockItem> BLOCK_OF_TITANIUM = BLOCK_ITEMS.registerSimpleBlockItem(
            "block_of_titanium",
            ModBlocks.BLOCK_OF_TITANIUM,
            new Item.Properties()
    );

    public static DeferredItem<BlockItem> BLOCK_OF_TITANSTEEL = BLOCK_ITEMS.registerSimpleBlockItem(
            "block_of_titansteel",
            ModBlocks.BLOCK_OF_TITANSTEEL,
            new Item.Properties().fireResistant().rarity(Rarity.RARE)
    );

    public static void register(IEventBus modEventBus){
        BLOCK_ITEMS.register(modEventBus);
    }
}
