package jp.ukon.ukon_core.client.gui;

import jp.ukon.ukon_core.client.gui.widget.AbstractUWidget;
import net.minecraft.network.chat.Component;

public class WidgetSorter extends AbstractUWidget {
    public XAlignment alignment;

    public WidgetSorter(int x, int y, int width, int height, Component message) {
        super(x, y, width, height, message);
    }

    public enum XAlignment {
        Right,
        Left,
        Center,
    }
}
