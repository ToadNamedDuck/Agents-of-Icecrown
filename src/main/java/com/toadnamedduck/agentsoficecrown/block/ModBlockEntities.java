package com.toadnamedduck.agentsoficecrown.block;

import com.toadnamedduck.agentsoficecrown.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> MOD_BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Constants.MODID);

    public static final Supplier<BlockEntityType<AltarBlockEntity>> ALTAR_BLOCK_ENTITY = MOD_BLOCK_ENTITIES.register("altar_block_entity", () ->
            BlockEntityType.Builder.of(
                    AltarBlockEntity::new,
                    ModBlocks.ALTAR.get())
                    .build(null)
    );

    public static void register(IEventBus modEventBus){
        MOD_BLOCK_ENTITIES.register(modEventBus);
    }

}
