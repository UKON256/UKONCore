package jp.ukon.ukon_core.foundations.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface IScreenElement {
    @OnlyIn(Dist.CLIENT)
    void render(PoseStack stack, int x, int y);
}
