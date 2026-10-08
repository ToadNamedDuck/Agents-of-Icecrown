package com.toadnamedduck.agentsoficecrown.client;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.blockentity.AltarBlockEntityRenderer;
import com.toadnamedduck.agentsoficecrown.blockentity.ModBlockEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid= Constants.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientBlockEntityRendererRegistration {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(
                ModBlockEntities.ALTAR_BLOCK_ENTITY.get(), AltarBlockEntityRenderer::new
        );
    }
}
