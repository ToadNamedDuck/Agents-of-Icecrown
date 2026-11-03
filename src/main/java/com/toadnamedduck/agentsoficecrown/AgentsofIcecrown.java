package com.toadnamedduck.agentsoficecrown;

import com.mojang.logging.LogUtils;
import com.toadnamedduck.agentsoficecrown.attachment.ModAttachments;
import com.toadnamedduck.agentsoficecrown.block.ModBlocks;
import com.toadnamedduck.agentsoficecrown.blockentity.ModBlockEntities;
import com.toadnamedduck.agentsoficecrown.datacomponent.ModDataComponents;
import com.toadnamedduck.agentsoficecrown.enchant.ModEntityEffects;
import com.toadnamedduck.agentsoficecrown.fluid.ModFluidTypes;
import com.toadnamedduck.agentsoficecrown.fluid.ModFluids;
import com.toadnamedduck.agentsoficecrown.item.ModArmorMaterials;
import com.toadnamedduck.agentsoficecrown.item.ModBlockItems;
import com.toadnamedduck.agentsoficecrown.item.ModItems;
import com.toadnamedduck.agentsoficecrown.menu.ModMenuTypes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;


// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Constants.MODID)
public class AgentsofIcecrown {
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public AgentsofIcecrown(IEventBus modEventBus, ModContainer modContainer) {
        // Register the Deferred Register to the mod event bus so blocks get registered
        ModBlocks.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ModBlockItems.register(modEventBus);
        ModItems.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        ModCreativeTabs.register(modEventBus);
        // Register FluidTypes and Fluids
        ModFluidTypes.register(modEventBus);
        ModFluids.register(modEventBus);
        //Armor
        ModArmorMaterials.register(modEventBus);
        //BlockEntities
        ModBlockEntities.register(modEventBus);
        //Data Components
        ModDataComponents.register(modEventBus);
        ModAttachments.register(modEventBus);
        //Menus
        ModMenuTypes.register(modEventBus);
        //Enchant Effects
        ModEntityEffects.register(modEventBus);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.SERVER, Config.SPEC);
    }
}
