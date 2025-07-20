package jp.ukon.ukon_core.util.maintain;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

/**
 * アップデートでいちいち変えるのがめんどいのでここでまとめる
 */
public class Components {
    public static MutableComponent empty() {
        return Component.empty();
    }

    public static MutableComponent literal(String string) {
        return Component.literal(string);
    }

    public static MutableComponent translatable(String key) {
        return Component.translatable(key);
    }

    public static MutableComponent translatable(String key, Object... args) {
        return Component.translatable(key, args);
    }

    public static MutableComponent keybind(String keyName) {
        return Component.keybind(keyName);
    }
}
