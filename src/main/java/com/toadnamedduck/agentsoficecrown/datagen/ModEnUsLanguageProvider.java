package com.toadnamedduck.agentsoficecrown.datagen;

import com.toadnamedduck.agentsoficecrown.block.ModBlocks;
import com.toadnamedduck.agentsoficecrown.item.ModItems;
import net.minecraft.data.PackOutput;


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

        //Blocks
        addBlockToList(ModBlocks.BLOCK_OF_SARONITE, "Block of Saronite");
        addBlockToList(ModBlocks.LICHSTONE, "Lichstone");
        addBlockToList(ModBlocks.LIQUID_SARONITE, "Liquid Saronite");
        addBlockToList(ModBlocks.RUNEFORGE, "Runeforge");

        //Entities

        //Death Messages

        //Advancements

        //GUIs

        //Misc - Creative Mode Tab
        addByKey("creativetab.agentsoficecrown.icecrown_tab", "Agents of Icecrown");
    }

}
