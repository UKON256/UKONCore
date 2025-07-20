package jp.ukon.ukon_core.mixin.client;

import com.mojang.blaze3d.pipeline.RenderTarget;
import jp.ukon.ukon_core.extender.IEMinecraftClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft implements IEMinecraftClient {
    @Final
    @Shadow
    @Mutable
    private RenderTarget mainRenderTarget;

    @Mutable
    @Shadow
    @Final
    public LevelRenderer levelRenderer;

    @Override
    public void setFrameBuffer(RenderTarget frameBuffer) {
        mainRenderTarget = frameBuffer;
    }

    @Override
    public void setWorldRenderer(LevelRenderer levelRenderer) {
        this.levelRenderer = levelRenderer;
    }
}
