package com.toadnamedduck.agentsoficecrown.datacomponent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;

import java.util.UUID;

public record SoulBinding(UUID owner, int souls){

    //Codec for how to read the data
    public static final Codec<SoulBinding> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    UUIDUtil.CODEC.fieldOf("owner").forGetter(SoulBinding::owner),
                    ExtraCodecs.NON_NEGATIVE_INT.optionalFieldOf("souls", 0).forGetter(SoulBinding::souls)
            ).apply(instance, SoulBinding::new)
    );

    //Stream Codec is related to data transfer between server/client
    //This tells the byte reader what order this data comes out in, in short.
    public static final StreamCodec<ByteBuf, SoulBinding> STREAM_CODEC = StreamCodec.composite(
        UUIDUtil.STREAM_CODEC, SoulBinding::owner,
            ByteBufCodecs.VAR_INT, SoulBinding::souls,
            SoulBinding::new
    );

    private static double roundToHalf(double value){
        return Math.round(value*2.0)/2.0;
    }

    public static double bonusDamage(int souls){
        //TODO: Add config for number of souls to hit dmg cap, and config for max bonus
        //rn the cap is gonna just be 1000, for a max of 21 bonus dmg
        double fraction = Math.sqrt(Math.min(souls, 1000)/(double)1000);
        return roundToHalf(21.0 * fraction);
    }

    //Helper for creating a new record for the weapons (To update the soul counter)
    public SoulBinding withSouls(int souls){
        return new SoulBinding(owner, souls);
    }
}
