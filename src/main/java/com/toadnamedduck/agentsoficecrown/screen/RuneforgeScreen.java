package com.toadnamedduck.agentsoficecrown.screen;

import com.toadnamedduck.agentsoficecrown.Constants;
import com.toadnamedduck.agentsoficecrown.ModTranslatableComponents;
import com.toadnamedduck.agentsoficecrown.enchant.ModEnchants;
import com.toadnamedduck.agentsoficecrown.menu.RuneforgeMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import static com.toadnamedduck.agentsoficecrown.ModTranslatableComponents.*;

public class RuneforgeScreen extends AbstractContainerScreen<RuneforgeMenu> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Constants.MODID, "textures/gui/runeforge.png");
    private Button awakenButton;
    private Button repairButton;
    private Button razoriceButton;
    private Button fallenCrusaderButton;
    private Button stoneskinGargoyleButton;

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
        razoriceButton.active = menu.canApplyEnchant(ModEnchants.RAZORICE);
        fallenCrusaderButton.active = menu.canApplyEnchant(ModEnchants.FALLEN_CRUSADER);
        stoneskinGargoyleButton.active = menu.canApplyEnchant(ModEnchants.STONESKIN_GARGOYLE);
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
                .bounds(leftPos + 15, topPos + 22, 56, 20)
                .build());

        repairButton = addRenderableWidget(Button.builder(
                        RUNEFORGE_REPAIR_BUTTON,
                        b -> minecraft.gameMode.handleInventoryButtonClick(menu.containerId, RuneforgeMenu.REPAIR_BUTTON))
                .bounds(leftPos + 15, topPos + 44, 56, 20)
                .build());

        razoriceButton = addRenderableWidget(Button.builder(
                        ENCHANT_RAZORICE,
                b -> minecraft.gameMode.handleInventoryButtonClick(menu.containerId, RuneforgeMenu.RUNEFORGES_START))
                .bounds(leftPos + 105, topPos + 11, 56, 20)
                .build());

        fallenCrusaderButton = addRenderableWidget(Button.builder(
                ENCHANT_FALLEN_CRUSADER,
                b -> minecraft.gameMode.handleInventoryButtonClick(menu.containerId, RuneforgeMenu.FALLEN_CRUSADER))
                .bounds(leftPos + 105, topPos + 33, 56, 20)
                .build());

        stoneskinGargoyleButton = addRenderableWidget(Button.builder(
                ENCHANTMENT_STONESKIN_GARGOYLE,
                b -> minecraft.gameMode.handleInventoryButtonClick(menu.containerId, RuneforgeMenu.STONESKIN_GARGOYLE))
                .bounds(leftPos + 105, topPos + 55, 56, 20)
                .build());


        awakenButton.setTooltip(Tooltip.create(RUNEFORGE_AWAKEN_TOOLTIP));
        repairButton.setTooltip(Tooltip.create(RUNEFORGE_REPAIR_TOOLTIP));
        razoriceButton.setTooltip(Tooltip.create(ENCHANT_RAZORICE));
        fallenCrusaderButton.setTooltip(Tooltip.create(ENCHANT_FALLEN_CRUSADER));
        stoneskinGargoyleButton.setTooltip(Tooltip.create(ENCHANTMENT_STONESKIN_GARGOYLE));
    }
}
