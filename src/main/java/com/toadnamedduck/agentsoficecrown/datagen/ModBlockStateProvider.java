package com.toadnamedduck.agentsoficecrown.datagen;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper){
        super(output, Constants.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels(){
        simpleBlockWithItem(ModBlocks.LICHSTONE.get(), models().cubeAll("lichstone", ResourceLocation.fromNamespaceAndPath(Constants.MODID, "block/lichstone")));
        simpleBlockWithItem(ModBlocks.BLOCK_OF_SARONITE.get(), models().cubeAll("block_of_saronite", ResourceLocation.fromNamespaceAndPath(Constants.MODID,"block/block_of_saronite")));
    }
}
