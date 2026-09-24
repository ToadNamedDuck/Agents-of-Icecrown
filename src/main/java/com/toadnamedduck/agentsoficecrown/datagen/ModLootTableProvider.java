package com.toadnamedduck.agentsoficecrown.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends LootTableProvider {
    public ModLootTableProvider (PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider){
        super(output,
                Set.of(),//Required tables - not necessary rn
                List.of(//Sub Provider list, add to when need new types of tables (like entity drops or chest loot)
                        new SubProviderEntry(ModBlockLootSubProvider::new, LootContextParamSets.BLOCK)
                ),
                lookupProvider
        );
    }
}
