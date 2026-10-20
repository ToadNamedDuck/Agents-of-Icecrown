package com.toadnamedduck.agentsoficecrown.datacomponent;

import com.toadnamedduck.agentsoficecrown.ModTags;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Unbreakable;

public class ModDataComponentHelpers {
    public static class SOUL_BINDING{
        //Soul Binding is a process that needs to be applied by both Altar and Runeforge eventually - helper class makes sure I can do that cleanly
        private static int SPENT_THRESHOLD = 3;

        public static void setSouls(ItemStack stack, int souls){
            SoulBinding binding = findBinding(stack);
            if(binding != null){
                stack.set(ModDataComponents.SOUL_BINDING.get(), binding.withSouls(souls));
            }
        }

        public static void awaken(ItemStack stack, Player player){
            SoulBinding binding = findBinding(stack);
            if(binding == null && stack.is(ModTags.ITEMS.MELEE_WEAPON)){
                stack.set(ModDataComponents.SOUL_BINDING.get(), new SoulBinding(player.getUUID(), 0, false));
                refreshDurabilityState(stack);
                //Could also give it an enchant glint, or change the model/texture of the weapon
            }
        }

        public static SoulBinding findBinding(ItemStack stack){
            return stack.get(ModDataComponents.SOUL_BINDING.get());
        }

        public static boolean isSpent(ItemStack stack){
            SoulBinding binding = findBinding(stack);
            if(binding == null) return false;

            int max = stack.getMaxDamage();
            if(max <= 0) return false;

            return max - stack.getDamageValue() <= SPENT_THRESHOLD;
        }

        public static void refreshDurabilityState(ItemStack stack){
            SoulBinding binding = findBinding(stack);
            if(binding == null) return;
            if(isSpent(stack)){
                if(!stack.has(DataComponents.UNBREAKABLE)){
                    stack.set(ModDataComponents.SOUL_BINDING.get(), binding.withSpent(true));
                    stack.set(DataComponents.UNBREAKABLE, new Unbreakable(false));
                }
            }
            else{
                if(binding.spent()){
                    stack.set(ModDataComponents.SOUL_BINDING.get(), binding.withSpent(false));
                    stack.remove(DataComponents.UNBREAKABLE);
                }
            }
        }
    }
}
