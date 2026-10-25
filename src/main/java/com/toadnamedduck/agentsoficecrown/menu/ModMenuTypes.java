package com.toadnamedduck.agentsoficecrown.menu;

import com.toadnamedduck.agentsoficecrown.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MOD_MENU_TYPES = DeferredRegister.create(Registries.MENU, Constants.MODID);

    public static final Supplier<MenuType<SoulCondenserMenu>> SOUL_CONDENSER_MENU_TYPE = MOD_MENU_TYPES.register("soul_condenser_menu", () ->
            new MenuType<SoulCondenserMenu>(SoulCondenserMenu::new, FeatureFlags.DEFAULT_FLAGS));

    public static void register(IEventBus modEventBus){
        MOD_MENU_TYPES.register(modEventBus);
    }
}
