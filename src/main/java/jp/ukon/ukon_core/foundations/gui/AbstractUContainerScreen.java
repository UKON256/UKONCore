package jp.ukon.ukon_core.foundations.gui;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import jp.ukon.ukon_core.AllUCGuiTextures;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@OnlyIn(Dist.CLIENT)
@ParametersAreNonnullByDefault
public abstract class AbstractUContainerScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
    protected int windowXOffset, windowYOffset;
    public AbstractUContainerScreen(T container, Inventory inventory, Component title) {
        super(container, inventory, title);
    }

    protected void setWindowSize(int width, int height) {
        imageWidth = width;
        imageHeight = height;
    }

    protected void setWindowOffset(int xOffset, int yOffset) {
        windowXOffset = xOffset;
        windowYOffset = yOffset;
    }

    @Override
    protected void init() {
        super.init();
        leftPos += windowXOffset;
        topPos += windowYOffset;
    }

    @Override
    protected void containerTick() {
        for (GuiEventListener listener : children()) {
            if (listener instanceof IGuiTickEventListener tick)
                tick.tick();
        }
    }

    protected <W extends GuiEventListener & Widget & NarratableEntry> void addRenderableWidgets(W... widgets) {
        for (W widget : widgets) {
            addRenderableWidget(widget);
        }
    }

    protected void removeWidgets(GuiEventListener... widgets) {
        for (GuiEventListener widget : widgets) {
            removeWidget(widget);
        }
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        partialTicks = minecraft.getFrameTime();

        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTicks);
        renderForeground(poseStack, mouseX, mouseY, partialTicks);
    }

    @Override
    protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) { }

    protected void renderForeground(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        renderTooltip(poseStack, mouseX, mouseY);
        for (Widget widget : renderables) {
            if (!(widget instanceof AbstractUWidget uWidget && uWidget.isHoveredOrFocused()))
                continue;

            List<Component> tooltip = uWidget.getTooltip();
            if (tooltip.isEmpty())
                continue;

            int tooltipX = uWidget.lockedTooltipX == -1 ? mouseX : uWidget.lockedTooltipX + uWidget.x;
            int tooltipY = uWidget.lockedTooltipY == -1 ? mouseY : uWidget.lockedTooltipY + uWidget.y;
            renderComponentTooltip(poseStack, tooltip, tooltipX, tooltipY);
        }
    }

    public void renderPlayerInventory(PoseStack poseStack, int x, int y, boolean isPartial) {
        AllUCGuiTextures texture = isPartial ? AllUCGuiTextures.PLAYER_INVENTORY_PARTIAL : AllUCGuiTextures.PLAYER_INVENTORY;
        texture.render(poseStack, x, y, this);
        font.draw(poseStack, playerInventoryTitle, x + 8, y + 6, 0x404040);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        InputConstants.Key mouseKey = InputConstants.getKey(keyCode, scanCode);
        if (getFocused() != null && this.minecraft.options.keyInventory.isActiveAndMatches(mouseKey))
            return false;
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public GuiEventListener getFocused() {
        GuiEventListener focused = super.getFocused();
        if (focused instanceof AbstractWidget && !((AbstractWidget) focused).isFocused())
            focused = null;
        setFocused(focused);
        return focused;
    }
}
