package com.toadnamedduck.agentsoficecrown.datagen;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.ModTags;
import com.toadnamedduck.agentsoficecrown.block.ModBlocks;
import com.toadnamedduck.agentsoficecrown.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider (PackOutput out, CompletableFuture<HolderLookup.Provider> lookupProvider){
        super(out, lookupProvider);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output){
        //Ore smelting (furnace, blast furnace)
        SimpleCookingRecipeBuilder
                .smelting(Ingredient.of(ModItems.RAW_SARONITE_ORE.get()), RecipeCategory.MISC, ModItems.SARONITE_INGOT.get(), 1.0f, 200)
                .group("saronite")
                .unlockedBy("has_raw_saronite", has(ModItems.RAW_SARONITE_ORE.get()))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "saronite_ingot_from_smelting"));
        SimpleCookingRecipeBuilder
                .blasting(Ingredient.of(ModItems.RAW_SARONITE_ORE.get()), RecipeCategory.MISC, ModItems.SARONITE_INGOT.get(), 1.0f, 100)
                .group("saronite")
                .unlockedBy("has_raw_saronite", has(ModItems.RAW_SARONITE_ORE.get()))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "saronite_ingot_from_blasting"));

        SimpleCookingRecipeBuilder
                .smelting(Ingredient.of(ModItems.SARONITE_WING.get()), RecipeCategory.MISC, ModItems.SARONITE_INGOT.get(), 1.0f, 200)
                .group("saronite")
                .unlockedBy("has_saronite_wing", has(ModItems.SARONITE_WING))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "saronite_ingot_from_wing"));


        //Ingot -> Block
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, ModBlocks.BLOCK_OF_SARONITE.get())
                .define('X', ModTags.ITEMS.SARONITE_INGOT)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_saronite_ingot", has(ModTags.ITEMS.SARONITE_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "saronite_block_from_ingot"));

        //Nugget -> Ingot
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, ModItems.SARONITE_INGOT.get())
                .define('X', ModTags.ITEMS.SARONITE_NUGGET)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_saronite_nugget", has(ModTags.ITEMS.SARONITE_NUGGET))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "saronite_ingot_from_nugget"));

        //Block -> Ingot
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, ModItems.SARONITE_INGOT.get(), 9)
                .requires(ModTags.ITEMS.SARONITE_BLOCK)
                .unlockedBy("has_saronite_block", has(ModTags.ITEMS.SARONITE_BLOCK))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "saronite_ingot_from_block"));

        //Ingot -> Nugget
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, ModItems.SARONITE_NUGGET.get(), 9)
                .requires(ModTags.ITEMS.SARONITE_INGOT)
                .unlockedBy("has_saronite_ingot", has(ModTags.ITEMS.SARONITE_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "saronite_nugget_from_ingot"));

        //Tools

        //Weapons

        ShapedRecipeBuilder
                .shaped(RecipeCategory.COMBAT, ModItems.SARONITE_SWORD.get())
                .define('X', ModTags.ITEMS.SARONITE_INGOT)
                .define('Y', Items.STICK)
                .define('Z', ModItems.SARONITE_WING.get())
                .pattern(" X ")
                .pattern("ZXZ")
                .pattern(" Y ")
                .unlockedBy("has_saronite_wing", has(ModItems.SARONITE_WING.get()))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "saronite_sword"));

        ShapedRecipeBuilder
                .shaped(RecipeCategory.COMBAT, ModItems.SARONITE_AXE.get())
                .define('X', ModTags.ITEMS.SARONITE_INGOT)
                .define('Y', Items.STICK)
                .define('Z', ModItems.SARONITE_WING.get())
                .pattern("XXZ")
                .pattern("XYZ")
                .pattern(" Y ")
                .unlockedBy("has_saronite_wing", has(ModItems.SARONITE_WING.get()))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "saronite_axe"));

        //Armor

        //Misc Items
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, ModItems.SARONITE_WING.get(), 4)
                .define('X', ModTags.ITEMS.SARONITE_INGOT)
                .pattern("XXX")
                .pattern(" XX")
                .pattern("  X")
                .unlockedBy("has_saronite_ingot", has(ModTags.ITEMS.SARONITE_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "saronite_wing"));

        //Blocks/Machines
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, ModBlocks.SOUL_CONDENSER.get())
                .define('X', ModTags.ITEMS.SARONITE_INGOT)
                .define('Y', Items.SOUL_SAND)
                .define('Z', Tags.Items.STONES)
                .pattern("X X")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy("has_saronite_ingot", has(ModTags.ITEMS.SARONITE_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "soul_condenser"));

        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, ModBlocks.RUNEFORGE.get())
                .define('X', ModTags.ITEMS.SARONITE_INGOT)
                .define('Y', ModItems.COALESCED_SOULS)
                .define('Z', Items.BONE_BLOCK)
                .pattern("XYX")
                .pattern("XZX")
                .pattern("ZZZ")
                .unlockedBy("has_coalesced_souls", has(ModItems.COALESCED_SOULS.get()))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "runeforge"));

        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, ModBlocks.ALTAR.get())
                .define('X', ModTags.ITEMS.STONES)
                .define('Y', Items.SOUL_SAND)
                .pattern("X X")
                .pattern("XXX")
                .pattern("XYX")
                .unlockedBy("has_soul_sand", has(Items.SOUL_SAND))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "altar"));
    }
}
