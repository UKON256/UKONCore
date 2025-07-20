package jp.ukon.ukon_core.client.gui.screen;

import jp.ukon.ukon_core.client.gui.IEGuiEventListener;
import jp.ukon.ukon_core.client.gui.ITickableGuiEventListener;
import jp.ukon.ukon_core.client.gui.widget.AbstractUWidget;
import jp.ukon.ukon_core.util.maintain.Components;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractUScreen extends Screen {
    public AbstractUScreen() {
        this(Components.empty());
    }
    public AbstractUScreen(Component title) {
        super(title);
    }


    @Override
    public void tick() {
        for (GuiEventListener listener : children()) {
            if (listener instanceof ITickableGuiEventListener tickable)
                tickable.tick();
        }
    }

    @Override
    public void onClose() {
        for (GuiEventListener listener : children()) {
            if (listener instanceof IEGuiEventListener extended)
                extended.onScreenClosed();
        }
        super.onClose();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        partialTicks = minecraft.getFrameTime();
        graphics.pose().pushPose();

        prepareFrame();
        renderBackground(graphics, mouseX, mouseY, partialTicks);
        renderWindow(graphics, mouseX, mouseY, partialTicks);
        super.render(graphics, mouseX, mouseY, partialTicks);
        renderForeground(graphics, mouseX, mouseY, partialTicks);
        endFrame();

        graphics.pose().popPose();
    }

    protected void prepareFrame() {
    }

    protected void renderBackground(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        renderBackground(graphics);
    }

    protected abstract void renderWindow(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks);

    protected void renderForeground(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
    }

    protected void endFrame() {
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        boolean keyPressed = super.keyPressed(keyCode, scanCode, modifiers);
        if (keyPressed || getFocused() != null)
            return keyPressed;

        if (this.minecraft.options.keyInventory.matches(keyCode, scanCode)) {
            this.onClose();
            return true;
        }

        boolean consumed = false;

        for (GuiEventListener widget : children()) {
            if (widget instanceof AbstractUWidget simiWidget) {
                if (simiWidget.keyPressed(keyCode, scanCode, modifiers))
                    consumed = true;
            }
        }

        return consumed;
    }

    @Nullable
    @Override
    public GuiEventListener getFocused() {
        GuiEventListener focused = super.getFocused();
        if (focused instanceof AbstractWidget && !((AbstractWidget) focused).isFocused())
            focused = null;
        setFocused(focused);
        return focused;
    }
}
