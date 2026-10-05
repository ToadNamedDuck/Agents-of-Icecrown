package com.toadnamedduck.agentsoficecrown.item;


import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ModArmorMaterials {

    public static DeferredRegister<ArmorMaterial> MOD_ARMORS = DeferredRegister.create(Registries.ARMOR_MATERIAL, Constants.MODID);

    //Set up armor values of each piece per type
    static Map<ArmorItem.Type, Integer>  titaniumDefenseMap = new EnumMap<>(Map.of(
            ArmorItem.Type.HELMET, 2,//2 6 5 2
            ArmorItem.Type.CHESTPLATE, 6,
            ArmorItem.Type.LEGGINGS, 5,
            ArmorItem.Type.BOOTS, 2
            )
    );

    //Layer setup for each armor material
    static List<ArmorMaterial.Layer> titaniumLayers = List.of(new ArmorMaterial.Layer(
            ResourceLocation.fromNamespaceAndPath(Constants.MODID, "titanium"),
            "",
            false
    ));

    //Armor material setup


    public static Holder<ArmorMaterial> titanium = MOD_ARMORS.register("titanium", () ->
            new ArmorMaterial(titaniumDefenseMap, 9, SoundEvents.ARMOR_EQUIP_IRON, () -> Ingredient.of(ModTags.ITEMS.TITANIUM_INGOT), titaniumLayers,0.0f, 0.0f)
    );

    public static void register(IEventBus eventBus){
        MOD_ARMORS.register(eventBus);
    }
}
