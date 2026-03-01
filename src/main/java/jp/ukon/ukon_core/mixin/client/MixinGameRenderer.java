package jp.ukon.ukon_core.mixin.client;

import jp.ukon.ukon_core.UKONCore;
import jp.ukon.ukon_core.api.event.render.GameRenderEndEvent;
import jp.ukon.ukon_core.extender.IEGameRenderer;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class MixinGameRenderer implements IEGameRenderer {
    @Shadow
    private boolean renderHand;

    @Shadow
    @Final
    @Mutable
    private Camera mainCamera;

    @Override
    public boolean getDoRenderHand() {
        return renderHand;
    }

    @Override
    public void setCamera(Camera camera) {
        this.mainCamera = camera;
    }

    @Inject(
            method = "Lnet/minecraft/client/renderer/GameRenderer;render(FJZ)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/GameRenderer;renderLevel(FJLcom/mojang/blaze3d/vertex/PoseStack;)V",
                    shift = At.Shift.AFTER
            )
    )
    private void onAfterRenderingCenter(float float_1, long long_1, boolean boolean_1, CallbackInfo ci) {
        var event = new GameRenderEndEvent();
        MinecraftForge.EVENT_BUS.post(event);
    }
}
