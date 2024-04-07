package jp.ukon.ukon_core.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT)
public class ChatLogger {
    public static void info(String message) {
        message = "[INFO] " + message;
        MutableComponent component = ComponentUtils.wrapInSquareBrackets(Component.literal(message))
                .withStyle(ChatFormatting.GREEN);
        sendInGame(component);
    }
    public static void warn(String message) {
        message = "[WARN] " + message;
        MutableComponent component = ComponentUtils.wrapInSquareBrackets(Component.literal(message))
                .withStyle(ChatFormatting.GOLD);
        sendInGame(component);
    }
    public static void error(String message) {
        message = "[ERROR] " + message;
        MutableComponent component = ComponentUtils.wrapInSquareBrackets(Component.literal(message))
                .withStyle(ChatFormatting.RED);
        sendInGame(component);
    }

    private static void sendInGame(Component component) {
        if (!Minecraft.getInstance().isRunning() || Minecraft.getInstance().screen == null) return;

        Player player = Minecraft.getInstance().player;
        if (player == null) return;
        player.displayClientMessage(component, false);
    }
}
