package com.toadnamedduck.agentsoficecrown.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class AltarBlockEntityRenderer implements BlockEntityRenderer<AltarBlockEntity> {

    private final ItemRenderer itemRenderer;
    private final float bobSpeed = (float) ((2*Math.PI)/60f);

    public AltarBlockEntityRenderer(BlockEntityRendererProvider.Context context){
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(AltarBlockEntity altarBlockEntity, float partialTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int combinedLight, int combinedOverlay) {
        if(!(altarBlockEntity.getLevel() == null) && !(altarBlockEntity.getItemStack().isEmpty()) ){
            long tick = altarBlockEntity.getLevel().getGameTime();
            float cycleTime = (tick % 240L) + partialTicks;
            float degrees = cycleTime * 1.5f;

            poseStack.pushPose();

            ItemStack altarItem = altarBlockEntity.getItemStack();
            BakedModel altarItemModel = this.itemRenderer.getModel(altarItem, altarBlockEntity.getLevel(), null, (int) altarBlockEntity.getBlockPos().asLong());

            //bobbing + translation
            float sinOffset = (Mth.sin((cycleTime * bobSpeed)));
            poseStack.translate(0.5, 0.75+sinOffset*0.125, 0.5);
            
            //rotation
            poseStack.mulPose(Axis.YP.rotationDegrees(degrees));


            itemRenderer.render(altarItem, ItemDisplayContext.GROUND, false, poseStack, multiBufferSource, combinedLight, combinedOverlay, altarItemModel);
            poseStack.popPose();
        }
    }
}
