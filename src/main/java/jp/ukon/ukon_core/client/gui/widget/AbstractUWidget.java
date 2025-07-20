package jp.ukon.ukon_core.client.gui.widget;

import jp.ukon.ukon_core.client.gui.IEGuiEventListener;
import jp.ukon.ukon_core.client.gui.ITickableGuiEventListener;
import jp.ukon.ukon_core.util.maintain.Components;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.client.gui.screens.inventory.tooltip.DefaultTooltipPositioner;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractUWidget extends AbstractWidget implements ITickableGuiEventListener, IEGuiEventListener {
    public boolean wasHovered = false;
    public float z;
    protected boolean isDragging = false;
    protected BiConsumer<Integer, Integer> onClick = ($, $$) -> { };
    protected List<Component> toolTip = new ArrayList<>();

    public AbstractUWidget(int x, int y, int width, int height) {
        super(x, y, width, height, Components.empty());
    }
    public AbstractUWidget(int x, int y, int width, int height, Component message) {
        super(x, y, width, height, message);
    }
    public AbstractUWidget(int x, int y, Component tooltip) {
        super(x, y, 16, 16, tooltip);
    }

    @Override
    protected ClientTooltipPositioner createTooltipPositioner() {
        return DefaultTooltipPositioner.INSTANCE;
    }

    public <T extends AbstractUWidget> T at(int x, int y, float z) {
        this.setX(x);
        this.setY(y);
        this.z = z;
        return (T) this;
    }

    public <T extends AbstractUWidget> T setActive(boolean active) {
        this.active = active;
        return (T) this;
    }

    @Override
    public void tick() {
    }

    /**
     * #AbstractUScreenかつそこに実装されている際、Screenが閉じられたときのみ実行されます
     */
    @Override
    public void onScreenClosed() {

    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        isDragging = true;
        runCallback(mouseX, mouseY);
    }

    @Override
    public void onRelease(double pMouseX, double pMouseY) {
        isDragging = false;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        if (!visible)
            return;
        isHovered = mouseX >= this.getX() && mouseY >= this.getY() && this.getX() + width > mouseX && this.getY() + height > mouseY;
        renderBefore(graphics, mouseX, mouseY, partialTicks);
        renderWidget(graphics, mouseX, mouseY, partialTicks);
        renderAfter(graphics, mouseX, mouseY, partialTicks);

        wasHovered = isHoveredOrFocused();
    }

    protected void renderBefore(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        graphics.pose().pushPose();
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
    }

    protected void renderAfter(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        graphics.pose().popPose();
    }

    public void runCallback(double mouseX, double mouseY) {
        this.onClick.accept((int) mouseX, (int) mouseY);
    }

    public <T extends AbstractUWidget> T setCallback(BiConsumer<Integer, Integer> consumer) {
        this.onClick = consumer;
        return (T) this;
    }

    public <T extends AbstractUWidget> T setCallback(Runnable action) {
        return setCallback((x, y) -> action.run());
    }

    @Override
    protected boolean clicked(double mouseX, double mouseY) {
        return this.isMouseOver(mouseX, mouseY);
    }

    public List<Component> getToolTip() {
        return toolTip;
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        defaultButtonNarrationText(narrationElementOutput);
    }

    @Override
    public void setHeight(int value) {
        this.height = value;
    }
}
