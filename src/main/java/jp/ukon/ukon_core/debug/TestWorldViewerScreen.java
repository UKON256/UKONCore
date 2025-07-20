package jp.ukon.ukon_core.debug;

import jp.ukon.ukon_core.client.gui.screen.AbstractUScreen;
import jp.ukon.ukon_core.client.gui.widget.WorldViewer;
import jp.ukon.ukon_core.client.render.viewer.WorldViewRenderInfo;
import jp.ukon.ukon_core.util.ClientWorldLoader;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Matrix4f;

import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
public class TestWorldViewerScreen extends AbstractUScreen {
    public Supplier<WorldViewRenderInfo> infoSupplier;
    public ResourceKey<Level> destination;
    public Vec3 position;

    public WorldViewer viewerWidget;

    public TestWorldViewerScreen(ServerLevel world, Vec3 pos) {
        destination = world.dimension();
        position = pos;
        infoSupplier = () -> new WorldViewRenderInfo.Builder()
                .setWorld(ClientWorldLoader.getClientWorld(destination))
                .setCameraPosition(pos)
                .setCameraTransformation(new Matrix4f())
                .build();
    }

    @Override
    protected void init() {
        addRenderableWidget(viewerWidget = new WorldViewer(0, 0, 100, 100, infoSupplier));
    }

    @Override
    protected void renderWindow(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
    }
}
