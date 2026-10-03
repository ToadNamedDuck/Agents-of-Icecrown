package com.toadnamedduck.agentsoficecrown.item;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.datagen.ModBlocksTagsProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.SimpleTier;

public class ModSimpleTiers {
    //Tools tier tags, since they are block tags, too.
    public static final TagKey<Block> incorrect_for_titanium_tool = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "incorrect_for_titanium_tool"));
    public static final TagKey<Block> incorrect_for_titansteel_tool = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "incorrect_for_titansteel_tool"));

    public static final SimpleTier titaniumTier = new SimpleTier(incorrect_for_titanium_tool, 1000, 6.0f, 2.0f, 14, () -> Ingredient.of(ModItems.TITANIUM_INGOT));
}
