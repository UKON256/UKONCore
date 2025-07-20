package jp.ukon.ukon_core.client.gui.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
public class CursorArea extends AbstractUWidget {
    protected long windowHandle;
    protected int cursorType;

    public CursorArea(int x, int y, int width, int height, int cursorType) {
        super(x, y, width, height);
        this.windowHandle = Minecraft.getInstance().getWindow().getWindow();
        this.cursorType = cursorType;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);

        if (isHovered)
            GLFW.glfwSetCursor(windowHandle, GLFW.glfwCreateStandardCursor(cursorType));

        if (wasHovered && !isHovered)
            GLFW.glfwSetCursor(windowHandle, GLFW.glfwCreateStandardCursor(GLFW.GLFW_ARROW_CURSOR));
    }

    public void setCursorType(int cursorType) {
        this.cursorType = cursorType;
    }
}
