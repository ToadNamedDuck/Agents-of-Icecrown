package com.toadnamedduck.agentsoficecrown.datagen;

import com.toadnamedduck.agentsoficecrown.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
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
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks(){
        return List.of(ModBlocks.BLOCK_OF_SARONITE.get(), ModBlocks.LICHSTONE.get(), ModBlocks.RUNEFORGE.get(), ModBlocks.BLOCK_OF_TITANIUM.get(), ModBlocks.BLOCK_OF_TITANSTEEL.get());
    }
}
