package jp.ukon.ukon_core.client.gui.widget;

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SlideBar extends AbstractUWidget {
    protected double xProgress;
    protected double yProgress;
    protected boolean isDragging;

    public SlideBar(int x, int y, int width, int height, Component message) {
        super(x, y, width, height, message);
        this.xProgress = 0;
        this.yProgress = 0;
        this.isDragging = false;
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        super.onClick(mouseX, mouseY);
        updateProgress(mouseX, mouseY);
    }

    @Override
    public void onDrag(double mouseX, double mouseY, double dragX, double dragY) {
        super.onDrag(mouseX, mouseY, dragX, dragY);
        if (this.isDragging)
            updateProgress(mouseX, mouseY);
    }

    protected void updateProgress(double mouseX, double mouseY) {
        this.xProgress = Math.max(0.0, Math.min(1.0, (mouseX - this.getX()) / this.width));
        this.yProgress = Math.max(0.0, Math.min(1.0, (mouseY - this.getY()) / this.height));
    }
}
