package jp.ukon.ukon_core.client.guide.action;

public class GuideAction {
    public GuideAction(int processingTicks) {
        this.processingTicks = processingTicks;
    }

    // この処理が実行される時間
    private int scheduledTick;
    // この処理にかかる時間
    private int processingTicks;

    protected boolean isActive = false;

    public void show() {
    }
    public void hide() {
    }

    public void tick(int currentTicks) {
        if (!isActive && (this.scheduledTick <= currentTicks && currentTicks <= this.scheduledTick + this.processingTicks))
            show();
        if (isActive && (this.scheduledTick > currentTicks || currentTicks > this.scheduledTick + this.processingTicks))
            hide();
    }

    public void setScheduledTick(int ticks) {
        this.scheduledTick = ticks;
    }
    public int getScheduledTick() {
        return this.scheduledTick;
    }

    public void setProcessingTicks(int ticks) {
        this.processingTicks = ticks;
    }
    public int getProcessingTicks() {
        return this.processingTicks;
    }
}
