package com.toadnamedduck.agentsoficecrown.datagen;

import com.toadnamedduck.agentsoficecrown.block.ModBlocks;
import com.toadnamedduck.agentsoficecrown.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.fml.common.Mod;


public class ModEnUsLanguageProvider extends ModLanguageProvider {
    public ModEnUsLanguageProvider(PackOutput output){
        super(output, "en_us");
    }

    @Override
    protected void addLocaleTranslations() {
        //Items
        addItemToList(ModItems.RAW_SARONITE_ORE, "Raw Saronite Ore");
        addItemToList(ModItems.LIQUID_SARONITE_BUCKET, "Bucket of Liquid Saronite");
        addItemToList(ModItems.SARONITE_INGOT, "Saronite Ingot");
        addItemToList(ModItems.SARONITE_NUGGET, "Saronite Nugget");
        addItemToList(ModItems.RAW_TITANIUM_ORE, "Raw Titanium Ore");
        addItemToList(ModItems.TITANIUM_INGOT, "Titanium Ingot");
        addItemToList(ModItems.TITANIUM_NUGGET, "Titanium Nugget");
        addItemToList(ModItems.TITANSTEEL_INGOT, "Titansteel Ingot");
        addItemToList(ModItems.TITANSTEEL_NUGGET, "Titansteel Nugget");

        //Blocks
        addBlockToList(ModBlocks.BLOCK_OF_SARONITE, "Block of Saronite");
        addBlockToList(ModBlocks.LICHSTONE, "Lichstone");
        addBlockToList(ModBlocks.LIQUID_SARONITE, "Liquid Saronite");
        addBlockToList(ModBlocks.RUNEFORGE, "Runeforge");
        addBlockToList(ModBlocks.BLOCK_OF_TITANIUM, "Block of Titanium");
        addBlockToList(ModBlocks.BLOCK_OF_TITANSTEEL, "Block of Titansteel");
        addBlockToList(ModBlocks.SARONITE_ORE, "Saronite Ore");
        addBlockToList(ModBlocks.DEEPSLATE_SARONITE_ORE, "Deepslate Saronite Ore");
        addBlockToList(ModBlocks.TITANIUM_ORE, "Titanium Ore");
        addBlockToList(ModBlocks.DEEPSLATE_TITANIUM_ORE, "Deepslate Titanium Ore");

        //Entities

        //Death Messages

        //Advancements

        //GUIs

        //Misc - Creative Mode Tab
        addByKey("creativetab.agentsoficecrown.icecrown_tab", "Agents of Icecrown");
    }

}
