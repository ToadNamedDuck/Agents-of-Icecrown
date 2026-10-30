package com.toadnamedduck.agentsoficecrown.worldgen;

import com.toadnamedduck.agentsoficecrown.Constants;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static ResourceKey<PlacedFeature> SARONITE_ORE = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Constants.MODID, "saronite_ore"));

    public static void bootstrap(BootstrapContext<PlacedFeature> context){
        HolderGetter<ConfiguredFeature<?,?>> configured = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(SARONITE_ORE, new PlacedFeature(
                configured.getOrThrow(ModConfiguredFeatures.SARONITE_ORE),
                List.of(
                        CountPlacement.of(4),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.triangle(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(16)),
                        BiomeFilter.biome()
                )
        ));
    }
}
