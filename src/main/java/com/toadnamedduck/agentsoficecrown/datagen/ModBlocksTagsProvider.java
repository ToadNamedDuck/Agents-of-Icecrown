package com.toadnamedduck.agentsoficecrown.datagen;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.ModTags;
import com.toadnamedduck.agentsoficecrown.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModBlocksTagsProvider extends BlockTagsProvider {
    public ModBlocksTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper){
        super(output, lookupProvider, Constants.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {

        tag(ModTags.BLOCKS.ORES)
                .addTag(ModTags.BLOCKS.SARONITE_ORES)
                .addTag(ModTags.BLOCKS.TITANIUM_ORES);

        tag(ModTags.BLOCKS.STORAGE_BLOCKS)
                .addTag(ModTags.BLOCKS.SARONITE_BLOCK)
                .addTag(ModTags.BLOCKS.TITANIUM_BLOCK)
                .addTag(ModTags.BLOCKS.TITANSTEEL_BLOCK);

        tag(ModTags.BLOCKS.TITANSTEEL_BLOCK)
                .add(ModBlocks.BLOCK_OF_TITANSTEEL.get());

        tag(ModTags.BLOCKS.TITANIUM_BLOCK)
                .add(ModBlocks.BLOCK_OF_TITANIUM.get());

        tag(ModTags.BLOCKS.SARONITE_BLOCK)
                .add(ModBlocks.BLOCK_OF_SARONITE.get());

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
                .add(ModBlocks.ALTAR.get())
                ;

        tag(ModTags.BLOCKS.SARONITE_ORES)
                .add(ModBlocks.SARONITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_SARONITE_ORE.get())
                ;

        tag(ModTags.BLOCKS.TITANIUM_ORES)
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

        tag(ModTags.BLOCKS.STONES)
                .add(ModBlocks.LICHSTONE.get())
                ;

        tag(ModTags.BLOCKS.INCORRECT_FOR_TITANIUM_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL);

        tag(ModTags.BLOCKS.INCORRECT_FOR_TITANSTEEL_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL);
    }

}
