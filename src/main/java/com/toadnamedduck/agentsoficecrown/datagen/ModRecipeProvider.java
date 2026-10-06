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
                .define('X', ModTags.ITEMS.SARONITE_INGOT)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_ingot", has(ModTags.ITEMS.SARONITE_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "saronite_block_from_ingot"));

        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, ModBlocks.BLOCK_OF_TITANIUM.get())
                .define('X', ModTags.ITEMS.TITANIUM_INGOT)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_ingot", has(ModTags.ITEMS.TITANIUM_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "titanium_block_from_ingot"));

        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, ModBlocks.BLOCK_OF_TITANSTEEL.get())
                .define('X', ModTags.ITEMS.TITANSTEEL_INGOT)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_ingot", has(ModTags.ITEMS.TITANSTEEL_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "titansteel_block_from_ingot"));

        //Nugget -> Ingot
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, ModItems.SARONITE_INGOT.get())
                .define('X', ModTags.ITEMS.SARONITE_NUGGET)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_nugget", has(ModTags.ITEMS.SARONITE_NUGGET))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "saronite_ingot_from_nugget"));

        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, ModItems.TITANIUM_INGOT.get())
                .define('X', ModTags.ITEMS.TITANIUM_NUGGET)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_nugget", has(ModTags.ITEMS.TITANIUM_NUGGET))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "titanium_ingot_from_nugget"));

        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, ModItems.TITANSTEEL_INGOT.get())
                .define('X', ModTags.ITEMS.TITANSTEEL_NUGGET)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_nugget", has(ModTags.ITEMS.TITANSTEEL_NUGGET))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "titansteel_ingot_from_nugget"));

        //Block -> Ingot
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, ModItems.SARONITE_INGOT.get(), 9)
                .requires(ModTags.ITEMS.SARONITE_BLOCK)
                .unlockedBy("has_block", has(ModTags.ITEMS.SARONITE_BLOCK))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "saronite_ingot_from_block"));

        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, ModItems.TITANIUM_INGOT.get(), 9)
                .requires(ModTags.ITEMS.TITANIUM_BLOCK)
                .unlockedBy("has_block", has(ModTags.ITEMS.TITANIUM_BLOCK))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "titanium_ingot_from_block"));

        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, ModItems.TITANSTEEL_INGOT.get(), 9)
                .requires(ModTags.ITEMS.TITANSTEEL_BLOCK)
                .unlockedBy("has_block", has(ModTags.ITEMS.TITANSTEEL_BLOCK))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "titansteel_ingot_from_block"));

        //Ingot -> Nugget
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, ModItems.SARONITE_NUGGET.get(), 9)
                .requires(ModTags.ITEMS.SARONITE_INGOT)
                .unlockedBy("has_ingot", has(ModTags.ITEMS.SARONITE_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "saronite_nugget_from_ingot"));

        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, ModItems.TITANIUM_NUGGET.get(), 9)
                .requires(ModTags.ITEMS.TITANIUM_INGOT)
                .unlockedBy("has_ingot", has(ModTags.ITEMS.TITANIUM_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "titanium_nugget_from_ingot"));

        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, ModItems.TITANSTEEL_NUGGET.get(), 9)
                .requires(ModTags.ITEMS.TITANSTEEL_INGOT)
                .unlockedBy("has_ingot", has(ModTags.ITEMS.TITANSTEEL_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "titansteel_nugget_from_ingot"));

        //Tools
        ShapedRecipeBuilder
                .shaped(RecipeCategory.TOOLS, ModItems.TITANIUM_PICKAXE.get())
                .define('X', ModTags.ITEMS.TITANIUM_INGOT)
                .define('Y', Items.STICK)
                .pattern("XXX")
                .pattern(" Y ")
                .pattern(" Y ")
                .unlockedBy("has_ingot", has(ModTags.ITEMS.TITANIUM_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "titanium_pickaxe"));

        ShapedRecipeBuilder
                .shaped(RecipeCategory.TOOLS, ModItems.TITANIUM_AXE.get())
                .define('X', ModTags.ITEMS.TITANIUM_INGOT)
                .define('Y', Items.STICK)
                .pattern("XX ")
                .pattern("XY ")
                .pattern(" Y ")
                .unlockedBy("has_ingot", has(ModTags.ITEMS.TITANIUM_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "titanium_axe"));

        ShapedRecipeBuilder
                .shaped(RecipeCategory.TOOLS, ModItems.TITANIUM_SHOVEL.get())
                .define('X', ModTags.ITEMS.TITANIUM_INGOT)
                .define('Y', Items.STICK)
                .pattern(" X ")
                .pattern(" Y ")
                .pattern(" Y ")
                .unlockedBy("has_ingot", has(ModTags.ITEMS.TITANIUM_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "titanium_shovel"));

        ShapedRecipeBuilder
                .shaped(RecipeCategory.TOOLS, ModItems.TITANIUM_HOE.get())
                .define('X', ModTags.ITEMS.TITANIUM_INGOT)
                .define('Y', Items.STICK)
                .pattern("XX ")
                .pattern(" Y ")
                .pattern(" Y ")
                .unlockedBy("has_ingot", has(ModTags.ITEMS.TITANIUM_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "titanium_hoe"));

        //Weapons
        ShapedRecipeBuilder
                .shaped(RecipeCategory.COMBAT, ModItems.TITANIUM_SWORD.get())
                .define('X', ModTags.ITEMS.TITANIUM_INGOT)
                .define('Y', Items.STICK)
                .pattern(" X ")
                .pattern(" X ")
                .pattern(" Y ")
                .unlockedBy("has_ingot", has(ModTags.ITEMS.TITANIUM_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "titanium_sword"));

        //Armor
        ShapedRecipeBuilder
                .shaped(RecipeCategory.COMBAT, ModItems.TITANIUM_HELMET.get())
                .define('X', ModTags.ITEMS.TITANIUM_INGOT)
                .pattern("XXX")
                .pattern("X X")
                .unlockedBy("has_ingot", has(ModTags.ITEMS.TITANIUM_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "titanium_helmet"));

        ShapedRecipeBuilder
                .shaped(RecipeCategory.COMBAT, ModItems.TITANIUM_CHESTPLATE.get())
                .define('X', ModTags.ITEMS.TITANIUM_INGOT)
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_ingot", has(ModTags.ITEMS.TITANIUM_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "titanium_chestplate"));

        ShapedRecipeBuilder
                .shaped(RecipeCategory.COMBAT, ModItems.TITANIUM_LEGGINGS.get())
                .define('X', ModTags.ITEMS.TITANIUM_INGOT)
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .unlockedBy("has_ingot", has(ModTags.ITEMS.TITANIUM_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "titanium_leggings"));

        ShapedRecipeBuilder
                .shaped(RecipeCategory.COMBAT, ModItems.TITANIUM_BOOTS.get())
                .define('X', ModTags.ITEMS.TITANIUM_INGOT)
                .pattern("X X")
                .pattern("X X")
                .unlockedBy("has_ingot", has(ModTags.ITEMS.TITANIUM_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "titanium_boots"));
    }
}
