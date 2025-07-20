package jp.ukon.ukon_core.client.guide.action.system;

import jp.ukon.ukon_core.client.guide.action.GuideAction;

public class WaitAction extends GuideAction {
    protected WaitAction(int waitingTicks) {
        super(waitingTicks);
    }

    public static WaitAction waitForTicks(int waitingTicks) {
        return new WaitAction(waitingTicks);
    }

    private static final int TICKS_PER_SECOND = 20;
    public static WaitAction waitForSeconds(int waitingSeconds) {
        return new WaitAction(waitingSeconds * TICKS_PER_SECOND);
    }
}
