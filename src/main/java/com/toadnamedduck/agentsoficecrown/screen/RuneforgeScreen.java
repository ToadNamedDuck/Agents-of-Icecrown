package com.toadnamedduck.agentsoficecrown.screen;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.menu.RuneforgeMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import static com.toadnamedduck.agentsoficecrown.ModTranslatableComponents.RUNEFORGE_AWAKEN_BUTTON;
import static com.toadnamedduck.agentsoficecrown.ModTranslatableComponents.RUNEFORGE_REPAIR_BUTTON;

public class RuneforgeScreen extends AbstractContainerScreen<RuneforgeMenu> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Constants.MODID, "textures/gui/runeforge.png");
    private Button awakenButton;
    private Button repairButton;

    public RuneforgeScreen(RuneforgeMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics g, float partialTick, int mouseX, int mouseY) {
        g.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        awakenButton.active = menu.canAwaken();
        repairButton.active = menu.canRepair();
        super.render(g, mouseX, mouseY, partialTick);
        renderTooltip(g, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        //Adding some buttons
        awakenButton = addRenderableWidget(Button.builder(
                        RUNEFORGE_AWAKEN_BUTTON,
                        b -> minecraft.gameMode.handleInventoryButtonClick(menu.containerId, RuneforgeMenu.AWAKEN_BUTTON))
                .bounds(leftPos + 10, topPos + 22, 56, 20)
                .build());

        repairButton = addRenderableWidget(Button.builder(
                        RUNEFORGE_REPAIR_BUTTON,
                        b -> minecraft.gameMode.handleInventoryButtonClick(menu.containerId, RuneforgeMenu.REPAIR_BUTTON))
                .bounds(leftPos + 10, topPos + 44, 56, 20)
                .build());

    }
}
