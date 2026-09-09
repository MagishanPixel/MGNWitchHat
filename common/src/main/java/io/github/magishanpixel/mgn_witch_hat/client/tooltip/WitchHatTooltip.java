package io.github.magishanpixel.mgn_witch_hat.client.tooltip;

import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import io.github.magishanpixel.mgn_witch_hat.misc.DataDecor;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import java.util.List;

public class WitchHatTooltip implements ClientTooltipComponent {
    private final DisplayStacks display;
    private static final ResourceLocation TEX_SLOT = MGNConstants.newId("textures/gui/witch_hat_tooltip_slot.png");

    public WitchHatTooltip(DisplayStacks display) {
        this.display = display;
    }

    @Override
    public int getWidth(Font font) {
        return 22 * Math.min(display.dataList().size(), 4);
    }

    @Override
    public int getHeight() {
        return 22 * (Math.max(1, (int) Math.ceil(((double) display.dataList().size())/4d)));
    }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics graphics) {
        List<DataDecor> stackList = display.dataList();
        int b = 0;
        int yCount = 0;

        for (int i = 0; i < stackList.size(); i++) {
            int posX = x + (22 * b);
            int posY = y + (22 * yCount);
            graphics.blit(TEX_SLOT, posX, posY, 0, 0, 22, 22, 22, 22);
            graphics.renderItem(stackList.get(i).stack(), posX + 3, posY + 3);
            b++;
            if (b >= 4) {
                b = 0;
                yCount++;
            }
        }
    }

    public record DisplayStacks(List<DataDecor> dataList) implements TooltipComponent {}


}
