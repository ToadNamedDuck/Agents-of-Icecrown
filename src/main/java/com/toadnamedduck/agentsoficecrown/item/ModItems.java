package com.toadnamedduck.agentsoficecrown.item;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.fluid.ModFluids;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.toadnamedduck.agentsoficecrown.item.ModArmorMaterials.titanium;
import static com.toadnamedduck.agentsoficecrown.item.ModSimpleTiers.titaniumTier;

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

    public static final DeferredItem<PickaxeItem> TITANIUM_PICKAXE = MOD_ITEMS.register("titanium_pickaxe", () ->
            new PickaxeItem(titaniumTier,
                    new Item.Properties()
                            .stacksTo(1)
                            .attributes(DiggerItem.createAttributes(titaniumTier, 1.0f, -2.8f))
            ));

    public static final DeferredItem<AxeItem> TITANIUM_AXE = MOD_ITEMS.register("titanium_axe", () ->
            new AxeItem(titaniumTier,
                    new Item.Properties()
                            .stacksTo(1)
                            .attributes(DiggerItem.createAttributes(titaniumTier, 6.0f, -3.1f))
            ));

    public static final DeferredItem<SwordItem> TITANIUM_SWORD = MOD_ITEMS.register("titanium_sword", () ->
            new SwordItem(titaniumTier,
                    new Item.Properties()
                            .stacksTo(1)
                            .attributes(SwordItem.createAttributes(titaniumTier, 3.0f, -2.4f))
            ));

    public static final DeferredItem<ShovelItem> TITANIUM_SHOVEL = MOD_ITEMS.register("titanium_shovel", () ->
            new ShovelItem(titaniumTier,
                    new Item.Properties()
                            .stacksTo(1)
                            .attributes(DiggerItem.createAttributes(titaniumTier, 1.5f, -3.0f))
            ));

    public static final DeferredItem<HoeItem> TITANIUM_HOE = MOD_ITEMS.register("titanium_hoe", () ->
            new HoeItem(titaniumTier,
                    new Item.Properties()
                            .stacksTo(1)
                            .attributes(DiggerItem.createAttributes(titaniumTier, -2.0f,-1.0f))
            ));

    public static final DeferredItem<ArmorItem> TITANIUM_HELMET = MOD_ITEMS.register("titanium_helmet", () ->
            new ArmorItem(titanium, ArmorItem.Type.HELMET,
                    new Item.Properties()
                            .durability(ArmorItem.Type.HELMET.getDurability(21))
            ));

    public static final DeferredItem<ArmorItem> TITANIUM_CHESTPLATE = MOD_ITEMS.register("titanium_chestplate", () ->
            new ArmorItem(titanium, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties()
                            .durability(ArmorItem.Type.CHESTPLATE.getDurability(21))
            ));

    public static final DeferredItem<ArmorItem> TITANIUM_LEGGINGS = MOD_ITEMS.register("titanium_leggings", () ->
            new ArmorItem(titanium, ArmorItem.Type.LEGGINGS,
                    new Item.Properties()
                        .durability(ArmorItem.Type.LEGGINGS.getDurability(21))
            ));

    public static final DeferredItem<ArmorItem> TITANIUM_BOOTS = MOD_ITEMS.register("titanium_boots", () ->
            new ArmorItem(titanium, ArmorItem.Type.BOOTS,
                    new Item.Properties()
                            .durability(ArmorItem.Type.BOOTS.getDurability(21))
            ));

    public static void register(IEventBus modEventBus){
        MOD_ITEMS.register(modEventBus);
    }
}
