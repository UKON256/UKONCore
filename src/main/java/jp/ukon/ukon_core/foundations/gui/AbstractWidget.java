package jp.ukon.ukon_core.foundations.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractWidget extends net.minecraft.client.gui.components.AbstractWidget implements IGuiTickEventListener {
    protected List<Component> toolTip = new LinkedList<>();

    public AbstractWidget(int x, int y, int width, int height) {
        this(x, y, width, height, Component.empty());
    }
    public AbstractWidget(int x, int y, int width, int height, Component message) {
        super(x, y, width, height, message);
    }

    public List<Component> getToolTip() {
        return toolTip;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(@Nullable PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        if (!visible)
            return;
        isHovered = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
        beforeRender(stack, mouseX, mouseY, partialTicks);
        renderButton(stack, mouseX, mouseY, partialTicks);
        afterRender(stack, mouseX, mouseY, partialTicks);
    }

    public void beforeRender(@Nullable PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        stack.pushPose();
    }
    @Override
    public void renderButton(@Nullable PoseStack stack, int mouseX, int mouseY, float partialTicks) {
    }
    public void afterRender(@Nullable PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        stack.popPose();
    }

    public void runOnClick() {
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        runOnClick();
    }

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {
        defaultButtonNarrationText(pNarrationElementOutput);
    }
}
