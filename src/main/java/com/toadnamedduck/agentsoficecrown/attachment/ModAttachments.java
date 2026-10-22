package com.toadnamedduck.agentsoficecrown.attachment;

import com.mojang.serialization.Codec;
import com.toadnamedduck.agentsoficecrown.Constants;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> MOD_DATA_ATTACHMENTS = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Constants.MODID);

    public static final Supplier<AttachmentType<Boolean>> BOUND = MOD_DATA_ATTACHMENTS.register("bound", () ->
            AttachmentType.<Boolean>builder(() -> false)
                    .serialize(Codec.BOOL)
                    .copyOnDeath()
                    .build());

    public static void register(IEventBus modEventBus){
        MOD_DATA_ATTACHMENTS.register(modEventBus);
    }

}