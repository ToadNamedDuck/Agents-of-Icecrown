package com.toadnamedduck.agentsoficecrown.datagen;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.block.ModBlocks;
import com.toadnamedduck.agentsoficecrown.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.HashSet;
import java.util.Set;

public class ModEnUsLanguageProvider extends LanguageProvider {
    public ModEnUsLanguageProvider(PackOutput output){
        super(output, Constants.MODID, "en_us");
    }
    private final Set<String> coveredKeys = new HashSet<>();

    @Override
    protected void addTranslations() {
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
        add("creativetab.agentsoficecrown.icecrown_tab", "Agents of Icecrown");

        checkItems();
    }
    private void addItemToList(DeferredHolder<Item, ?> entryItem, String name){
        var item = entryItem.get();
        this.add(item, name);
        coveredKeys.add(item.getDescriptionId());
    }

    private void addBlockToList(DeferredHolder<Block, ?> entryBlock, String name){
        var block = entryBlock.get();
        this.add(block, name);
        coveredKeys.add(block.getDescriptionId());
    }

    protected void checkItems(){
        //Make sure everything is accounted for
        for(var block: ModBlocks.BLOCKS.getEntries()) {
            String key = block.get().getDescriptionId();
            if(!this.coveredKeys.contains(key)){
                throw new IllegalStateException("Missing lang entry for: " + key);
            }

        }
        for(var item: ModItems.MOD_ITEMS.getEntries()){
            String key = item.get().getDescriptionId();
            if(!this.coveredKeys.contains(key)){
                throw new IllegalStateException("Missing lang entry for: " + key);
            }
        }
    }
}
