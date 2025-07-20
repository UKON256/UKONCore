package jp.ukon.ukon_core.client.guide.gui;

import com.mojang.blaze3d.pipeline.RenderTarget;
import jp.ukon.ukon_core.client.gui.screen.AbstractUScreen;
import jp.ukon.ukon_core.client.guide.CompiledGuideData;
import jp.ukon.ukon_core.client.guide.GuideRegistry;
import jp.ukon.ukon_core.client.guide.scenes.NotFoundScene;
import jp.ukon.ukon_core.client.render.viewer.OverlayWorldRenderer;
import jp.ukon.ukon_core.client.render.viewer.WorldViewRenderInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Matrix4f;

import java.util.Comparator;

public class GuideScreen extends AbstractUScreen {
    public int ticks;
    public CompiledGuideData data;
    @OnlyIn(Dist.CLIENT)
    public RenderTarget frameBuffer;

    public GuideScreen(CompiledGuideData data)  {
        if (data == null)
            data = GuideRegistry.copyCompiledData(NotFoundScene.ID);

        ticks = 0;
        this.data = data;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderWindow(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        // executeTickがticks未満の場合下に、それ以外の場合はexecuteTickの小さい順にソート
        data.actions.sort(Comparator.comparingInt(action -> {
            int executeTick = action.getScheduledTick();
            return executeTick < ticks ? Integer.MAX_VALUE : executeTick;
        }));

        data.actions.forEach(action -> action.tick(ticks));
        ticks++;

        Matrix4f cameraTransformation = new Matrix4f();
        cameraTransformation.identity();

        Vec3 cameraPosition = Minecraft.getInstance().cameraEntity.position().add(5, 20, 5);

        WorldViewRenderInfo viewInfo = new WorldViewRenderInfo.Builder()
                .setWorld(Minecraft.getInstance().level)
                .setCameraPosition(cameraPosition)
                .setCameraTransformation(cameraTransformation)
                .build();

        OverlayWorldRenderer.submitRenderingTask(viewInfo, frameBuffer);
    }

    @Override
    public boolean isPauseScreen() {
        return true;
    }
}
