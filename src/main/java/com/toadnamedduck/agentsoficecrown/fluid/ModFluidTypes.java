package com.toadnamedduck.agentsoficecrown.fluid;

import com.toadnamedduck.agentsoficecrown.Constants;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.pathfinder.PathType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModFluidTypes {
    public static final DeferredRegister<FluidType> MOD_FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, Constants.MODID);

    public static final Supplier<FluidType> LIQUID_SARONITE = MOD_FLUID_TYPES.register("liquid_saronite", () ->
            new FluidType(FluidType.Properties.create()
                    .canDrown(true)
                    .canSwim(true)
                    .pathType(PathType.LAVA)
                    .adjacentPathType(PathType.LAVA)
                    .canConvertToSource(false)
                    .canExtinguish(true)
                    .canHydrate(false)
                    .canPushEntity(false)
                    .density(750)
                    .viscosity(2000)
                    .lightLevel(8)
                    .temperature(250)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
            ));

    public static void register(IEventBus modEventBus){
        MOD_FLUID_TYPES.register(modEventBus);
    }
}
