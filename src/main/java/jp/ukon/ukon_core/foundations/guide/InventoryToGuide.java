package jp.ukon.ukon_core.foundations.guide;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import jp.ukon.ukon_core.AllItems;
import jp.ukon.ukon_core.foundations.item.IHasGuideItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class InventoryToGuide {
    private static int guideCtrlTick = 0;
    private static int requiredTick = 50;
    private static final int radius = 12;
    public static void onRenderToolTip(PoseStack ms, ItemStack select, int mouseX, int mouseY)
    {
        if (!(select.getItem() instanceof IHasGuideItem guide))
            return;

        int x = mouseX - 30;
        int y = mouseY - 20;

        RenderSystem.disableDepthTest();

        if (Screen.hasControlDown())
        {
            guideCtrlTick++;

            // 進捗バー
            float angle = (float) guideCtrlTick / requiredTick * 360;
            int circleX = x + 8;// + radius;
            int circleY = y + 8;// + radius;

            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            BufferBuilder buf = Tesselator.getInstance().getBuilder();
            buf.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);

            buf.vertex(circleX, circleY, 0).color(1, 1, 0, 1).endVertex();
            //中心点から円周上に頂点を追加
            for (float i = angle; i > 0; i--) {
                double radian = (i - 90) / 180F * Math.PI;
                var xPos = circleX + Math.cos(radian) * radius;
                var yPos = circleY + Math.sin(radian) * radius;
                buf.vertex(xPos, yPos, 0).color(1, 1, 0, 1).endVertex();
            }
            buf.vertex(circleX, circleY, 0).color(1, 1, 0, 0).endVertex();

            Tesselator.getInstance().end();
            RenderSystem.disableBlend();
        }
        else
        {
            guideCtrlTick = 0;
        }

        if (guideCtrlTick >= requiredTick)
        {
            guide.getGuide().open();
            guideCtrlTick = 0;
        }

        var mc = Minecraft.getInstance();

        // ガイドの案内を描画
        // アイコン
        ms.pushPose();
        ms.translate(0, 0, 300);
        mc.getItemRenderer().renderAndDecorateItem(new ItemStack(AllItems.Guide.get()), x, y, 400);
        ms.popPose();

        // ?マーク
        ms.pushPose();
        ms.translate(0, 0, 500);
        mc.font.drawShadow(ms, "?", x + 10, y + 3, 0xFFFFFFFF);

        // Ctrlマーク
        ms.scale(0.8f, 0.8f, 1);
        boolean isMacOS = Minecraft.ON_OSX;
        Component key = Component.literal(isMacOS ? "Cmd" : "Ctrl").withStyle(ChatFormatting.BOLD);
        mc.font.drawShadow(ms, key, (x + 10) * 1.25f - 14, (y + 4) * 1.25f + 15, 0xFFFFFFFF);
        ms.popPose();

        RenderSystem.enableDepthTest();
    }
}
