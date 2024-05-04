package jp.ukon.ukon_core.foundations.gui;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public interface IGuiTickEventListener {
    void tick();
}
