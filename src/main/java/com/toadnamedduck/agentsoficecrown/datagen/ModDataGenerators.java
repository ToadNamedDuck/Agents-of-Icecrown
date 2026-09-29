package com.toadnamedduck.agentsoficecrown.datagen;

import com.toadnamedduck.agentsoficecrown.Constants;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Constants.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModDataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator gen = event.getGenerator();
        PackOutput out = gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        ModBlocksTagsProvider blockTags = new ModBlocksTagsProvider(out, lookupProvider, existingFileHelper);

        gen.addProvider(event.includeServer(), new ModRecipeProvider(out, lookupProvider));
        gen.addProvider(event.includeServer(), new ModLootTableProvider(out, lookupProvider));
        gen.addProvider(event.includeServer(), blockTags);
        gen.addProvider(event.includeServer(), new ModItemsTagsProvider(out, lookupProvider, blockTags, existingFileHelper));
        gen.addProvider(event.includeServer(), new ModBlockStateProvider(out, existingFileHelper));

        gen.addProvider(event.includeClient(), new ModEnUsLanguageProvider(out));
    }

}
