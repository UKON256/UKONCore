package jp.ukon.ukon_core.foundations.gui.widgets;

import com.mojang.blaze3d.vertex.PoseStack;
import jp.ukon.ukon_core.foundations.gui.AbstractUWidget;
import net.minecraft.network.chat.Component;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.Nullable;

public class FluidArea<T extends FluidTank> extends AbstractUWidget {
    public T tank;

    public FluidArea(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void render(@Nullable PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        if (!visible)
            return;
        if (!this.isHoveredOrFocused())
            return;

        Component component = tank.isEmpty() ? Component.empty() : Component.literal(tank.getFluid().getDisplayName() + ": " + tank.getFluidAmount() + "/" + tank.getCapacity());
        setTooltip(component);
    }
}
