package com.toadnamedduck.agentsoficecrown;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.Set;

public class ModTranslatableComponents {
    public static final MutableComponent INERT = Component.translatable("tooltip.agentsoficecrown.inert");
    public static final MutableComponent SOULS = Component.translatable("tooltip.agentsoficecrown.souls");
    public static final MutableComponent SOULBINDING_OWNER_MISMATCH = Component.translatable("tooltip.agentsoficecrown.soulbinding_owner_mismatch");
    public static final MutableComponent SOULBINDING_DURABILITY_SPENT = Component.translatable("tooltip.agentsoficecrown.soulbinding_durability_spent");
    public static final MutableComponent MOD_CREATIVE_TAB = Component.translatable("creativetab.agentsoficecrown.icecrown_tab");
    public static final MutableComponent RITUAL_IN_PROGRESS = Component.translatable("altarritual.agentsoficecrown.in_progress");
    public static final MutableComponent RITUAL_ALTAR_EMPTY = Component.translatable("altarritual.agentsoficecrown.altar_empty");
    public static final MutableComponent RITUAL_WRONG_ITEM = Component.translatable("altarritual.agentsoficecrown.altar_wrong_item");
    public static final MutableComponent RITUAL_ALREADY_BOUND = Component.translatable("altarritual.agentsoficecrown.altar_already_bound");
    public static final MutableComponent RITUAL_HEALTH_TOO_LOW = Component.translatable("altarritual.agentsoficecrown.health_too_low");
    public static final MutableComponent RITUAL_NOT_PREPARED = Component.translatable("altarritual.agentsoficecrown.ritual_not_prepared");
    public static final MutableComponent RITUAL_CANCELLED = Component.translatable("altarritual.agentsoficecrown.ritual_cancelled");
    public static final MutableComponent RITUAL_SUCCESS = Component.translatable("altarritual.agentsoficecrown.ritual_successful");
    public static final MutableComponent SOUL_CONDENSER_TITLE = Component.translatable("gui.agentsoficecrown.soul_cond_title");
    public static final MutableComponent COALESCED_SOULS_TOOLTIP = Component.translatable("tooltip.agentsoficecrown.coalesced_souls_tooltip");
    public static final MutableComponent COALESCED_SOULS_NO_BINDING = Component.translatable("item.agentsoficecrown.coalesced_souls_no_binding");
    public static final MutableComponent RUNEFORGE_TITLE = Component.translatable("gui.agentsoficecrown.runeforge_title");
    public static final MutableComponent RUNEFORGE_AWAKEN_BUTTON = Component.translatable("gui.agentsoficecrown.runeforge_awaken_button");
    public static final MutableComponent RUNEFORGE_REPAIR_BUTTON = Component.translatable("gui.agentsoficecrown.runeforge_repair_button");
    public static final MutableComponent RUNEFORGE_AWAKEN_TOOLTIP = Component.translatable("gui.agentsoficecrown.awaken_button_tooltip");
    public static final MutableComponent RUNEFORGE_REPAIR_TOOLTIP = Component.translatable("gui.agentsoficecrown.repair_button_tooltip");
    public static final MutableComponent ENCHANT_RAZORICE = Component.translatable("enchantment.agentsoficecrown.razorice");
    public static final MutableComponent ENCHANT_FALLEN_CRUSADER = Component.translatable("enchantment.agentsoficecrown.fallen_crusader");
    public static final MutableComponent ENCHANTMENT_STONESKIN_GARGOYLE = Component.translatable("enchantment.agentsoficecrown.stoneskin_gargoyle");
    public static final MutableComponent SOULBINDING_MAX_BONUS = Component.translatable("config.agentsoficecrown.soulbinding_max_bonus");
    public static final MutableComponent SOULBINDING_EFFECTIVE_SOUL_CAP = Component.translatable("config.agentsoficecrown.soulbinding_effective_soul_cap");
    public static final MutableComponent SOULBINDING_DEBUFF_HEALTH_PENALTY = Component.translatable("config.agentsoficecrown.soulbinding_health_penalty");
    public static final MutableComponent COALESCED_SOULS_SOUL_VALUE = Component.translatable("config.agentsoficecrown.coalesced_souls_value");
    public static final MutableComponent CONFIG_SOULBINDING = Component.translatable("agentsoficecrown.configuration.soulbinding");
    public static final MutableComponent CONFIG_SOULS = Component.translatable("agentsoficecrown.configuration.souls");
    public static final MutableComponent SARONITE_SET_TOOLTIP = Component.translatable("tooltip.agentsoficecrown.saronite_set");
    public static final MutableComponent SARONITE_SET_TOOLTIP_INFO = Component.translatable("tooltip.agentsoficecrown.saronite_set_info");

    public static final Set<MutableComponent> MOD_TRANSLATABLE = Set.of(INERT, SOULS, SOULBINDING_OWNER_MISMATCH, SOULBINDING_DURABILITY_SPENT, MOD_CREATIVE_TAB, RITUAL_IN_PROGRESS,
            RITUAL_ALTAR_EMPTY, RITUAL_WRONG_ITEM, RITUAL_ALREADY_BOUND, RITUAL_HEALTH_TOO_LOW, RITUAL_NOT_PREPARED, RITUAL_CANCELLED, RITUAL_SUCCESS, SOUL_CONDENSER_TITLE, COALESCED_SOULS_TOOLTIP, COALESCED_SOULS_NO_BINDING,
            RUNEFORGE_TITLE, RUNEFORGE_AWAKEN_BUTTON, RUNEFORGE_AWAKEN_TOOLTIP, RUNEFORGE_REPAIR_TOOLTIP, ENCHANT_RAZORICE, ENCHANT_FALLEN_CRUSADER, ENCHANTMENT_STONESKIN_GARGOYLE, SOULBINDING_MAX_BONUS,
            SOULBINDING_EFFECTIVE_SOUL_CAP, SOULBINDING_DEBUFF_HEALTH_PENALTY, COALESCED_SOULS_SOUL_VALUE, CONFIG_SOULBINDING, CONFIG_SOULS, SARONITE_SET_TOOLTIP, SARONITE_SET_TOOLTIP_INFO);
}
