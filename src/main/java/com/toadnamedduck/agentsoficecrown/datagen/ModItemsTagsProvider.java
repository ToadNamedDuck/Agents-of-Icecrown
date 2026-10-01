package com.toadnamedduck.agentsoficecrown.datagen;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModItemsTagsProvider extends ItemTagsProvider {
    public ModItemsTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ModBlocksTagsProvider modBlocksTagsProvider, ExistingFileHelper existingFileHelper){
        super(output, lookupProvider, modBlocksTagsProvider.contentsGetter(), Constants.MODID, existingFileHelper);
    }


    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {
        //Specific material tag keys here, so they are easier to keep track of
        TagKey<Item> saroniteRawMaterial = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "raw_materials/saronite"));
        TagKey<Item> saroniteBucket = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "buckets/liquid_saronite"));
        TagKey<Item> saroniteIngot = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/saronite"));
        TagKey<Item> saroniteNugget = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/saronite"));
        TagKey<Item> titaniumRawMaterial = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "raw_materials/titanium"));
        TagKey<Item> titaniumIngot = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/titanium"));
        TagKey<Item> titaniumNugget = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/titanium"));
        TagKey<Item> titansteelIngot = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/titansteel"));
        TagKey<Item> titansteelNugget = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/titansteel"));


        //Broad tag keys here
        TagKey<Item> buckets = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "buckets"));
        TagKey<Item> raw_materials = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "raw_materials"));
        TagKey<Item> ingots = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots"));
        TagKey<Item> nuggets = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets"));
        TagKey<Item> ores = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores"));

        //Special registration for both c:stones as an item tag and a block tag, to make sure both block and item of stones are tagged correctly
        TagKey<Block> stonesBlockTag = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "stones"));
        TagKey<Item> stonesItemTag = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "stones"));

        //ores and whatnot
        TagKey<Block> saroniteOresBlock = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/saronite"));
        TagKey<Item> saroniteOresItem = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/saronite"));
        TagKey<Block> titaniumOresBlock = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/titanium"));
        TagKey<Item> titaniumOresItem = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/titanium"));

        //Other
        TagKey<Item> beacon_payable = TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace("beacon_payment_items"));

        //Copy the block tags to the item tag - registers EVERY thing (say with c:stones as a block tag) with the mirrored item tag (so every c:stones block tag is paired forever with c:stones the item tag) - useful if I add more stones
        //Items is mirrored FROM blocks
        //I can probably do the same with ores :) and deepslate variants and all of that
        this.copy(stonesBlockTag, stonesItemTag);
        this.copy(saroniteOresBlock, saroniteOresItem);
        this.copy(titaniumOresBlock, titaniumOresItem);

        //Fold subtags into broader c category
        tag(buckets)
                .addTag(saroniteBucket);
        tag(raw_materials)
                .addTag(saroniteRawMaterial)
                .addTag(titaniumRawMaterial);
        tag(ingots)
                .addTag(saroniteIngot)
                .addTag(titaniumIngot)
                .addTag(titansteelIngot);
        tag(nuggets)
                .addTag(saroniteNugget)
                .addTag(titaniumNugget)
                .addTag(titansteelNugget);
        tag(ores)
                .addTag(saroniteOresItem)
                .addTag(titaniumOresItem);

        //Tag actual items lol
        tag(saroniteRawMaterial)
                .add(ModItems.RAW_SARONITE_ORE.get());

        tag(saroniteBucket)
                .add(ModItems.LIQUID_SARONITE_BUCKET.get());

        tag(saroniteIngot)
                .add(ModItems.SARONITE_INGOT.get());

        tag(saroniteNugget)
                .add(ModItems.SARONITE_NUGGET.get());

        tag(titaniumRawMaterial)
                .add(ModItems.RAW_TITANIUM_ORE.get());

        tag(titaniumIngot)
                .add(ModItems.TITANIUM_INGOT.get());

        tag(titaniumNugget)
                .add(ModItems.TITANIUM_NUGGET.get());

        tag(titansteelIngot)
                .add(ModItems.TITANSTEEL_INGOT.get());

        tag(titansteelNugget)
                .add(ModItems.TITANSTEEL_NUGGET.get());

        tag(beacon_payable)
                .addTag(saroniteIngot)
                .addTag(titansteelIngot);
    }
}
