package jp.ukon.ukon_core.init;

import jp.ukon.ukon_core.UKONCore;
import jp.ukon.ukon_core.client.guide.GuideRegistry;
import jp.ukon.ukon_core.client.guide.scenes.NotFoundScene;

public class UCGuides {
    private static final GuideRegistry REGISTRY = new GuideRegistry(UKONCore.ID);

    public static void register() {
        REGISTRY.of("notFound")
                .addChapter(NotFoundScene::notFound);
        REGISTRY.of(UCItems.Guide)
                .addChapter(NotFoundScene::notFound);
    }
}
