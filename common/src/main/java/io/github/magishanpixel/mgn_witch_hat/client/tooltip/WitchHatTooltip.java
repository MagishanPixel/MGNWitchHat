package io.github.magishanpixel.mgn_witch_hat.client.tooltip;

import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class WitchHatTooltip implements ClientTooltipComponent {
    private final DisplayStacks display;
    private static final ResourceLocation TEX_SLOT = MGNConstants.newId("textures/gui/witch_hat_tooltip_slot.png");

    public WitchHatTooltip(DisplayStacks display) {
        this.display = display;
    }

    @Override
    public int getWidth(Font font) {
        return 24 * display.stackList().size();
    }

    @Override
    public int getHeight() {
        return 24 * (Math.max(1, (int) Math.floor(((double) display.stackList().size())/3d)));
    }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics graphics) {
        List<ItemStack> stackList = display.stackList();

        for (int i = 0; i < stackList.size(); i++) {
            int posX = x + (24 * i);
            graphics.blit(TEX_SLOT, posX, y, 0, 0, 24, 24, 24, 24);
            graphics.renderItem(stackList.get(i), posX + 4, y + 4);
        }
    }

    public record DisplayStacks(List<ItemStack> stackList) implements TooltipComponent {}


}
