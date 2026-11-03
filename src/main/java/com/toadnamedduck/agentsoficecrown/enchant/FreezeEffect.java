package com.toadnamedduck.agentsoficecrown.enchant;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public record FreezeEffect(LevelBasedValue ticks) implements EnchantmentEntityEffect {
    public static final MapCodec<FreezeEffect> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            LevelBasedValue.CODEC.fieldOf("ticks").forGetter(FreezeEffect::ticks)
            ).apply(i, FreezeEffect::new));

    @Override
    public void apply(@NotNull ServerLevel level, int enchantLvl, @NotNull EnchantedItemInUse enchantedItemInUse, @NotNull Entity target, @NotNull Vec3 origin) {
        int total = target.getTicksFrozen() + (int) ticks.calculate(enchantLvl);
        target.setTicksFrozen(total);
    }

    @Override
    public @NotNull MapCodec<FreezeEffect> codec() {
        return CODEC;
    }
}
