package com.toadnamedduck.agentsoficecrown.client;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.menu.ModMenuTypes;
import com.toadnamedduck.agentsoficecrown.screen.SoulCondenserScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid= Constants.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientScreenRegistration {
    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event){
        event.register(ModMenuTypes.SOUL_CONDENSER_MENU_TYPE.get(), SoulCondenserScreen::new);
    }
}
