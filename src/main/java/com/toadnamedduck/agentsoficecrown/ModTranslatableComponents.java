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
    public static final Set<MutableComponent> MOD_TRANSLATABLE = Set.of(INERT, SOULS, SOULBINDING_OWNER_MISMATCH, SOULBINDING_DURABILITY_SPENT, MOD_CREATIVE_TAB);
}
