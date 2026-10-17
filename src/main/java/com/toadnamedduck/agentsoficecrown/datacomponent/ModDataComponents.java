package com.toadnamedduck.agentsoficecrown.datacomponent;

import com.toadnamedduck.agentsoficecrown.Constants;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents {
    public static DeferredRegister.DataComponents MOD_DATA_COMPONENTS = DeferredRegister.createDataComponents(Constants.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<SoulBinding>> SOUL_BINDING =
            MOD_DATA_COMPONENTS.registerComponentType("soul_binding", builder ->
                    builder
                            .persistent(SoulBinding.CODEC)
                            .networkSynchronized(SoulBinding.STREAM_CODEC)
            );

    public static void register(IEventBus modEventBus){
        MOD_DATA_COMPONENTS.register(modEventBus);
    }
}
