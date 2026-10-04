package com.toadnamedduck.agentsoficecrown;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class BLOCKS {

        private static TagKey<Block> c(String path){
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", path));
        }

        private static TagKey<Block> aoi(String path){
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MODID, path));
        }

        public static final TagKey<Block> STONES = c("stones");
        public static final TagKey<Block> ORES = c("ores");
        public static final TagKey<Block> STORAGE_BLOCKS = c("storage_blocks");

        public static final TagKey<Block> SARONITE_ORES = c("ores/saronite");
        public static final TagKey<Block> TITANIUM_ORES = c("ores/titanium");
        public static final TagKey<Block> INCORRECT_FOR_TITANIUM_TOOL = aoi("incorrect_for_titanium_tool");
        public static final TagKey<Block> INCORRECT_FOR_TITANSTEEL_TOOL = aoi("incorrect_for_titansteel_tool");
        public static final TagKey<Block> SARONITE_BLOCK = c("storage_blocks/saronite");
        public static final TagKey<Block> TITANIUM_BLOCK = c("storage_blocks/titanium");
        public static final TagKey<Block> TITANSTEEL_BLOCK = c("storage_blocks/titansteel");
    }

    public static class ITEMS {
        private static TagKey<Item> c(String path){
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", path));
        }

        private static TagKey<Item> aoi(String path){
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Constants.MODID, path));
        }

        private static TagKey<Item> mc(String path){
            return TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace(path));
        }

        public static final TagKey<Item> BUCKETS = c("buckets");
        public static final TagKey<Item> RAW_MATERIALS = c("raw_materials");
        public static final TagKey<Item> INGOTS = c("ingots");
        public static final TagKey<Item> NUGGETS = c("nuggets");
        public static final TagKey<Item> ORES = c("ores");
        public static final TagKey<Item> STONES = c("stones");
        public static final TagKey<Item> STORAGE_BLOCKS = c("storage_blocks");

        public static final TagKey<Item> SARONITE_ORES = c("ores/saronite");
        public static final TagKey<Item> TITANIUM_ORES = c("ores/titanium");
        public static final TagKey<Item> BEACON_PAYMENT_ITEMS = mc("beacon_payment_items");
        public static final TagKey<Item> RAW_SARONITE = c("raw_materials/saronite");
        public static final TagKey<Item> SARONITE_INGOT = c("ingots/saronite");
        public static final TagKey<Item> SARONITE_NUGGET = c("nuggets/saronite");
        public static final TagKey<Item> SARONITE_BLOCK = c("storage_blocks/saronite");
        public static final TagKey<Item> SARONITE_BUCKET = c("buckets/liquid_saronite");
        public static final TagKey<Item> RAW_TITANIUM = c("raw_materials/titanium");
        public static final TagKey<Item> TITANIUM_INGOT = c("ingots/titanium");
        public static final TagKey<Item> TITANIUM_NUGGET = c("nuggets/titanium");
        public static final TagKey<Item> TITANIUM_BLOCK = c("storage_blocks/titanium");
        public static final TagKey<Item> TITANSTEEL_INGOT = c("ingots/titansteel");
        public static final TagKey<Item> TITANSTEEL_NUGGET = c("nuggets/titansteel");
        public static final TagKey<Item> TITANSTEEL_BLOCK = c("storage_blocks/titansteel");
    }
}
