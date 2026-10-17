package com.toadnamedduck.agentsoficecrown.event;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.ModTags;
import com.toadnamedduck.agentsoficecrown.datacomponent.ModDataComponents;
import com.toadnamedduck.agentsoficecrown.datacomponent.SoulBinding;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.List;

@EventBusSubscriber(modid = Constants.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void onToolTip(ItemTooltipEvent tooltipEvent){
        ItemStack item = tooltipEvent.getItemStack();

        if(!item.is(ModTags.ITEMS.SARONITE_WEAPONS)) return;

        List<Component> tooltip = tooltipEvent.getToolTip();
        SoulBinding binding = item.get(ModDataComponents.SOUL_BINDING.get());

        if(binding == null){
            tooltip.add(Component.translatable("tooltip.agentsoficecrown.inert").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
        else{
            tooltip.add(Component.translatable("tooltip.agentsoficecrown.souls", binding.souls()).withStyle(ChatFormatting.AQUA));

            Player holdingPlayer = tooltipEvent.getEntity();
            if(holdingPlayer != null && !holdingPlayer.getUUID().equals(binding.owner())){
                tooltip.add(Component.translatable("tooltip.agentsoficecrown.soulbinding_owner_mismatch").withStyle(ChatFormatting.LIGHT_PURPLE).withStyle(ChatFormatting.ITALIC));
            }
        }
    }

}
