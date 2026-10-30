package com.toadnamedduck.agentsoficecrown.worldgen;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> SARONITE_ORE = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "saronite_ore"));

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        List<OreConfiguration.TargetBlockState> targetBlocks = List.of(

                OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ModBlocks.SARONITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), ModBlocks.DEEPSLATE_SARONITE_ORE.get().defaultBlockState())
        );

        context.register(SARONITE_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(targetBlocks, 8, 0.35f)));
    }
}
