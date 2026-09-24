package com.toadnamedduck.agentsoficecrown.datagen;

import com.toadnamedduck.agentsoficecrown.block.ModBlocks;
import com.toadnamedduck.agentsoficecrown.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
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
        oreSmelting(output, List.of(ModItems.RAW_SARONITE_ORE.get()), RecipeCategory.MISC, ModItems.SARONITE_INGOT.get(), 1.0f, 200, "saronite");
        oreBlasting(output, List.of(ModItems.RAW_SARONITE_ORE.get()), RecipeCategory.MISC, ModItems.SARONITE_INGOT.get(), 1.0f, 100, "saronite");

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
                .save(output, "saronite_ingot_from_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.REFINED_SARONITE_INGOT.get(), 9)
                .requires(ModBlocks.BLOCK_OF_REFINED_SARONITE.get())
                .unlockedBy("has_block", has(ModBlocks.BLOCK_OF_REFINED_SARONITE.get()))
                .save(output, "refined_saronite_ingot_from_block");



    }
}
