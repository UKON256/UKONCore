package jp.ukon.ukon_core.mixin.client;

import com.mojang.blaze3d.pipeline.RenderTarget;
import jp.ukon.ukon_core.extender.IEFrameBuffer;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderTarget.class)
public abstract class MixinRenderTarget implements IEFrameBuffer {
    private boolean isStencilBufferEnabled;
    @Shadow
    public int width;
    @Shadow
    public int height;

    @Shadow
    public abstract void resize(int width, int height, boolean clearError);

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(boolean useDepth, CallbackInfo ci) {
        isStencilBufferEnabled = false;
    }

    @Override
    public void setIsStencilBufferEnabledAndReload(boolean cond) {
        if (isStencilBufferEnabled != cond) {
            isStencilBufferEnabled = cond;
            resize(width, height, Minecraft.ON_OSX);
        }
    }
}
