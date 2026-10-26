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

    public static final Set<MutableComponent> MOD_TRANSLATABLE = Set.of(INERT, SOULS, SOULBINDING_OWNER_MISMATCH, SOULBINDING_DURABILITY_SPENT, MOD_CREATIVE_TAB, RITUAL_IN_PROGRESS,
            RITUAL_ALTAR_EMPTY, RITUAL_WRONG_ITEM, RITUAL_ALREADY_BOUND, RITUAL_HEALTH_TOO_LOW, RITUAL_NOT_PREPARED, RITUAL_CANCELLED, RITUAL_SUCCESS, SOUL_CONDENSER_TITLE);
}
