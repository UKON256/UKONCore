package jp.ukon.postengine.math;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;

import java.util.function.Consumer;

public class EasingFloat {
    protected IEasingBehaviour behaviour;
    protected float startValue;
    protected float currentValue;
    protected float targetValue;
    protected float speed;
    protected float progress;

    public EasingFloat(IEasingBehaviour behaviour) {
        startWith(0, 0, 0);
        setBehaviour(behaviour);
    }

    /**
     * 値が変化しきるまで処理を実行します。
     * ※startWithなどで別途初期化が必要です
     * @param consumer 実行する処理
     */
    public void transition(Consumer<Float> consumer) {
        while (!isCompleted()) {
            consumer.accept(getCurrent());
            tick();
        }
    }

    // 毎tick実行される必要がある
    public void tick() {
        if (behaviour == null)
            return;
        if (isCompleted())
            return;
        currentValue = behaviour.get(this);
        progress = Mth.clamp(progress + speed, 0, 1);
    }
    public EasingFloat startBySecond(float value, float targetValue, double second) {
        float speed = (float) (1.0 / second / 20);
        return startWith(value, targetValue, speed);
    }
    public EasingFloat startWith(float value, float targetValue, float speed) {
        startValue = currentValue = value;
        this.targetValue = targetValue;
        this.speed = speed;
        progress = 0;
        return this;
    }
    public void setBehaviour(IEasingBehaviour behaviour) {
        this.behaviour = behaviour;
    }

    public boolean isCompleted() {
        return progress >= 1;
    }

    public CompoundTag writeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putFloat("Start", startValue);
        nbt.putFloat("Target", targetValue);
        nbt.putFloat("Speed", speed);
        nbt.putFloat("Progress", progress);
        return nbt;
    }
    public void readNBT(CompoundTag nbt) {
        startValue = nbt.getFloat("Start");
        targetValue = nbt.getFloat("Target");
        speed = nbt.getFloat("Speed");
        progress = nbt.getFloat("Progress");
    }

    public float getCurrent() {
        return currentValue;
    }
    public float getStartValue() {
        return startValue;
    }
    public float getTargetValue() {
        return targetValue;
    }
    public float getSpeed() {
        return speed;
    }
    public float getProgress() {
        return progress;
    }

    @FunctionalInterface
    public interface IEasingBehaviour {
        IEasingBehaviour IDLE = EasingFloat::getCurrent;
        IEasingBehaviour LINEAR = (e) -> Mth.clampedLerp(e.getStartValue(), e.getTargetValue(), e.getProgress());
        IEasingBehaviour POW = (e) -> (float)(1 - Math.pow(1 - e.getProgress(), 3));

        float get(EasingFloat easingFloat);
    }
}