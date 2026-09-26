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

        //Broad tag keys here
        TagKey<Item> buckets = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "buckets"));
        TagKey<Item> raw_materials = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "raw_materials"));
        TagKey<Item> ingots = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots"));
        TagKey<Item> nuggets = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets"));

        //Fold subtags into broader c category
        tag(buckets)
                .addTag(saroniteBucket);
        tag(raw_materials)
                .addTag(saroniteRawMaterial);
        tag(ingots)
                .addTag(saroniteIngot);
        tag(nuggets)
                .addTag(saroniteNugget);

        //Tag actual items lol
        tag(saroniteRawMaterial)
                .add(ModItems.RAW_SARONITE_ORE.get());

        tag(saroniteBucket)
                .add(ModItems.LIQUID_SARONITE_BUCKET.get());

        tag(saroniteIngot)
                .add(ModItems.SARONITE_INGOT.get());

        tag(saroniteNugget)
                .add(ModItems.SARONITE_NUGGET.get());
    }
}
