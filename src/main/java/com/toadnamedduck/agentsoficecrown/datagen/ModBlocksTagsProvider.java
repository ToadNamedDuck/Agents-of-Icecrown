package com.toadnamedduck.agentsoficecrown.datagen;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

import static com.toadnamedduck.agentsoficecrown.item.ModSimpleTiers.incorrect_for_titanium_tool;
import static com.toadnamedduck.agentsoficecrown.item.ModSimpleTiers.incorrect_for_titansteel_tool;

public class ModBlocksTagsProvider extends BlockTagsProvider {
    public ModBlocksTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper){
        super(output, lookupProvider, Constants.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {

        TagKey<Block> stones = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "stones"));
        TagKey<Block> saronite_ores = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/saronite"));
        TagKey<Block> titanium_ores = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/titanium"));

        TagKey<Block> ores = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores"));

        tag(ores)
                .addTag(saronite_ores)
                .addTag(titanium_ores);

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.BLOCK_OF_SARONITE.get())
                .add(ModBlocks.LICHSTONE.get())
                .add(ModBlocks.RUNEFORGE.get())
                .add(ModBlocks.BLOCK_OF_TITANIUM.get())
                .add(ModBlocks.BLOCK_OF_TITANSTEEL.get())
                .add(ModBlocks.SARONITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_SARONITE_ORE.get())
                .add(ModBlocks.TITANIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_TITANIUM_ORE.get())
                ;

        tag(saronite_ores)
                .add(ModBlocks.SARONITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_SARONITE_ORE.get())
                ;

        tag(titanium_ores)
                .add(ModBlocks.TITANIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_TITANIUM_ORE.get())
                ;

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.RUNEFORGE.get())
                ;

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.BLOCK_OF_SARONITE.get())
                .add(ModBlocks.BLOCK_OF_TITANIUM.get())
                .add(ModBlocks.BLOCK_OF_TITANSTEEL.get())
                .add(ModBlocks.SARONITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_SARONITE_ORE.get())
                .add(ModBlocks.TITANIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_TITANIUM_ORE.get())
                ;

        tag(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlocks.BLOCK_OF_SARONITE.get())
                .add(ModBlocks.BLOCK_OF_TITANSTEEL.get())
                ;

        tag(stones)
                .add(ModBlocks.LICHSTONE.get())
                ;

        tag(incorrect_for_titanium_tool)
                .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL);

        tag(incorrect_for_titansteel_tool)
                .addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL);
    }

}
