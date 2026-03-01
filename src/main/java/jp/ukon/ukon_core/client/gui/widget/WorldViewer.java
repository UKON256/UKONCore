package jp.ukon.ukon_core.client.gui.widget;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.pipeline.TextureTarget;
import jp.ukon.ukon_core.client.render.CameraEntity;
import jp.ukon.ukon_core.client.render.viewer.OverlayWorldRenderer;
import jp.ukon.ukon_core.client.render.viewer.WorldViewRenderInfo;
import jp.ukon.ukon_core.util.helper.RenderHelper;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
public class WorldViewer extends AbstractUWidget {
    public RenderTarget frameBuffer;
    public Supplier<WorldViewRenderInfo> infoSupplier;

    public WorldViewer(int x, int y, int width, int height, Supplier<WorldViewRenderInfo> infoSupplier) {
        super(x, y, width, height);
        this.infoSupplier = infoSupplier;
        frameBuffer = new TextureTarget(2, 2, true, true);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        super.render(graphics, mouseX, mouseY, partialTicks);

        OverlayWorldRenderer.submitRenderingTask(infoSupplier.get(), frameBuffer);

        RenderHelper.drawFrameBuffer(frameBuffer, this.getX(), this.getX() + this.width, this.getY(), this.getY() + this.height);
    }
}
