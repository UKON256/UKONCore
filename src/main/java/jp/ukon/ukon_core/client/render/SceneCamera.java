package jp.ukon.ukon_core.client.render;

import net.minecraft.client.Camera;

public class SceneCamera extends Camera {
    public void setRot(float xRotation, float yRotation) {
        setRotation(yRotation, xRotation);
    }
}
