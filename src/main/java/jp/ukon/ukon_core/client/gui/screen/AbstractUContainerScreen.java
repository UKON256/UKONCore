package jp.ukon.ukon_core.client.gui.screen;

import jp.ukon.ukon_core.client.gui.ITickableGuiEventListener;
import jp.ukon.ukon_core.client.gui.widget.AbstractUWidget;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractUContainerScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
    public AbstractUContainerScreen(T menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void containerTick() {
        for (GuiEventListener listener : children()) {
            if (listener instanceof ITickableGuiEventListener tickable)
                tickable.tick();
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        partialTicks = minecraft.getFrameTime();

        renderBackground(graphics, mouseX, mouseY, partialTicks);
        super.render(graphics, mouseX, mouseY, partialTicks);
        renderForeground(graphics, mouseX, mouseY, partialTicks);
    }

    protected void renderBackground(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
    }

    protected void renderForeground(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        renderTooltip(graphics, mouseX, mouseY);
        for (Renderable widget : renderables) {
            if (!(widget instanceof AbstractUWidget uWidget && uWidget.isHoveredOrFocused()))
                continue;
            List<Component> tooltip = uWidget.getToolTip();
            if (tooltip.isEmpty())
                continue;
            graphics.renderComponentTooltip(font, tooltip, mouseX, mouseY);
        }
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
