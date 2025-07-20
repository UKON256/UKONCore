package jp.ukon.ukon_core.extender;

import com.mojang.blaze3d.pipeline.RenderTarget;
import net.minecraft.client.renderer.LevelRenderer;

/**
 * MixinでMinecraftに実装する用の拡張インターフェース
 */
public interface IEMinecraftClient {
    void setFrameBuffer(RenderTarget frameBuffer);

    void setWorldRenderer(LevelRenderer levelRenderer);
}
