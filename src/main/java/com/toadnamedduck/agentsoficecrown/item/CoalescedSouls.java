package com.toadnamedduck.agentsoficecrown.item;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.datacomponent.SoulBinding;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.toadnamedduck.agentsoficecrown.ModTranslatableComponents.COALESCED_SOULS_NO_BINDING;
import static com.toadnamedduck.agentsoficecrown.ModTranslatableComponents.COALESCED_SOULS_TOOLTIP;
import static com.toadnamedduck.agentsoficecrown.datacomponent.ModDataComponentHelpers.SOUL_BINDING.findBinding;
import static com.toadnamedduck.agentsoficecrown.datacomponent.ModDataComponentHelpers.SOUL_BINDING.setSouls;

public class CoalescedSouls extends Item {

    public CoalescedSouls(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        ItemStack self = player.getItemInHand(usedHand);
        if(!player.isShiftKeyDown()) return InteractionResultHolder.pass(self);

        //Check item in opposite hand, make sure it has a soul binding component
        ItemStack other;
        if(usedHand == InteractionHand.MAIN_HAND) other = player.getItemInHand(InteractionHand.OFF_HAND);
        else other = player.getItemInHand(InteractionHand.MAIN_HAND);
        SoulBinding binding = findBinding(other);

        if(binding == null || !player.getUUID().equals(binding.owner())){
            if(!level.isClientSide()) player.displayClientMessage(COALESCED_SOULS_NO_BINDING.withStyle(ChatFormatting.RED), true);
            return InteractionResultHolder.fail(self);
        }

        if(!level.isClientSide){
            setSouls(other, binding.souls() + Constants.SOULS_PER_ITEM);
            self.consume(1, player);
            Vec3 look = player.getLookAngle();
            Vec3 spot = player.getEyePosition().add(look.scale(0.7)).add(0, -0.15, 0);
            if(level instanceof ServerLevel){
                ((ServerLevel) level).sendParticles(ParticleTypes.SOUL_FIRE_FLAME, spot.x, spot.y, spot.z, 5, 0.15, 0.15, 0.15, 0.02);
                level.playSound(null, spot.x, spot.y, spot.z, SoundEvents.SOUL_ESCAPE, SoundSource.PLAYERS, 1, 0.8f);
            }
        }
        return InteractionResultHolder.sidedSuccess(self, level.isClientSide());
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        tooltipComponents.add(COALESCED_SOULS_TOOLTIP);
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
