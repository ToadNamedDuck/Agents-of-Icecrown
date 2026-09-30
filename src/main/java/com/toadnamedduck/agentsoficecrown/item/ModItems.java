package com.toadnamedduck.agentsoficecrown.item;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.fluid.ModFluids;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items MOD_ITEMS = DeferredRegister.createItems(Constants.MODID);

    public static final DeferredItem<BucketItem> LIQUID_SARONITE_BUCKET = MOD_ITEMS.register("liquid_saronite_bucket", () ->
            new BucketItem(ModFluids.LIQUID_SARONITE_SOURCE.get(), new Item.Properties())
            );

    public static final DeferredItem<Item> RAW_SARONITE_ORE = MOD_ITEMS.register("raw_saronite_ore", () ->
            new Item(new Item.Properties()
                    .stacksTo(64)
                    .rarity(Rarity.RARE)
                    .fireResistant()
            ));

    public static final DeferredItem<Item> SARONITE_INGOT = MOD_ITEMS.register("saronite_ingot", () ->
            new Item(new Item.Properties()
                    .stacksTo(64)
                    .rarity(Rarity.RARE)
                    .fireResistant()
            ));

    public static final DeferredItem<Item> SARONITE_NUGGET = MOD_ITEMS.register("saronite_nugget", () ->
            new Item(new Item.Properties()
                    .stacksTo(64)
                    .rarity(Rarity.RARE)
                    .fireResistant()
            ));

    public static final DeferredItem<Item> RAW_TITANIUM_ORE = MOD_ITEMS.register("raw_titanium_ore", () ->
            new Item(new Item.Properties()
                    .stacksTo(64)
            ));

    public static final DeferredItem<Item> TITANIUM_INGOT = MOD_ITEMS.register( "titanium_ingot", () ->
            new Item(new Item.Properties()
                    .stacksTo(64)
            ));

    public static final DeferredItem<Item> TITANIUM_NUGGET = MOD_ITEMS.register("titanium_nugget", () ->
            new Item(new Item.Properties()
                    .stacksTo(64)
            ));

    public static final DeferredItem<Item> TITANSTEEL_INGOT = MOD_ITEMS.register("titansteel_ingot", () ->
            new Item(new Item.Properties()
                    .stacksTo(64)
                    .rarity(Rarity.RARE)
                    .fireResistant()
            ));

    public static final DeferredItem<Item> TITANSTEEL_NUGGET = MOD_ITEMS.register("titansteel_nugget", () ->
            new Item(new Item.Properties()
                    .stacksTo(64)
                    .rarity(Rarity.RARE)
                    .fireResistant()
            ));

    public static void register(IEventBus modEventBus){
        MOD_ITEMS.register(modEventBus);
    }
}
