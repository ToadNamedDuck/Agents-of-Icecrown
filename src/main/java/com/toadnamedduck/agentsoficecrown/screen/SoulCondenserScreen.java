package com.toadnamedduck.agentsoficecrown.screen;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.menu.SoulCondenserMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class SoulCondenserScreen extends AbstractContainerScreen<SoulCondenserMenu> {
    public SoulCondenserScreen(SoulCondenserMenu menu, Inventory inv, Component title){
        super(menu, inv, title);
    }
    private static final int SQUARE_LENGTH = 18;
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Constants.MODID, "textures/gui/soul_condenser.png");
    private static final int ARROW_X = 102, ARROW_Y = 32, ARROW_W = 15, ARROW_H = 22;
    private static final int FILLED_U = 0, FILLED_V = 166;

    @Override
    protected void renderBg(GuiGraphics g, float partialTick, int mouseX, int mouseY){
        g.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);

        int h = menu.getScaledProgress(ARROW_H);
        if(h > 0){
            g.blit(TEXTURE, leftPos + ARROW_X, topPos + ARROW_Y, FILLED_U, FILLED_V, ARROW_W, h);
        }
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick){
        super.render(g, mouseX, mouseY, partialTick);
        renderTooltip(g, mouseX, mouseY);
    }
}
