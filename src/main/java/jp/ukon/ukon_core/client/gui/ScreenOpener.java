package jp.ukon.ukon_core.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;

import javax.annotation.Nullable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ScreenOpener {
    private static final Deque<Screen> screenHistories = new ArrayDeque<>();
    private static Screen backSteppedScreen = null;

    public static List<Screen> getScreenHistories() {
        return new ArrayList<>(screenHistories);
    }
    public static void clearScreenHistories() {
        screenHistories.clear();
    }

    public static void open(Screen toOpen) {
        open(Minecraft.getInstance().screen, toOpen);
    }
    public static void open(@Nullable Screen current, Screen toOpen) {
        backSteppedScreen = null;
        if (current != null) {
            screenHistories.push(current);
        } else
            screenHistories.clear();
        openScreen(toOpen);
    }

    public static void openPreScreen(Screen current) {
        backSteppedScreen = current;
        openScreen(screenHistories.pop());
    }

    private static void openScreen(Screen screen) {
        Minecraft.getInstance().tell(() -> {
            Minecraft.getInstance().setScreen(screen);
        });
    }
}
