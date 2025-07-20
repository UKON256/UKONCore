package jp.ukon.ukon_core.client.guide;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import jp.ukon.postengine.math.EasingFloat;
import jp.ukon.ukon_core.UKONCore;
import jp.ukon.ukon_core.client.gui.ScreenOpener;
import jp.ukon.ukon_core.client.guide.gui.GuideScreen;
import jp.ukon.ukon_core.util.helper.RenderHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.lwjgl.opengl.GL11;

public class InventoryToGuide {
    private static final int fontColor = 0xFFFFFFFF;
    private static final float radius = 12.5f;
    private static final double requiredSec = 2.5;
    private static final ItemStack guideStack = new ItemStack(Items.KNOWLEDGE_BOOK);
    private static EasingFloat easing = new EasingFloat(EasingFloat.IEasingBehaviour.POW).startBySecond(0, 100, requiredSec);

    public static void onRenderToolTip(GuiGraphics graphics, ItemStack selected, int mouseX, int mouseY)
    {
        if (!GuideRegistry.hasGuide(selected.getItem()))
            return;

        int renderingCenterX = mouseX - 15;
        int renderingCenterY = mouseY - 5;

        RenderSystem.disableDepthTest();

        if (Screen.hasControlDown())
        {
            easing.tick();

            // 進捗バー
            float angle = easing.getProgress() * 360;

            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            RenderSystem.setShader(GameRenderer::getPositionColorShader);

            BufferBuilder buffer = Tesselator.getInstance().getBuilder();
            buffer.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);

            // 中心点（透明）
            buffer.vertex(renderingCenterX, renderingCenterY, 0).color(255, 255, 255, 0).endVertex();
            // 円周上の点（不透明）
            for (float i = angle; i > 0; i--) {
                double radian = (i - 90) / 180F * Math.PI;
                var xPos = renderingCenterX + Math.cos(radian) * radius;
                var yPos = renderingCenterY + Math.sin(radian) * radius;
                buffer.vertex(xPos, yPos, 0).color(255, 255, 255, 255).endVertex();
            }
            // 再度中心点 (透明)
            buffer.vertex(renderingCenterX, renderingCenterY, 0).color(255, 255, 255, 0).endVertex();

            Tesselator.getInstance().end();

            if (easing.isCompleted())
                openGuide(selected);
        }
        else
            easing.startBySecond(0, 100, requiredSec);

        var mc = Minecraft.getInstance();

        //===== ガイドの案内を描画 =====//
        // アイコン
        graphics.pose().pushPose();
        graphics.pose().translate(0, 0, 400);
        graphics.renderItem(guideStack, renderingCenterX - 8, renderingCenterY - 8);
        graphics.pose().popPose();

        graphics.pose().pushPose();
        graphics.pose().translate(0, 0, 800);
        // ?マーク
        graphics.drawString(mc.font, "?", renderingCenterX - mc.font.width("?"), renderingCenterY - mc.font.lineHeight / 2, fontColor, true);

        // Ctrlマーク
        graphics.pose().scale(0.8f, 0.8f, 1);
        boolean isMacOS = Minecraft.ON_OSX;
        Component key = Component.literal(isMacOS ? "Cmd" : "Ctrl").withStyle(ChatFormatting.BOLD);
        int keyWidth = mc.font.width(key);
        graphics.drawString(mc.font, key.getString(), renderingCenterX * 1.25f - keyWidth / 2, renderingCenterY * 1.25f - mc.font.lineHeight / 2 + 10, fontColor, true);
        graphics.pose().popPose();

        RenderSystem.enableDepthTest();
    }

    private static void openGuide(ItemStack selected) {
        ScreenOpener.open(new GuideScreen(GuideRegistry.copyCompiledData(selected.getItem())));
    }
}
