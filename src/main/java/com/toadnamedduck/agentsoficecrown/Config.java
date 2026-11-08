package com.toadnamedduck.agentsoficecrown;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue SOULBINDING_MAX_EFFECTIVE_SOULS;
    public static final ModConfigSpec.DoubleValue SOULBINDING_MAX_DAMAGE_BONUS;
    public static final ModConfigSpec.IntValue  COALESCED_SOULS_SOUL_VALUE;
    public static final ModConfigSpec.IntValue SOULBINDING_DEBUFF_HEALTH_PENALTY;

    static final ModConfigSpec SPEC;

    static{
        BUILDER.push("soulbinding");
        SOULBINDING_MAX_DAMAGE_BONUS = BUILDER
                .comment("The maximum bonus damage an awakened weapon can have at the maximum effective soul cap.")
                .translation("config.agentsoficecrown.soulbinding_max_bonus")
                .defineInRange("maxBonusDamage", 20.0, 0, 1000.0);
        SOULBINDING_MAX_EFFECTIVE_SOULS = BUILDER
                .comment("The number of souls at which an awakened weapon will have maximum bonus damage.")
                .translation("config.agentsoficecrown.soulbinding_effective_soul_cap")
                .defineInRange("effectiveSoulsCap", 1000, 1, 100000);
        SOULBINDING_DEBUFF_HEALTH_PENALTY = BUILDER
                .comment("The amount of health the player will lose if they don't keep a weapon bound to them on their person. 2 health = 1 heart.")
                .translation("config.agentsoficecrown.soulbinding_health_penalty")
                .defineInRange("healthPenalty", 4, 0, 18);
        BUILDER.pop();

        BUILDER.push("souls");
        COALESCED_SOULS_SOUL_VALUE = BUILDER
                .comment("Value in souls of Coalesced Souls items.")
                .translation("config.agentsoficecrown.coalesced_souls_value")
                .defineInRange("soulValue", 50, 1, 1000);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
