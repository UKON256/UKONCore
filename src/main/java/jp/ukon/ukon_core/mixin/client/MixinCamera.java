package jp.ukon.ukon_core.mixin.client;

import jp.ukon.ukon_core.extender.IECamera;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Camera.class)
public abstract class MixinCamera implements IECamera {
    @Shadow
    private BlockGetter level;

    @Shadow
    protected abstract void setPosition(Vec3 pPos);

    @Override
    public void resetState(Vec3 pos, ClientLevel level) {
        setPosition(pos);
        this.level = level;
    }
}
