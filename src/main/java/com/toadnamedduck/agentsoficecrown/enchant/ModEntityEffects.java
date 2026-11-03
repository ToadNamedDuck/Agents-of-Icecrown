package com.toadnamedduck.agentsoficecrown.enchant;

import com.mojang.serialization.MapCodec;
import com.toadnamedduck.agentsoficecrown.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntityEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_EFFECTS = DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Constants.MODID);
    public static final Supplier<MapCodec<FreezeEffect>> FREEZE = ENTITY_EFFECTS.register("freeze", () -> FreezeEffect.CODEC);

    public static void register(IEventBus modEventBus){
        ENTITY_EFFECTS.register(modEventBus);
    }
}
