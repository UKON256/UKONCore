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
public abstract class AbstractUScreen extends Screen {
    protected AbstractUScreen() {
        super(Component.empty());
    }
    protected AbstractUScreen(Component title) {
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
            if (!(widget instanceof AbstractUWidget uWidget && uWidget.isHoveredOrFocused() && uWidget.visible))
                continue;

            List<Component> tooltip = uWidget.getTooltip();
            if (tooltip.isEmpty())
                continue;

            int ttx = uWidget.lockedTooltipX == -1 ? mouseX : uWidget.lockedTooltipX + uWidget.x;
            int tty = uWidget.lockedTooltipY == -1 ? mouseY : uWidget.lockedTooltipY + uWidget.y;
            renderComponentTooltip(stack, tooltip, ttx, tty);
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

    @Override
    public void tick() {
        for (GuiEventListener listener : children()) {
            if (listener instanceof IGuiTickEventListener tick)
                tick.tick();
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}