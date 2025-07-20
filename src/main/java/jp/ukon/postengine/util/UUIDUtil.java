package jp.ukon.postengine.util;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class UUIDUtil {
    public static UUID generateUUIDFromString(String input) {
        return UUID.nameUUIDFromBytes(input.getBytes(StandardCharsets.UTF_8));
    }
}
