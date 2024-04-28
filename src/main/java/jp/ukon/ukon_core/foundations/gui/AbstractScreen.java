package jp.ukon.ukon_core.foundations.gui;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractScreen extends Screen {
    protected AbstractScreen() {
        super(Component.empty());
    }
    protected AbstractScreen(Component title) {
        super(title);
    }

    protected <T extends GuiEventListener & Widget & NarratableEntry> void addRenderableWidgets(T... widgets) {
        for (T widget : widgets) {
            addRenderableWidget(widget);
        }
    }
    protected void removeWidgets(GuiEventListener... widgets) {
        for (GuiEventListener widget : widgets) {
            removeWidget(widget);
        }
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        partialTicks = minecraft.getFrameTime();

        stack.pushPose();

        prepareFrame();

        renderWindowBackground(stack, mouseX, mouseY, partialTicks);
        renderWindow(stack, mouseX, mouseY, partialTicks);
        super.render(stack, mouseX, mouseY, partialTicks);
        renderWindowForeground(stack, mouseX, mouseY, partialTicks);

        endFrame();

        stack.popPose();
    }

    protected void prepareFrame() {
    }
    protected void renderWindowBackground(PoseStack stack, int mouseX, int mouseY, float PartialTicks) {
        renderBackground(stack);
    }
    protected abstract void renderWindow(PoseStack stack, int mouseX, int mouseY, float PartialTicks);
    protected void renderWindowForeground(PoseStack stack, int mouseX, int mouseY, float PartialTicks) {
        for (Widget widget : renderables) {
            /*if (widget instanceof AbstractWidget abstractWidget && widget.isHoveredOrFocused() && abstractWidget.visible) {
                List<Component> tooltip = abstractWidget.getToolTip();
                if (tooltip.isEmpty())
                    continue;
                int ttx = abstractWidget.lockedTooltipX == -1 ? mouseX : simiWidget.lockedTooltipX + simiWidget.x;
                int tty = abstractWidget.lockedTooltipY == -1 ? mouseY : simiWidget.lockedTooltipY + simiWidget.y;
                renderComponentTooltip(stack, tooltip, ttx, tty);
            }*/
        }
    }
    protected void endFrame() {
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        boolean keyPressed = super.keyPressed(keyCode, scanCode, modifiers);
        if (keyPressed || getFocused() != null)
            return keyPressed;

        InputConstants.Key mouseKey = InputConstants.getKey(keyCode, scanCode);
        if (this.minecraft.options.keyInventory.isActiveAndMatches(mouseKey)) {
            this.onClose();
            return true;
        }

        return false;
    }

    public void fadeIn() {

    }
    public void fadeOut() {

    }

    @Override
    public void tick() {
        for (GuiEventListener listener : children()) {
            if (!(listener instanceof IGuiTickEventListener i)) continue;
            i.tick();
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}