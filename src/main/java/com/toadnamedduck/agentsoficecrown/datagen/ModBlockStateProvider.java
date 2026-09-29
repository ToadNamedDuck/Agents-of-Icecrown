package com.toadnamedduck.agentsoficecrown.datagen;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.block.ModBlocks;
import com.toadnamedduck.agentsoficecrown.block.RuneforgeBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper){
        super(output, Constants.MODID, existingFileHelper);
    }



    @Override
    protected void registerStatesAndModels(){
        //Runeforge models
        ModelFile runeforgeBottomModel = models().getExistingFile(modLoc("block/runeforge_bottom"));
        ModelFile runeforgeTopModel = models().getExistingFile(modLoc("block/runeforge_top"));

        horizontalBlock(ModBlocks.RUNEFORGE.get(),state -> {
            if(state.getValue(RuneforgeBlock.HALF) == DoubleBlockHalf.LOWER){
                return runeforgeBottomModel;
            }
            else{
                return runeforgeTopModel;
            }
        });
        itemModels().withExistingParent("runeforge", modLoc("item/runeforge_combined_item"));

        //Simple Blocks
        simpleBlockWithItem(ModBlocks.LICHSTONE.get(), models().cubeAll("lichstone", ResourceLocation.fromNamespaceAndPath(Constants.MODID, "block/lichstone")));
        simpleBlockWithItem(ModBlocks.BLOCK_OF_SARONITE.get(), models().cubeAll("block_of_saronite", ResourceLocation.fromNamespaceAndPath(Constants.MODID,"block/block_of_saronite")));
    }
}
