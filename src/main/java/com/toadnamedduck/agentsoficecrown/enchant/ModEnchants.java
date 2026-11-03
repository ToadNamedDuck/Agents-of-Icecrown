package com.toadnamedduck.agentsoficecrown.enchant;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.ModTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.ApplyMobEffect;

import java.util.List;
import java.util.Optional;

public class ModEnchants {
    public static final ResourceKey<Enchantment> RAZORICE = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "razorice"));

    public static void bootstrap(BootstrapContext<Enchantment> context){
        HolderGetter<Item> items = context.lookup(Registries.ITEM);

        context.register(RAZORICE,
                Enchantment.enchantment(new Enchantment.EnchantmentDefinition(
                                items.getOrThrow(ModTags.ITEMS.MELEE_WEAPON),
                                Optional.empty(),
                                1,
                                1,
                                new Enchantment.Cost(1,0),
                                new Enchantment.Cost(1,0),1,
                                List.of(EquipmentSlotGroup.MAINHAND)
                                ))
                        .withEffect(EnchantmentEffectComponents.POST_ATTACK,
                                EnchantmentTarget.ATTACKER,
                                EnchantmentTarget.VICTIM,
                                new ApplyMobEffect(
                                        HolderSet.direct(MobEffects.MOVEMENT_SLOWDOWN),
                                        LevelBasedValue.constant(3), LevelBasedValue.constant(3),//Base effect 3 seconds
                                        LevelBasedValue.constant(0), LevelBasedValue.constant(0)//No level scaling
                                ))
                        .build(RAZORICE.location()));
    }
}
