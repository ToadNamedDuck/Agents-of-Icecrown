package com.toadnamedduck.agentsoficecrown.event;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.ModTags;
import com.toadnamedduck.agentsoficecrown.datacomponent.ModDataComponentHelpers;
import com.toadnamedduck.agentsoficecrown.datacomponent.SoulBinding;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.util.FakePlayer;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

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
            tooltip.add(Component.translatable("tooltip.agentsoficecrown.souls", binding.souls(), SoulBinding.bonusDamage(binding.souls())).withStyle(ChatFormatting.AQUA));

            Player holdingPlayer = tooltipEvent.getEntity();
            if(holdingPlayer != null && !holdingPlayer.getUUID().equals(binding.owner())){
                tooltip.add(SOULBINDING_OWNER_MISMATCH.withStyle(ChatFormatting.LIGHT_PURPLE).withStyle(ChatFormatting.ITALIC));
            }
        }
    }

    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent event){
        if(event.getSlot() != EquipmentSlot.MAINHAND) return;
        if(!(event.getEntity() instanceof ServerPlayer player) || player instanceof FakePlayer) return;

        AttributeInstance dmg = player.getAttribute(Attributes.ATTACK_DAMAGE);
        if(dmg == null) return;

        SoulBinding binding = findBinding(event.getTo());
        if(binding != null && binding.owner().equals(player.getUUID())){
            double bonusDMG = SoulBinding.bonusDamage(binding.souls());
            dmg.addOrUpdateTransientModifier(new AttributeModifier(SOUL_DAMAGE_MODIFIER_ID, bonusDMG, AttributeModifier.Operation.ADD_VALUE));
        }
        else{
            dmg.removeModifier(SOUL_DAMAGE_MODIFIER_ID);
        }
    }

    @SubscribeEvent
    public static void onLivingDeathEvent(LivingDeathEvent event){
        //Adding souls on kill to soulbound weapon

        Entity killer = event.getSource().getEntity();
        if(!(killer instanceof ServerPlayer) || killer instanceof FakePlayer) return;
        //Make sure the kill is from melee
        if(!(event.getSource().getDirectEntity() == killer)) return;

        ItemStack item = killer.getWeaponItem();
        SoulBinding binding = findBinding(item);
        if(item.isEmpty() || binding == null || !(binding.owner().equals(killer.getUUID()))) return;

        int victimHP = Mth.floor(event.getEntity().getMaxHealth());
        ModDataComponentHelpers.SOUL_BINDING.setSouls(item, binding.souls() + (victimHP/20));
    }
}
