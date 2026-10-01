package com.toadnamedduck.agentsoficecrown.datagen;

import com.toadnamedduck.agentsoficecrown.block.ModBlocks;
import com.toadnamedduck.agentsoficecrown.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public class ModBlockLootSubProvider extends BlockLootSubProvider {
    protected ModBlockLootSubProvider(HolderLookup.Provider registries){
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate(){
        dropSelf(ModBlocks.BLOCK_OF_SARONITE.get());
        dropSelf(ModBlocks.LICHSTONE.get());
        dropSelf(ModBlocks.BLOCK_OF_TITANIUM.get());
        dropSelf(ModBlocks.BLOCK_OF_TITANSTEEL.get());
        add(ModBlocks.RUNEFORGE.get(), createDoorTable(ModBlocks.RUNEFORGE.get()));

        add(ModBlocks.SARONITE_ORE.get(), createOreDrop(ModBlocks.SARONITE_ORE.get(), ModItems.RAW_SARONITE_ORE.get()));
        add(ModBlocks.DEEPSLATE_SARONITE_ORE.get(), createOreDrop(ModBlocks.DEEPSLATE_SARONITE_ORE.get(), ModItems.RAW_SARONITE_ORE.get()));
        add(ModBlocks.TITANIUM_ORE.get(),createOreDrop(ModBlocks.TITANIUM_ORE.get(), ModItems.RAW_TITANIUM_ORE.get()));
        add(ModBlocks.DEEPSLATE_TITANIUM_ORE.get(),createOreDrop(ModBlocks.DEEPSLATE_TITANIUM_ORE.get(), ModItems.RAW_TITANIUM_ORE.get()));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks(){
        return ModBlocks.BLOCKS.getEntries().stream().<Block>map(DeferredHolder::get).toList();
    }
}
