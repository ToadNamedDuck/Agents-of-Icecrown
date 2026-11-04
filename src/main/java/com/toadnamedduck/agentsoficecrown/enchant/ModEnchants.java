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
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.ApplyMobEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentAttributeEffect;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

import java.util.List;
import java.util.Optional;

public class ModEnchants {
    public static final ResourceKey<Enchantment> RAZORICE = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "razorice"));
    public static final ResourceKey<Enchantment> FALLEN_CRUSADER = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(Constants.MODID,"fallen_crusader"));
    public static final ResourceKey<Enchantment> STONESKIN_GARGOYLE = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "stoneskin_gargoyle"));

    public static void bootstrap(BootstrapContext<Enchantment> context){
        HolderGetter<Item> items = context.lookup(Registries.ITEM);
        HolderGetter<Enchantment> enchants = context.lookup(Registries.ENCHANTMENT);

        context.register(RAZORICE,
                Enchantment.enchantment(new Enchantment.EnchantmentDefinition(
                                items.getOrThrow(ModTags.ITEMS.MELEE_WEAPON),
                                Optional.empty(),
                                1,
                                1,
                                new Enchantment.Cost(0,0),
                                new Enchantment.Cost(0,0),
                                1,
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
                        .withEffect(EnchantmentEffectComponents.POST_ATTACK,
                                EnchantmentTarget.ATTACKER,
                                EnchantmentTarget.VICTIM,
                                new FreezeEffect(LevelBasedValue.constant(300)),
                                LootItemRandomChanceCondition.randomChance(0.45f)
                                )
                        .exclusiveWith(enchants.getOrThrow(ModTags.ENCHANTMENTS.RUNEFORGE_ENCHANTS))
                        .build(RAZORICE.location()));

        context.register(FALLEN_CRUSADER,
                Enchantment.enchantment(new Enchantment.EnchantmentDefinition(
                        items.getOrThrow(ModTags.ITEMS.MELEE_WEAPON),
                        Optional.empty(),
                        1,
                        1,
                        new Enchantment.Cost(0,0),
                        new Enchantment.Cost(0,0),
                        1,
                        List.of(EquipmentSlotGroup.MAINHAND)
                ))
                        .withEffect(
                                EnchantmentEffectComponents.POST_ATTACK,
                                EnchantmentTarget.ATTACKER,
                                EnchantmentTarget.ATTACKER,
                                new ApplyMobEffect(HolderSet.direct(MobEffects.DAMAGE_BOOST), LevelBasedValue.constant(15), LevelBasedValue.constant(15), LevelBasedValue.perLevel(1), LevelBasedValue.perLevel(1)),
                                LootItemRandomChanceCondition.randomChance(0.05f))
                        .exclusiveWith(enchants.getOrThrow(ModTags.ENCHANTMENTS.RUNEFORGE_ENCHANTS))
                        .build(FALLEN_CRUSADER.location()));

        context.register(STONESKIN_GARGOYLE,
                Enchantment.enchantment(
                        new Enchantment.EnchantmentDefinition(
                                items.getOrThrow(ModTags.ITEMS.MELEE_WEAPON),
                                Optional.empty(),
                                1,
                                1,
                                new Enchantment.Cost(0, 0),
                                new Enchantment.Cost(0, 0),
                                1,
                                List.of(EquipmentSlotGroup.MAINHAND)
                        ))
                        .withEffect(
                                EnchantmentEffectComponents.ATTRIBUTES,
                                new EnchantmentAttributeEffect(
                                        ResourceLocation.fromNamespaceAndPath(Constants.MODID, "stoneskin_gargoyle_armor"),
                                        Attributes.ARMOR,
                                        LevelBasedValue.constant(4),
                                        AttributeModifier.Operation.ADD_VALUE
                                ))
                        .withEffect(
                                EnchantmentEffectComponents.ATTRIBUTES,
                                new EnchantmentAttributeEffect(
                                        ResourceLocation.fromNamespaceAndPath(Constants.MODID, "stoneskin_gargoyle_max_health"),
                                        Attributes.MAX_HEALTH,
                                        LevelBasedValue.constant(4),
                                        AttributeModifier.Operation.ADD_VALUE
                                ))
                        .withEffect(
                                EnchantmentEffectComponents.ATTRIBUTES,
                                new EnchantmentAttributeEffect(
                                        ResourceLocation.fromNamespaceAndPath(Constants.MODID, "stoneskin_gargoyle_armor_toughness"),
                                        Attributes.ARMOR_TOUGHNESS,
                                        LevelBasedValue.constant(2),
                                        AttributeModifier.Operation.ADD_VALUE
                                ))
                        .exclusiveWith(enchants.getOrThrow(ModTags.ENCHANTMENTS.RUNEFORGE_ENCHANTS))
                        .build(STONESKIN_GARGOYLE.location()));
    }
}
