package jp.ukon.ukon_core.extender;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.Vec3;

public interface IECamera {
    void resetState(Vec3 pos, ClientLevel level);
    void setCameraPosition(Vec3 pos);
    void setCameraRotation(float xRot, float yRot);
    void setCameraLevel(BlockGetter blockGetter);
}
