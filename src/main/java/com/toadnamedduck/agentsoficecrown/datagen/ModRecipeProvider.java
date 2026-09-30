package com.toadnamedduck.agentsoficecrown.datagen;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.block.ModBlocks;
import com.toadnamedduck.agentsoficecrown.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.jarjar.metadata.ContainedJarIdentifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;
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
                .smelting(Ingredient.of(ModItems.RAW_TITANIUM_ORE.get()), RecipeCategory.MISC, ModItems.TITANIUM_INGOT.get(), 1.0f, 200)
                .group("titanium")
                .unlockedBy("has_raw_titanium", has(ModItems.RAW_TITANIUM_ORE.get()))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "titanium_ingot_from_smelting"));

        SimpleCookingRecipeBuilder
                .blasting(Ingredient.of(ModItems.RAW_TITANIUM_ORE.get()),RecipeCategory.MISC, ModItems.TITANIUM_INGOT.get(), 1.0f, 100)
                .group("titanium")
                .unlockedBy("has_raw_titanium", has(ModItems.RAW_TITANIUM_ORE.get()))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "titanium_ingot_from_blasting"));


        //Ingot -> Block
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, ModBlocks.BLOCK_OF_SARONITE.get())
                .define('X', ModItems.SARONITE_INGOT.get())
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_ingot", has(ModItems.SARONITE_INGOT.get()))
                .save(output);

        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, ModBlocks.BLOCK_OF_TITANIUM.get())
                .define('X', ModItems.TITANIUM_INGOT.get())
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_ingot", has(ModItems.TITANIUM_INGOT.get()))
                .save(output);

        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, ModBlocks.BLOCK_OF_TITANSTEEL.get())
                .define('X', ModItems.TITANSTEEL_INGOT.get())
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_ingot", has(ModItems.TITANSTEEL_INGOT.get()))
                .save(output);

        //Nugget -> Ingot
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, ModItems.SARONITE_INGOT.get())
                .define('X', ModItems.SARONITE_NUGGET.get())
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_ingot", has(ModItems.SARONITE_INGOT.get()))
                .save(output);

        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, ModItems.TITANIUM_INGOT.get())
                .define('X', ModItems.TITANIUM_NUGGET.get())
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_ingot", has(ModItems.TITANIUM_INGOT.get()))
                .save(output);

        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, ModItems.TITANSTEEL_INGOT.get())
                .define('X', ModItems.TITANSTEEL_NUGGET.get())
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_ingot", has(ModItems.TITANSTEEL_INGOT.get()))
                .save(output);

        //Block -> Ingot
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, ModItems.SARONITE_INGOT.get(), 9)
                .requires(ModBlocks.BLOCK_OF_SARONITE.get())
                .unlockedBy("has_block", has(ModBlocks.BLOCK_OF_SARONITE.get()))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "saronite_ingot_from_block"));

        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, ModItems.TITANIUM_INGOT.get(), 9)
                .requires(ModBlocks.BLOCK_OF_TITANIUM.get())
                .unlockedBy("has_block", has(ModBlocks.BLOCK_OF_TITANIUM.get()))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "titanium_ingot_from_block"));

        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, ModItems.TITANSTEEL_INGOT.get(), 9)
                .requires(ModBlocks.BLOCK_OF_TITANSTEEL.get())
                .unlockedBy("has_block", has(ModBlocks.BLOCK_OF_TITANSTEEL.get()))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "titansteel_ingot_from_block"));

        //Ingot -> Nugget
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, ModItems.SARONITE_NUGGET.get(), 9)
                .requires(ModItems.SARONITE_INGOT.get())
                .unlockedBy("has_ingot", has(ModItems.SARONITE_INGOT.get()))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "saronite_nugget_from_ingot"));

        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, ModItems.TITANIUM_NUGGET.get(), 9)
                .requires(ModItems.TITANIUM_INGOT.get())
                .unlockedBy("has_ingot", has(ModItems.TITANIUM_INGOT.get()))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "titanium_nugget_from_ingot"));

        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, ModItems.TITANSTEEL_NUGGET.get(), 9)
                .requires(ModItems.TITANSTEEL_INGOT.get())
                .unlockedBy("has_ingot", has(ModItems.TITANSTEEL_INGOT.get()))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "titansteel_nugget_from_ingot"));

    }
}
