package com.toadnamedduck.agentsoficecrown.item;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.fluid.ModFluids;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.toadnamedduck.agentsoficecrown.item.ModSimpleTiers.saroniteTier;

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

    public static final DeferredItem<SwordItem> SARONITE_SWORD = MOD_ITEMS.register("saronite_sword", () ->
            new SwordItem(saroniteTier,
                    new Item.Properties()
                            .stacksTo(1)
                            .rarity(Rarity.RARE)
                            .fireResistant()
                            .attributes(SwordItem.createAttributes(saroniteTier, 4.0f, -2.4f))
            ));

    public static final DeferredItem<AxeItem> SARONITE_AXE = MOD_ITEMS.register("saronite_axe", () ->
            new AxeItem(saroniteTier,
                    new Item.Properties()
                            .stacksTo(1)
                            .rarity(Rarity.RARE)
                            .fireResistant()
                            .attributes(AxeItem.createAttributes(saroniteTier, 7.0f, -3.1f))
            ));

    public static final DeferredItem<Item> SARONITE_WING = MOD_ITEMS.register("saronite_wing", () ->
            new Item(
                    new Item.Properties()
                            .stacksTo(64)
                            .rarity(Rarity.RARE)
                            .fireResistant()
            ));

    public static final DeferredItem<CoalescedSouls> COALESCED_SOULS = MOD_ITEMS.register("coalesced_souls", () ->
        new CoalescedSouls(
                new Item.Properties()
                        .stacksTo(64)
        ));

    public static void register(IEventBus modEventBus){
        MOD_ITEMS.register(modEventBus);
    }
}
