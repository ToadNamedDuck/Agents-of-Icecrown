package com.toadnamedduck.agentsoficecrown.client;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.fluid.ModFluidTypes;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid=Constants.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientFluidRegistration {
    @SubscribeEvent
    static void registerFluidExtensions(RegisterClientExtensionsEvent event){
        event.registerFluidType(new IClientFluidTypeExtensions() {
            @Override
            public @NotNull ResourceLocation getStillTexture() {
                return ResourceLocation.fromNamespaceAndPath(Constants.MODID, "block/liquid_saronite_still");
            }
            @Override
            public @NotNull ResourceLocation getFlowingTexture() {
                return ResourceLocation.fromNamespaceAndPath(Constants.MODID, "block/liquid_saronite_flowing");
            }
        }, ModFluidTypes.LIQUID_SARONITE.get()
        );
    }
}
