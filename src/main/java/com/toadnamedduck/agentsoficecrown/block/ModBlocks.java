package com.toadnamedduck.agentsoficecrown.block;
import com.toadnamedduck.agentsoficecrown.Constants;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Constants.MODID);

    public static final DeferredBlock<Block> TEST_STONE = BLOCKS.register(
            "test_stone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .destroyTime(1.5f)
                    .explosionResistance(6.0f)
                    .sound(SoundType.STONE)
                    .lightLevel(state -> 0)
            ));

    public static void register(IEventBus modEventBus){
        BLOCKS.register(modEventBus);
    }
}