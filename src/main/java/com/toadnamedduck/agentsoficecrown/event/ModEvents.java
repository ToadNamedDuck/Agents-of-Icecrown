package com.toadnamedduck.agentsoficecrown.event;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.ModTags;
import com.toadnamedduck.agentsoficecrown.attachment.ModAttachments;
import com.toadnamedduck.agentsoficecrown.attachment.SoulBoundDebuffHelpers;
import com.toadnamedduck.agentsoficecrown.datacomponent.ModDataComponentHelpers;
import com.toadnamedduck.agentsoficecrown.datacomponent.SoulBinding;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.util.FakePlayer;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.List;

import static com.toadnamedduck.agentsoficecrown.ModTranslatableComponents.*;
import static com.toadnamedduck.agentsoficecrown.datacomponent.ModDataComponentHelpers.SOUL_BINDING.*;

@EventBusSubscriber(modid = Constants.MODID)
public class ModEvents {

    private static ResourceLocation SOUL_DAMAGE_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(Constants.MODID, "soul_damage");
    private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
    private static final int SARONITE_ARMOR_COOLDOWN_TICKS = 2400;

    @SubscribeEvent
    public static void onToolTip(ItemTooltipEvent tooltipEvent){
        ItemStack item = tooltipEvent.getItemStack();

        if(!item.is(ModTags.ITEMS.MELEE_WEAPON)) return;

        List<Component> tooltip = tooltipEvent.getToolTip();
        SoulBinding binding = findBinding(item);

        if(binding == null && item.is(ModTags.ITEMS.SARONITE_WEAPONS)){
            tooltip.add(INERT.withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
        if(binding != null){
            tooltip.add(Component.translatable("tooltip.agentsoficecrown.souls", binding.souls(), SoulBinding.bonusDamage(binding.souls())).withStyle(ChatFormatting.AQUA));

            Player holdingPlayer = tooltipEvent.getEntity();
            if(holdingPlayer != null && !holdingPlayer.getUUID().equals(binding.owner())){
                tooltip.add(SOULBINDING_OWNER_MISMATCH.withStyle(ChatFormatting.LIGHT_PURPLE).withStyle(ChatFormatting.ITALIC));
            }
            if(isSpent(item)){
                tooltip.add(SOULBINDING_DURABILITY_SPENT.withStyle(ChatFormatting.RED));
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
        refreshDurabilityState(event.getTo());
        if(binding != null && binding.owner().equals(player.getUUID()) && !isSpent(event.getTo())){
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

    @SubscribeEvent
    public static void onItemAttributeEvent(ItemAttributeModifierEvent event){
        if(!ModDataComponentHelpers.SOUL_BINDING.isSpent(event.getItemStack())) return;

        for (var entry: List.copyOf(event.getModifiers())){
            if(entry.attribute().equals(Attributes.ATTACK_DAMAGE)){
                event.replaceModifier(entry.attribute(),
                        new AttributeModifier(entry.modifier().id(), 0, entry.modifier().operation()),
                        entry.slot()
                );
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event){
        if(event.getEntity() instanceof ServerPlayer player && player.tickCount % 20 == 0){
            SoulBoundDebuffHelpers.addOrRemoveDebuff(player);
        }
    }

    private static boolean hasFullSet(Player player){
        Iterable<ItemStack> armor = player.getArmorSlots();
        for(ItemStack item : armor){
            if(!item.is(ModTags.ITEMS.SARONITE_ARMOR)) return false;
        }
        return true;
    }

    @SubscribeEvent
    public static void onPlayerHurt(LivingDamageEvent.Pre event){
        if(!(event.getEntity() instanceof ServerPlayer player) || player instanceof FakePlayer) return;
        if(event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) return;
        if(player.getHealth() - event.getNewDamage() > 0) return;//Only saving on a lethal hit
        if(!hasFullSet(player)) return;
        Iterable<ItemStack> armor = player.getArmorSlots();

        for(ItemStack item : armor){
            if(player.getCooldowns().isOnCooldown(item.getItem())) return;
        }

        event.setNewDamage(0);
        player.heal(10);

        for (EquipmentSlot slot : ARMOR_SLOTS){
            ItemStack item = player.getItemBySlot(slot);
            item.hurtAndBreak((int) (item.getMaxDamage() * 0.15f), player, slot);
        }

        startCooldown(player, SARONITE_ARMOR_COOLDOWN_TICKS);

        //Particle shower, sound, etc.
        ServerLevel level = player.serverLevel();
        level.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, player.getX(), player.getY() + 1, player.getZ(), 40, 0.4, 0.25, 0.4, 0.15);
        level.playSound(null, player.getX(), player.getY() + 1, player.getZ(), SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1, -0.84f);
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event){
        restoreVisualCooldowns(event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event){
        restoreVisualCooldowns(event.getEntity());
    }

    private static void startCooldown(ServerPlayer player, int ticks){
        player.setData(ModAttachments.SARONITE_ARMOR_COOLDOWN.get(), player.serverLevel().getGameTime() + ticks);
        showSaroniteCooldown(player, ticks);
    }

    private static void showSaroniteCooldown(ServerPlayer player, int ticks){
        for(EquipmentSlot slot : ARMOR_SLOTS){
            ItemStack item = player.getItemBySlot(slot);
            if(item.is(ModTags.ITEMS.SARONITE_ARMOR)){
                player.getCooldowns().addCooldown(item.getItem(), ticks);
            }
        }
    }

    private static void restoreVisualCooldowns(Player player){
        if(!(player instanceof ServerPlayer sPlayer)) return;
        long remainder = sPlayer.getData(ModAttachments.SARONITE_ARMOR_COOLDOWN) - sPlayer.serverLevel().getGameTime();
        if(remainder > 0) showSaroniteCooldown( sPlayer, (int) remainder);
    }
}