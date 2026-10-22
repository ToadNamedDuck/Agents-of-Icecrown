package com.toadnamedduck.agentsoficecrown.attachment;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.datacomponent.ModDataComponentHelpers;
import com.toadnamedduck.agentsoficecrown.datacomponent.SoulBinding;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import static com.toadnamedduck.agentsoficecrown.datacomponent.ModDataComponentHelpers.SOUL_BINDING.findBinding;

public class SoulBoundDebuffHelpers {
    private static final ResourceLocation DEBUFF_ID = ResourceLocation.fromNamespaceAndPath(Constants.MODID, "soulbinding_debuff");

    public static void addOrRemoveDebuff(ServerPlayer player){
        boolean debuff = player.getData(ModAttachments.BOUND) && !holdingBoundWeapon(player);
        setModifier(player, Attributes.MAX_HEALTH, DEBUFF_ID, -4.0, AttributeModifier.Operation.ADD_VALUE, debuff);
        setModifier(player, Attributes.ATTACK_DAMAGE, DEBUFF_ID, -0.10, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, debuff);
    }

    private static boolean holdingBoundWeapon(Player player){
        SoulBinding oItem = findBinding(player.getOffhandItem());
        for(ItemStack item: player.getInventory().items){
            SoulBinding iBinding = findBinding(item);
            if(iBinding != null && iBinding.owner().equals(player.getUUID())){
                return true;
            }
        }
        if(oItem != null && oItem.owner().equals(player.getUUID())) return true;
        return false;
    }

    private static void setModifier(Player player, Holder<Attribute> attribute, ResourceLocation modifierID, double amount, AttributeModifier.Operation operation, boolean debuff){
        AttributeInstance instance = player.getAttribute(attribute);
        if(instance != null){
            if(debuff && !instance.hasModifier(modifierID)){
                instance.addTransientModifier(new AttributeModifier(modifierID, amount, operation));
            }
            if(!debuff && instance.hasModifier(modifierID)){
                instance.removeModifier(modifierID);
            }
        }
    }
}
