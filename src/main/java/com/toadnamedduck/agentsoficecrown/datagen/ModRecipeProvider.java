package com.toadnamedduck.agentsoficecrown.datagen;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.block.ModBlocks;
import com.toadnamedduck.agentsoficecrown.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider (PackOutput out, CompletableFuture<HolderLookup.Provider> lookupProvider){
        super(out, lookupProvider);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output){
        //Ore smelting (furnace, blast furnace for now. Maybe additional machine for Refined Saronite later?)
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_SARONITE_ORE.get()), RecipeCategory.MISC, ModItems.SARONITE_INGOT.get(), 1.0f, 200)
                .group("saronite")
                .unlockedBy("has_raw_saronite", has(ModItems.RAW_SARONITE_ORE.get()))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "saronite_ingot_from_smelting"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.RAW_SARONITE_ORE.get()), RecipeCategory.MISC, ModItems.SARONITE_INGOT.get(), 1.0f, 100)
                .group("saronite")
                .unlockedBy("has_raw_saronite", has(ModItems.RAW_SARONITE_ORE.get()))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "saronite_ingot_from_blasting"));

        //Ingot -> Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_OF_SARONITE.get())
                .define('X', ModItems.SARONITE_INGOT.get())
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_ingot", has(ModItems.SARONITE_INGOT.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_OF_REFINED_SARONITE.get())
                .define('X', ModItems.REFINED_SARONITE_INGOT.get())
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_ingot", has(ModItems.REFINED_SARONITE_INGOT.get()))
                .save(output);

        //Block -> Ingot
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SARONITE_INGOT.get(), 9)
                .requires(ModBlocks.BLOCK_OF_SARONITE.get())
                .unlockedBy("has_block", has(ModBlocks.BLOCK_OF_SARONITE.get()))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "saronite_ingot_from_block"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.REFINED_SARONITE_INGOT.get(), 9)
                .requires(ModBlocks.BLOCK_OF_REFINED_SARONITE.get())
                .unlockedBy("has_block", has(ModBlocks.BLOCK_OF_REFINED_SARONITE.get()))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "refined_saronite_ingot_from_block"));



    }
}
