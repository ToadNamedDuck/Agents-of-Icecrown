package com.toadnamedduck.agentsoficecrown.item;

import com.toadnamedduck.agentsoficecrown.ModTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModSimpleTiers {

    public static final SimpleTier saroniteTier = new SimpleTier(ModTags.BLOCKS.INCORRECT_FOR_SARONITE_TOOL, 1000, 8.0f, 0.0f, 0, () -> Ingredient.of(ModTags.ITEMS.SARONITE_INGOT));
}
