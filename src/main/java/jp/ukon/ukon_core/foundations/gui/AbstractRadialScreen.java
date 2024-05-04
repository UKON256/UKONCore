package jp.ukon.ukon_core.foundations.gui;

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractRadialScreen extends AbstractUScreen {
    protected AbstractRadialScreen(Component title) {
        super(title);
    }
}
