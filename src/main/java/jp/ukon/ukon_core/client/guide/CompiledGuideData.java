package jp.ukon.ukon_core.client.guide;

import javax.annotation.concurrent.Immutable;

import jp.ukon.ukon_core.client.guide.action.GuideAction;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * コンパイルされたガイド情報
 */
@Immutable
public final class CompiledGuideData {
    public final List<GuideAction> actions;

    public CompiledGuideData(List<GuideAction> actions) {
        this.actions = new ArrayList<>(actions);
    }

    public CompiledGuideData copy() {
        return new CompiledGuideData(new ArrayList<>(actions));
    }
}
