package jp.ukon.ukon_core.mixin.client;

import jp.ukon.ukon_core.extender.IECamera;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Camera.class)
public abstract class MixinCamera implements IECamera {
    @Shadow
    private BlockGetter level;

    @Shadow
    protected abstract void setPosition(Vec3 pPos);

    @Shadow
    protected abstract void setRotation(float pYRot, float pXRot);

    @Override
    public void resetState(Vec3 pos, ClientLevel level) {
        setPosition(pos);
        this.level = level;
    }

    @Override
    public void setCameraPosition(Vec3 pos) {
        setPosition(pos);
    }

    @Override
    public void setCameraRotation(float xRot, float yRot) {
        setRotation(yRot, xRot);
    }

    @Override
    public void setCameraLevel(BlockGetter blockGetter) {
        this.level = blockGetter;
    }
}
