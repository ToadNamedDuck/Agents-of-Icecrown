package com.toadnamedduck.agentsoficecrown.fluid;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.block.ModBlocks;
import com.toadnamedduck.agentsoficecrown.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFluids {
    public static DeferredRegister<Fluid> MOD_FLUIDS = DeferredRegister.create(Registries.FLUID, Constants.MODID);

    public static final DeferredHolder<Fluid, BaseFlowingFluid.Source> LIQUID_SARONITE_SOURCE = MOD_FLUIDS.register("liquid_saronite", () -> new BaseFlowingFluid.Source(
            new BaseFlowingFluid.Properties(
                    ModFluidTypes.LIQUID_SARONITE,
                    ModFluids.LIQUID_SARONITE_SOURCE,
                    ModFluids.LIQUID_SARONITE_FLOWING
            )
                    .block(ModBlocks.LIQUID_SARONITE)
                    .bucket(ModItems.LIQUID_SARONITE_BUCKET)
    ));

    public static final DeferredHolder<Fluid, BaseFlowingFluid.Flowing> LIQUID_SARONITE_FLOWING = MOD_FLUIDS.register("flowing_liquid_saronite", () -> new BaseFlowingFluid.Flowing(
            new BaseFlowingFluid.Properties(
                    ModFluidTypes.LIQUID_SARONITE,
                    ModFluids.LIQUID_SARONITE_SOURCE,
                    ModFluids.LIQUID_SARONITE_FLOWING
            )
                    .block(ModBlocks.LIQUID_SARONITE)
    ));

    public static void register(IEventBus modEventBus){
        MOD_FLUIDS.register(modEventBus);
    }
}
