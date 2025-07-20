package jp.ukon.ukon_core.client.guide.scenes;

import jp.ukon.postengine.util.UUIDUtil;
import jp.ukon.ukon_core.UKONCore;
import jp.ukon.ukon_core.client.guide.SceneBuilder;
import jp.ukon.ukon_core.client.guide.action.system.WaitAction;

import java.util.UUID;

public class NotFoundScene{
    public static final UUID ID = UUIDUtil.generateUUIDFromString(UKONCore.ID + ":notFound");

    public static void notFound(SceneBuilder builder) {
        builder.addAction(WaitAction.waitForTicks(5))
                .addAction(WaitAction.waitForSeconds(1));
    }
}
