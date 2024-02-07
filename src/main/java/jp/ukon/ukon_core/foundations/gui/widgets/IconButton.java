package jp.ukon.ukon_core.foundations.gui.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import jp.ukon.ukon_core.foundations.gui.AbstractWidget;
import jp.ukon.ukon_core.foundations.gui.GuiTextures;
import jp.ukon.ukon_core.foundations.gui.IScreenElement;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

public class IconButton extends AbstractWidget {
    protected IScreenElement icon;

    public IconButton(int x, int y, int width, int height, IScreenElement icon) {
        super(x, y, width, height);
        this.icon = icon;
    }

    @Override
    public void renderButton(@Nullable PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        if (!visible) return;

        GuiTextures button = active ? GuiTextures.BUTTON_DOWN : isHoveredOrFocused() ? GuiTextures.BUTTON_HOVER : GuiTextures.BUTTON_UP;

        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        drawBackground(stack, button);
        icon.render(stack, x + 1, y + 1);
    }

    private void drawBackground(PoseStack stack, GuiTextures texture) {
        GuiTextures.BUTTON_UP.bind();
        blit(stack, x, y, texture.startX, texture.startY, texture.width, texture.height);
    }

    public void setTooltip(Component text) {
        toolTip.clear();
        toolTip.add(text);
    }

    public void setIcon(IScreenElement icon) {
        this.icon = icon;
    }
}
