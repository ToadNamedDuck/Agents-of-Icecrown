package com.toadnamedduck.agentsoficecrown.sound;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.neoforge.common.util.DeferredSoundType;

public class ModSoundTypes {
    //Volume, pitch, break, step, place, hit, fall
    public static final DeferredSoundType ALTAR = new DeferredSoundType(
          1.0f,
          1.0f,
            () -> SoundEvents.SOUL_SAND_BREAK,
            () -> SoundEvents.DEEPSLATE_TILES_STEP,
            () -> SoundEvents.SOUL_SAND_PLACE,
            () -> SoundEvents.DEEPSLATE_TILES_HIT,
            () -> SoundEvents.DEEPSLATE_TILES_FALL
    );
}
