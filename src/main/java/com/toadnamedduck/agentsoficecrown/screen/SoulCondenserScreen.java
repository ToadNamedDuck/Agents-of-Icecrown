package com.toadnamedduck.agentsoficecrown.screen;

import com.toadnamedduck.agentsoficecrown.menu.SoulCondenserMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class SoulCondenserScreen extends AbstractContainerScreen<SoulCondenserMenu> {
    public SoulCondenserScreen(SoulCondenserMenu menu, Inventory inv, Component title){
        super(menu, inv, title);
    }
    private static final int SQUARE_LENGTH = 18;

    @Override
    protected void renderBg(GuiGraphics g, float partialTick, int mouseX, int mouseY){
        g.fill(leftPos, topPos, leftPos + imageWidth, topPos + imageHeight, 0xFFC6C6C6);
        g.fill(leftPos + 56, topPos + 17, leftPos + 56 + SQUARE_LENGTH, topPos + 17 + SQUARE_LENGTH, 0xFF8B8B8B);//input
        g.fill(leftPos +56, topPos + 53, leftPos + 56 + SQUARE_LENGTH, topPos + 53 + SQUARE_LENGTH, 0xFF8B8B8B);//output

        //todo: Arrow
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick){
        super.render(g, mouseX, mouseY, partialTick);
        renderTooltip(g, mouseX, mouseY);
    }
}
