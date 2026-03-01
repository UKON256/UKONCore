package jp.ukon.ukon_core.client.render.viewer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.Validate;
import org.joml.Matrix4f;

public class WorldViewRenderInfo {
    public ClientLevel renderingLevel;
    public Vec3 cameraPosition;
    public Matrix4f cameraTransformation;
    public int renderDistance;
    public boolean doRenderSky;
    public boolean doRenderFog;
    public boolean doRenderHand;

    // Builder用
    private WorldViewRenderInfo(ClientLevel renderingLevel, Vec3 cameraPosition, Matrix4f cameraTransformation, int renderDistance, boolean doRenderSky, boolean doRenderFog, boolean doRenderHand) {
        this.renderingLevel = renderingLevel;
        this.cameraPosition = cameraPosition;
        this.cameraTransformation = cameraTransformation;
        this.renderDistance = renderDistance;
        this.doRenderSky = doRenderSky;
        this.doRenderFog = doRenderFog;
        this.doRenderHand = doRenderHand;
    }

    public static class Builder {
        private ClientLevel renderingLevel;
        private Vec3 cameraPosition;
        private Matrix4f cameraTransformation;
        private int renderDistance = Minecraft.getInstance().options.getEffectiveRenderDistance();
        private boolean doRenderSky = true;
        private boolean doRenderFog = true;
        private boolean doRenderHand = false;

        public Builder() {
        }

        public Builder setWorld(ClientLevel level) {
            this.renderingLevel = level;
            return this;
        }

        public Builder setCameraPosition(Vec3 pos) {
            this.cameraPosition = pos;
            return this;
        }

        public Builder setCameraTransformation(Matrix4f transformation) {
            this.cameraTransformation = transformation;
            return this;
        }

        public Builder setRenderDistance(int renderDistance) {
            this.renderDistance = renderDistance;
            return this;
        }

        public Builder setDoRenderSky(boolean doRender) {
            this.doRenderSky = doRender;
            return this;
        }

        public Builder setDoRenderFog(boolean doRender) {
            this.doRenderFog = doRender;
            return this;
        }

        public Builder setDoRenderHand(boolean doRender) {
            this.doRenderHand = doRender;
            return this;
        }

        public WorldViewRenderInfo build() {
            return new WorldViewRenderInfo(renderingLevel, cameraPosition, cameraTransformation, renderDistance, doRenderSky, doRenderFog, doRenderHand);
        }
    }
}
