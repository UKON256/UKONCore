package jp.ukon.ukon_core.extender;

import net.minecraft.client.Camera;

public interface IEGameRenderer {
    boolean getDoRenderHand();

    void setCamera(Camera camera);
}
