package jp.ukon.ukon_core.client.guide;

import java.util.ArrayList;
import java.util.List;

import jp.ukon.ukon_core.client.guide.action.GuideAction;

public class SceneBuilder {
    private List<GuideAction> actions = new ArrayList<>();
    // addAction時に時間指定するための仮変数
    private int runtimeTicks;

    public SceneBuilder(List<ISceneEntry> entries) {
        runtimeTicks = 0;
        for (ISceneEntry entry : entries)
            entry.compile(this);
    }

    public SceneBuilder addAction(GuideAction action) {
        action.setScheduledTick(runtimeTicks);
        runtimeTicks += action.getProcessingTicks();
        actions.add(action);
        return this;
    }

    public CompiledGuideData compile() {
        return new CompiledGuideData(actions);
    }

    @FunctionalInterface
    public interface ISceneEntry {
        void compile(SceneBuilder builder);
    }
}
