package jp.ukon.ukon_core.client.render.viewer;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import jp.ukon.ukon_core.api.event.render.GameRenderEndEvent;
import jp.ukon.ukon_core.extender.IECamera;
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

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class OverlayWorldRenderer {
    private static Queue<RenderingTask> renderingTasks = new ArrayDeque<>();

    /**
     * 次のフレームのレンダーコールに描画用のタスクを追加します
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
        Minecraft mc = Minecraft.getInstance();

        // 描画先を仮想RenderTargetへ切り替え
        RenderTarget oldFrameBuffer = mc.getMainRenderTarget();
        ((IEMinecraftClient) mc).setFrameBuffer(frameBuffer);
        frameBuffer.bindWrite(true);

        if (!info.doRenderSky) {
            GlStateManager._colorMask(true, true, true, true);
            frameBuffer.setClearColor(0, 0, 0, 0);
            frameBuffer.clear(true);
        }

        // レンダリング
        RenderSystem.clear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT, Minecraft.ON_OSX);
        prepareFrame();
        renderWorld(info);

        // 元のターゲットに戻す
        ((IEMinecraftClient) mc).setFrameBuffer(oldFrameBuffer);
        mc.getMainRenderTarget().bindWrite(true);
    }

    protected static void prepareFrame() {
        Minecraft mc = Minecraft.getInstance();
        mc.getMainRenderTarget().bindWrite(false);
    }

    protected static void renderWorld(WorldViewRenderInfo info) {
        // 準備
        Minecraft mc = Minecraft.getInstance();
        ResourceKey<Level> dimension = info.renderingLevel.dimension();
        LevelRenderer worldRenderer = ClientWorldLoader.getWorldRenderer(dimension);

        ClientLevel oldWorld = mc.level;
        LevelRenderer oldWorldRenderer = mc.levelRenderer;
        Camera oldCamera = mc.gameRenderer.getMainCamera();
        boolean oldDoRenderHand = ((IEGameRenderer) mc.gameRenderer).getDoRenderHand();
        boolean oldNoPhysics = mc.player.noPhysics;

        // レンダリング本体
        Camera newCamera = new Camera();
        ((IECamera) newCamera).setCameraLevel(info.renderingLevel);
        //((IECamera) newCamera).setCameraPosition(info.cameraPosition);
        //((IECamera) viewCamera).setCameraRotation(0, 0);

        mc.level = info.renderingLevel;
        ((IEMinecraftClient) mc).setWorldRenderer(worldRenderer);
        ((IEGameRenderer) mc.gameRenderer).setCamera(newCamera);
        mc.player.noPhysics = true;
        mc.gameRenderer.setRenderHand(info.doRenderHand);
        //((IEParticleEngine) mc.particleEngine).setWorld(info.renderingLevel);
        //mc.getBlockEntityRenderDispatcher().level = info.renderingLevel;

        mc.gameRenderer.renderLevel(mc.getPartialTick(), System.nanoTime(), new PoseStack());
        //mc.getEntityRenderDispatcher().prepare(mc.level, viewCamera, mc.crosshairPickEntity);

        // 復元
        ((IEMinecraftClient) mc).setWorldRenderer(oldWorldRenderer);
        ((IEGameRenderer) mc.gameRenderer).setCamera(oldCamera);
        mc.player.noPhysics = oldNoPhysics;
        mc.level = oldWorld;
        mc.gameRenderer.setRenderHand(oldDoRenderHand);
        //((IEParticleEngine) mc.particleEngine).setWorld(oldWorld);
        //mc.getBlockEntityRenderDispatcher().level = oldWorld;
    }

    @SubscribeEvent
    public static void onGameRenderEnd(GameRenderEndEvent event) {
        renderingTasks.forEach(task -> {
            renderWorldIntoFrameBuffer(task.info, task.renderTarget);
        });
        renderingTasks.clear();
    }

    private record RenderingTask(WorldViewRenderInfo info, RenderTarget renderTarget) {}
}
