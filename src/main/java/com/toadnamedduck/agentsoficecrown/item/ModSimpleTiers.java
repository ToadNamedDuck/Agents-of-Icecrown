package com.toadnamedduck.agentsoficecrown.item;

import com.toadnamedduck.agentsoficecrown.ModTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModSimpleTiers {

    public static final SimpleTier titaniumTier = new SimpleTier(ModTags.BLOCKS.INCORRECT_FOR_TITANIUM_TOOL, 1000, 6.0f, 2.0f, 14, () -> Ingredient.of(ModItems.TITANIUM_INGOT));
}
