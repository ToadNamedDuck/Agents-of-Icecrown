package com.toadnamedduck.agentsoficecrown.item;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.fluid.ModFluidTypes;
import com.toadnamedduck.agentsoficecrown.fluid.ModFluids;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items MOD_ITEMS = DeferredRegister.createItems(Constants.MODID);

    public static final DeferredItem<BucketItem> LIQUID_SARONITE_BUCKET = MOD_ITEMS.register("liquid_saronite_bucket", () ->
            new BucketItem(ModFluids.LIQUID_SARONITE_SOURCE.get(), new Item.Properties())
            );

    public static void register(IEventBus modEventBus){
        MOD_ITEMS.register(modEventBus);
    }
}
