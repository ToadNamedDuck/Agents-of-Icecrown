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

public abstract class ModLanguageProvider extends LanguageProvider {
    ModLanguageProvider(PackOutput output, String locale){
        super(output, Constants.MODID, locale);
    }


    protected final Set<String> coveredKeys = new HashSet<>();

    @Override
    protected final void addTranslations() {
        addLocaleTranslations();
        checkItems();
    }

    protected abstract void addLocaleTranslations();

    protected void addItemToList(DeferredHolder<Item, ?> entryItem, String name){
        var item = entryItem.get();
        this.add(item, name);
        coveredKeys.add(item.getDescriptionId());
    }

    protected void addBlockToList(DeferredHolder<Block, ?> entryBlock, String name){
        var block = entryBlock.get();
        this.add(block, name);
        coveredKeys.add(block.getDescriptionId());
    }

    protected void addByKey(String key, String name){
        this.add(key, name);
        coveredKeys.add(key);
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
        //Misc checks not in a loop-worthy registry, like single creative tab
        if(!this.coveredKeys.contains("creativetab.agentsoficecrown.icecrown_tab")){
            throw new IllegalStateException("Missing lang entry for: creativetab.agentsoficecrown.icecrown_tab");
        }
    }
}
