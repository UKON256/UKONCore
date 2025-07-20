package jp.ukon.ukon_core.client.render.viewer;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.PoseStack;
import jp.ukon.ukon_core.api.event.render.GameRenderEndEvent;
import jp.ukon.ukon_core.extender.IECamera;
import jp.ukon.ukon_core.extender.IEFrameBuffer;
import jp.ukon.ukon_core.extender.IEGameRenderer;
import jp.ukon.ukon_core.extender.IEMinecraftClient;
import jp.ukon.ukon_core.extender.IEParticleEngine;
import jp.ukon.ukon_core.util.ClientWorldLoader;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.lwjgl.opengl.GL11.GL_STENCIL_TEST;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class OverlayWorldRenderer {
    private static Queue<RenderingTask> renderingTasks = new ArrayDeque<>();
    private static RenderTarget _renderingFrameBuffer;

    /**
     * 次のフレームの連打―コールに描画用のタスクを追加します
     * @param info
     * @param frameBuffer
     */
    public static void submitRenderingTask(WorldViewRenderInfo info, RenderTarget frameBuffer) {
        RenderTarget mainFrameBuffer = Minecraft.getInstance().getMainRenderTarget();
        if (frameBuffer.width != mainFrameBuffer.width || frameBuffer.height != mainFrameBuffer.height)
            frameBuffer.resize(mainFrameBuffer.width, mainFrameBuffer.height, true);

        renderingTasks.add(new RenderingTask(info, frameBuffer));
    }

    private static void renderWorldIntoFrameBuffer(WorldViewRenderInfo info, RenderTarget frameBuffer) {
        // 描画準備
        _renderingFrameBuffer = frameBuffer;
        RenderTarget oldFrameBuffer = Minecraft.getInstance().getMainRenderTarget();
        ((IECamera)Minecraft.getInstance().gameRenderer.getMainCamera()).resetState(info.cameraPosition, info.renderingLevel);
        ((IEMinecraftClient) Minecraft.getInstance()).setFrameBuffer(frameBuffer);

        // 描画本体
        if (!info.doRenderSky) {
            // pre-clear the framebuffer with 0 alpha, if it doesn't render the sky
            GlStateManager._colorMask(true, true, true, true);
            frameBuffer.setClearColor(0, 0, 0, 0);
            frameBuffer.clear(true);
        }
        frameBuffer.bindWrite(true);
        prepareRendering();
        renderWorld(info);
        //disorganizeRendering();

        // 描画の際に変更した部分の復元
        ((IEMinecraftClient) Minecraft.getInstance()).setFrameBuffer(oldFrameBuffer);
        oldFrameBuffer.bindWrite(true);
        _renderingFrameBuffer = null;
    }

    protected static void prepareRendering() {
        ((IEFrameBuffer) _renderingFrameBuffer).setIsStencilBufferEnabledAndReload(true);
        Minecraft.getInstance().getMainRenderTarget().bindWrite(false);
        GL11.glClearStencil(0);
        GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);

        GlStateManager._enableDepthTest();
        GL11.glEnable(GL_STENCIL_TEST);
    }

    protected static void renderWorld(WorldViewRenderInfo info) {
        // 準備
        Minecraft mc = Minecraft.getInstance();
        IEGameRenderer extendedGameRenderer = (IEGameRenderer) mc.gameRenderer;
        ResourceKey<Level> dimension = info.renderingLevel.dimension();
        LevelRenderer worldRenderer = ClientWorldLoader.getWorldRenderer(dimension);

        ClientLevel oldWorld = mc.level;
        LevelRenderer oldWorldRenderer = mc.levelRenderer;
        boolean oldDoRenderHand = extendedGameRenderer.getDoRenderHand();
        Camera oldCamera = mc.gameRenderer.getMainCamera();

        // レンダリング本体
        mc.level = info.renderingLevel;
        ((IEMinecraftClient) mc).setWorldRenderer(worldRenderer);
        mc.gameRenderer.setRenderHand(info.doRenderHand);
        extendedGameRenderer.setCamera(new Camera());
        ((IEParticleEngine) mc.particleEngine).setWorld(info.renderingLevel);
        mc.getBlockEntityRenderDispatcher().level = info.renderingLevel;

        mc.gameRenderer.renderLevel(mc.getPartialTick(), System.nanoTime(), new PoseStack());

        // 復元
        mc.level = oldWorld;
        ((IEMinecraftClient) mc).setWorldRenderer(oldWorldRenderer);
        mc.gameRenderer.setRenderHand(oldDoRenderHand);
        extendedGameRenderer.setCamera(oldCamera);
        ((IEParticleEngine) mc.particleEngine).setWorld(oldWorld);
        mc.getBlockEntityRenderDispatcher().level = info.renderingLevel;
    }

    /*protected static void disorganizeRendering() {

    }*/

    @SubscribeEvent
    public static void onGameRenderEnd(GameRenderEndEvent event) {
        renderingTasks.forEach(task -> {
            renderWorldIntoFrameBuffer(task.info, task.renderTarget);
        });
        renderingTasks.clear();
    }

    private record RenderingTask(WorldViewRenderInfo info, RenderTarget renderTarget) {}
}
