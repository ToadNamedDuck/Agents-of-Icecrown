package com.toadnamedduck.agentsoficecrown.event;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.ModTags;
import com.toadnamedduck.agentsoficecrown.datacomponent.ModDataComponents;
import com.toadnamedduck.agentsoficecrown.datacomponent.SoulBinding;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.Arrays;
import java.util.List;

import static com.toadnamedduck.agentsoficecrown.ModTranslatableComponents.INERT;
import static com.toadnamedduck.agentsoficecrown.ModTranslatableComponents.SOULBINDING_OWNER_MISMATCH;
import static com.toadnamedduck.agentsoficecrown.datacomponent.ModDataComponentHelpers.SOUL_BINDING.findBinding;

@EventBusSubscriber(modid = Constants.MODID)
public class ModEvents {

    private static ResourceLocation SOUL_DAMAGE_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(Constants.MODID, "soul_damage");

    @SubscribeEvent
    public static void onToolTip(ItemTooltipEvent tooltipEvent){
        ItemStack item = tooltipEvent.getItemStack();

        if(!item.is(ModTags.ITEMS.SARONITE_WEAPONS)) return;

        List<Component> tooltip = tooltipEvent.getToolTip();
        SoulBinding binding = findBinding(item);

        if(binding == null){
            tooltip.add(INERT.withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
        else{
            tooltip.add(Component.translatable("tooltip.agentsoficecrown.souls", binding.souls()).withStyle(ChatFormatting.AQUA));

            Player holdingPlayer = tooltipEvent.getEntity();
            if(holdingPlayer != null && !holdingPlayer.getUUID().equals(binding.owner())){
                tooltip.add(SOULBINDING_OWNER_MISMATCH.withStyle(ChatFormatting.LIGHT_PURPLE).withStyle(ChatFormatting.ITALIC));
            }
        }
    }

    @SubscribeEvent
    public static void onItemAttributeModifierEvent(ItemAttributeModifierEvent event){
        ItemStack item = event.getItemStack();
        SoulBinding binding = findBinding(item);

        if(binding != null){
            double bonusDMG = SoulBinding.bonusDamage(binding.souls());
            event.addModifier(Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(SOUL_DAMAGE_MODIFIER_ID, bonusDMG, AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.MAINHAND
            );
        }
    }
}
