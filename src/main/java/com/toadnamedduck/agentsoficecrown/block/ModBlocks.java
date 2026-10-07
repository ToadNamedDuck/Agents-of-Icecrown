package com.toadnamedduck.agentsoficecrown.block;
import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.fluid.ModFluids;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Constants.MODID);

    public static final DeferredBlock<Block> LICHSTONE = BLOCKS.register(
            "lichstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.5f, 6.0f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()
            ));

    public static final DeferredBlock<LiquidBlock> LIQUID_SARONITE = BLOCKS.register("liquid_saronite", () ->
            new LiquidBlock(ModFluids.LIQUID_SARONITE_SOURCE.get(), BlockBehaviour.Properties.of()
                    .liquid()
                    .noCollission()
                    .strength(100.0f)
                    .noLootTable()
                    .pushReaction(PushReaction.DESTROY)
                    .lightLevel(state -> 13)
                    .replaceable()
            )
    );

    public static final DeferredBlock<Block> BLOCK_OF_SARONITE = BLOCKS.register(
            "block_of_saronite",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(5.0f, 6.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)
            ));

    public static final DeferredBlock<RuneforgeBlock> RUNEFORGE = BLOCKS.register(
            "runeforge",
            () -> new RuneforgeBlock(BlockBehaviour.Properties.of()
                    .strength(5.0f, 6.0f)
                    .noOcclusion()
                    .pushReaction(PushReaction.BLOCK)
                    .requiresCorrectToolForDrops()
            ));

    public static final DeferredBlock<Block> BLOCK_OF_TITANIUM = BLOCKS.register(
                "block_of_titanium",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(5.0f, 6.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)
            ));

    public static final DeferredBlock<Block> BLOCK_OF_TITANSTEEL = BLOCKS.register(
            "block_of_titansteel",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(50.0F, 1200.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.NETHERITE_BLOCK)
            ));

    public static final DeferredBlock<Block> SARONITE_ORE = BLOCKS.register(
            "saronite_ore",
            () -> new DropExperienceBlock(UniformInt.of(0, 2), BlockBehaviour.Properties.of()
                    .strength(3.0f,3.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> DEEPSLATE_SARONITE_ORE = BLOCKS.register(
            "deepslate_saronite_ore",
            () -> new DropExperienceBlock(UniformInt.of(0, 2), BlockBehaviour.Properties.of()
                    .strength(4.5f,3.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)
            ));

    public static final DeferredBlock<Block> TITANIUM_ORE = BLOCKS.register(
            "titanium_ore",
            () -> new DropExperienceBlock(UniformInt.of(0, 2), BlockBehaviour.Properties.of()
                    .strength(3.0f,3.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> DEEPSLATE_TITANIUM_ORE = BLOCKS.register(
            "deepslate_titanium_ore",
            () -> new DropExperienceBlock(UniformInt.of(0, 2), BlockBehaviour.Properties.of()
                    .strength(4.5f,3.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)
            ));

    public static final DeferredBlock<AltarBlock> ALTAR = BLOCKS.register(
            "altar",
            () -> new AltarBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f, 6.0f)
                    .pushReaction(PushReaction.BLOCK)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.SOUL_SAND)
                    .noOcclusion()
            ));

    public static void register(IEventBus modEventBus){
        BLOCKS.register(modEventBus);
    }
}