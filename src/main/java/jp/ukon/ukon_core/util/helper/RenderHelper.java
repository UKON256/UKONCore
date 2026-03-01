package jp.ukon.ukon_core.util.helper;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.renderer.GameRenderer;

public class RenderHelper {

    public static void drawFrameBuffer(RenderTarget frameBuffer, float xStart, float xEnd, float yStart, float yEnd) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, frameBuffer.getColorTextureId());

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder buffer = tesselator.getBuilder();

        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);

        // 左上
        buffer.vertex(xStart, yStart, 0)
                .uv(0, 1)
                .endVertex();
        // 左下
        buffer.vertex(xStart, yEnd, 0)
                .uv(0, 0)
                .endVertex();
        // 右下
        buffer.vertex(xEnd, yEnd, 0)
                .uv(1, 0)
                .endVertex();
        // 右上
        buffer.vertex(xEnd, yStart, 0)
                .uv(1, 1)
                .endVertex();
        tesselator.end();

        RenderSystem.disableBlend();
    }
}
