package jp.ukon.ukon_core.util.helper;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import jp.ukon.ukon_core.UKONCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

public class RenderHelper {

    public static void drawFrameBuffer(RenderTarget frameBuffer, boolean doUseAlphaBlend, boolean doEnableModifyAlpha, float xStart, float xEnd, float yStart, float yEnd) {
        /*RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.disableCull();
        RenderSystem.viewport(0, 0, viewportWidth, viewportHeight);
        RenderSystem.setShader(GameRenderer::getPositionColorShader);

        if (doUseAlphaBlend) {
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE);
        }
        else {
            RenderSystem.disableBlend();
        }

        if (doEnableModifyAlpha)
            RenderSystem.colorMask(true, true, true, true);
        else
            RenderSystem.colorMask(true, true, true, false);

        //FIXME:
        frameBuffer = Minecraft.getInstance().getMainRenderTarget();
        //RenderSystem.setShaderTexture(0, frameBuffer.getColorTextureId());*/

        // FIXME: null代入を直す
        /*ShaderInstance shader = doUseAlphaBlend ? Minecraft.getInstance().gameRenderer.blitShader : null;
        shader.setSampler("DiffuseSampler", frameBuffer.getColorTextureId());
        Matrix4f projectionMatrix = new Matrix4f().setOrtho(0, (float) viewportWidth, (float) viewportHeight, 0, 1000, 3000);
        shader.MODEL_VIEW_MATRIX.set(new Matrix4f().translation(0, 0, -2000));
        shader.PROJECTION_MATRIX.set(projectionMatrix);
        shader.apply();*/

        /*float frameBufferXScale = (float) viewportWidth / (float) frameBuffer.width;
        float frameBufferYScale = (float) viewportHeight / (float) frameBuffer.height;

        BufferBuilder buffer = RenderSystem.renderThreadTesselator().getBuilder();
        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        // 左上
        buffer.vertex(xStart, yStart, 0)
                //.uv(0.0F, 0.0F)
                .color(255, 255, 255, 255)
                .endVertex();
        // 右上
        buffer.vertex(xEnd, yStart, 0)
                //.uv(frameBufferXScale, 0.0F)
                .color(255, 255, 255, 255)
                .endVertex();
        // 右下
        buffer.vertex(xEnd, yEnd, 0)
                //.uv(frameBufferXScale, frameBufferYScale)
                .color(255, 255, 255, 255)
                .endVertex();
        // 左下
        buffer.vertex(xStart, yEnd, 0)
                //.uv(0.0F, frameBufferYScale)
                .color(255, 255, 255, 255)
                .endVertex();
        BufferUploader.draw(buffer.end());

        //shader.clear();

        RenderSystem.colorMask(true, true, true, true);
        RenderSystem.depthMask(true);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();*/

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder buffer = tesselator.getBuilder();

        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);

        // 画面全体に矩形を描画 (ARGB: 半透明赤)
        float r = 1.0f, g = 0.0f, b = 0.0f, a = 0.5f;

        // 左上
        buffer.vertex(xStart, yStart, 0)
                .color(r, g, b, a)
                .endVertex();
        // 左下
        buffer.vertex(xStart, yEnd, 0)
                .color(r, g, b, a)
                .endVertex();
        // 右下
        buffer.vertex(xEnd, yEnd, 0)
                .color(r, g, b, a)
                .endVertex();
        // 右上
        buffer.vertex(xEnd, yStart, 0)
                .color(r, g, b, a)
                .endVertex();

        tesselator.end();

        RenderSystem.disableBlend();
    }
}
