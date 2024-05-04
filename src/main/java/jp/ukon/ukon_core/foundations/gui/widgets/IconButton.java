package jp.ukon.ukon_core.foundations.gui.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import jp.ukon.ukon_core.AllUCGuiTextures;
import jp.ukon.ukon_core.foundations.gui.AbstractUWidget;
import jp.ukon.ukon_core.foundations.gui.IScreenElement;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

public class IconButton extends AbstractUWidget {
    protected IScreenElement icon;

    public IconButton(int x, int y, int width, int height, IScreenElement icon) {
        super(x, y, width, height);
        this.icon = icon;
    }

    @Override
    public void renderButton(@Nullable PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        if (!visible) return;

        AllUCGuiTextures button = active ? AllUCGuiTextures.BUTTON_DOWN : isHoveredOrFocused() ? AllUCGuiTextures.BUTTON_HOVER : AllUCGuiTextures.BUTTON_UP;

        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        drawBackground(stack, button);
        icon.render(stack, x + 1, y + 1);
    }

    private void drawBackground(PoseStack stack, AllUCGuiTextures texture) {
        AllUCGuiTextures.BUTTON_UP.bind();
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
