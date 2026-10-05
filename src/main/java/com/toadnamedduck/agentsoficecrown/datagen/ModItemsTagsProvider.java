package com.toadnamedduck.agentsoficecrown.datagen;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.ModTags;
import com.toadnamedduck.agentsoficecrown.block.ModBlocks;
import com.toadnamedduck.agentsoficecrown.item.ModBlockItems;
import com.toadnamedduck.agentsoficecrown.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModItemsTagsProvider extends ItemTagsProvider {
    public ModItemsTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ModBlocksTagsProvider modBlocksTagsProvider, ExistingFileHelper existingFileHelper){
        super(output, lookupProvider, modBlocksTagsProvider.contentsGetter(), Constants.MODID, existingFileHelper);
    }


    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {

        this.copy(ModTags.BLOCKS.STONES, ModTags.ITEMS.STONES);
        this.copy(ModTags.BLOCKS.SARONITE_ORES, ModTags.ITEMS.SARONITE_ORES);
        this.copy(ModTags.BLOCKS.TITANIUM_ORES, ModTags.ITEMS.TITANIUM_ORES);
        this.copy(ModTags.BLOCKS.STORAGE_BLOCKS, ModTags.ITEMS.STORAGE_BLOCKS);
        this.copy(ModTags.BLOCKS.ORES, ModTags.ITEMS.ORES);
        this.copy(ModTags.BLOCKS.SARONITE_BLOCK, ModTags.ITEMS.SARONITE_BLOCK);
        this.copy(ModTags.BLOCKS.TITANIUM_BLOCK, ModTags.ITEMS.TITANIUM_BLOCK);
        this.copy(ModTags.BLOCKS.TITANSTEEL_BLOCK, ModTags.ITEMS.TITANSTEEL_BLOCK);

        //Fold subtags into broader c category
        tag(ModTags.ITEMS.BUCKETS)
                .addTag(ModTags.ITEMS.SARONITE_BUCKET);

        tag(ModTags.ITEMS.RAW_MATERIALS)
                .addTag(ModTags.ITEMS.RAW_SARONITE)
                .addTag(ModTags.ITEMS.RAW_TITANIUM);

        tag(ModTags.ITEMS.INGOTS)
                .addTag(ModTags.ITEMS.SARONITE_INGOT)
                .addTag(ModTags.ITEMS.TITANIUM_INGOT)
                .addTag(ModTags.ITEMS.TITANSTEEL_INGOT);

        tag(ModTags.ITEMS.NUGGETS)
                .addTag(ModTags.ITEMS.SARONITE_NUGGET)
                .addTag(ModTags.ITEMS.TITANIUM_NUGGET)
                .addTag(ModTags.ITEMS.TITANSTEEL_NUGGET);

        tag(ModTags.ITEMS.TOOLS)
                .addTag(ModTags.ITEMS.MELEE_WEAPON)
                .addTag(ModTags.ITEMS.MINING_TOOL);

        //Tag actual items lol
        tag(ModTags.ITEMS.RAW_SARONITE)
                .add(ModItems.RAW_SARONITE_ORE.get());

        tag(ModTags.ITEMS.SARONITE_BUCKET)
                .add(ModItems.LIQUID_SARONITE_BUCKET.get());

        tag(ModTags.ITEMS.SARONITE_INGOT)
                .add(ModItems.SARONITE_INGOT.get());

        tag(ModTags.ITEMS.SARONITE_NUGGET)
                .add(ModItems.SARONITE_NUGGET.get());

        tag(ModTags.ITEMS.RAW_TITANIUM)
                .add(ModItems.RAW_TITANIUM_ORE.get());

        tag(ModTags.ITEMS.TITANIUM_INGOT)
                .add(ModItems.TITANIUM_INGOT.get());

        tag(ModTags.ITEMS.TITANIUM_NUGGET)
                .add(ModItems.TITANIUM_NUGGET.get());

        tag(ModTags.ITEMS.TITANSTEEL_INGOT)
                .add(ModItems.TITANSTEEL_INGOT.get());

        tag(ModTags.ITEMS.TITANSTEEL_NUGGET)
                .add(ModItems.TITANSTEEL_NUGGET.get());

        tag(ModTags.ITEMS.BEACON_PAYMENT_ITEMS)
                .addTag(ModTags.ITEMS.SARONITE_INGOT)
                .addTag(ModTags.ITEMS.TITANSTEEL_INGOT);

        tag(ItemTags.SWORDS)
                .add(ModItems.TITANIUM_SWORD.get());

        tag(ItemTags.PICKAXES)
                .add(ModItems.TITANIUM_PICKAXE.get());

        tag(ItemTags.SHOVELS)
                .add(ModItems.TITANIUM_SHOVEL.get());

        tag(ItemTags.HOES)
                .add(ModItems.TITANIUM_HOE.get());

        tag(ItemTags.AXES)
                .add(ModItems.TITANIUM_AXE.get());

        tag(ItemTags.HEAD_ARMOR)
                .add(ModItems.TITANIUM_HELMET.get());

        tag(ItemTags.CHEST_ARMOR)
                .add(ModItems.TITANIUM_CHESTPLATE.get());

        tag(ItemTags.LEG_ARMOR)
                .add(ModItems.TITANIUM_LEGGINGS.get());

        tag(ItemTags.FOOT_ARMOR)
                .add(ModItems.TITANIUM_BOOTS.get());

        tag(ModTags.ITEMS.MINING_TOOL)
                .add(ModItems.TITANIUM_PICKAXE.get())
                .add(ModItems.TITANIUM_SHOVEL.get())
                .add(ModItems.TITANIUM_HOE.get());

        tag(ModTags.ITEMS.MELEE_WEAPON)
                .add(ModItems.TITANIUM_AXE.get())
                .add(ModItems.TITANIUM_SWORD.get());
    }
}
