package jp.ukon.ukon_core.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class ChatHandler {
    public static void info(String message) {
        message = "[INFO] " + message;
        sendInGame(message);
    }
    public static void warn(String message) {
        message = "[WARN] " + message;
        sendInGame(message);
    }
    public static void error(String message) {
        message = "[ERROR] " + message;
        sendInGame(message);
    }

    private static void sendInGame(String message) {
        if (!Minecraft.getInstance().isRunning() || Minecraft.getInstance().screen == null) return;

        Player player = Minecraft.getInstance().player;
        if (player == null) return;
        player.sendSystemMessage(Component.literal(message));
    }
}
