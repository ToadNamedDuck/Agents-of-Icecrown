package com.toadnamedduck.agentsoficecrown.datacomponent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ModDataComponentHelpers {
    public static class SOUL_BINDING{
        //Soul Binding is a process that needs to be applied by both Altar and Runeforge eventually - helper class makes sure I can do that cleanly

        public static void setSouls(ItemStack stack, int souls){
            SoulBinding binding = findBinding(stack);
            if(binding != null){
                stack.set(ModDataComponents.SOUL_BINDING.get(), binding.withSouls(souls));
            }
        }

        public static void awaken(ItemStack stack, Player player){
            SoulBinding binding = findBinding(stack);
            if(binding == null){
                stack.set(ModDataComponents.SOUL_BINDING.get(), new SoulBinding(player.getUUID(), 0));
                //Could also give it an enchant glint, or change the model/texture of the weapon
            }
        }

        public static SoulBinding findBinding(ItemStack stack){
            return stack.get(ModDataComponents.SOUL_BINDING.get());
        }
    }
}
