package jp.ukon.ukon_core.foundations.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import jp.ukon.ukon_core.UKONCore;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum GuiTextures implements IScreenElement {
    BUTTON_UP("widgets", 0, 0, 18, 18),
    BUTTON_HOVER("widgets", 18, 0, 18, 18),
    BUTTON_DOWN("widgets", 36, 0, 18, 18),

    BUTTON_RIGHT_UP("widgets", 0, 18, 8, 13),
    BUTTON_RIGHT_HOVER("widgets", 8, 18, 8, 13),
    BUTTON_RIGHT_LIMIT("widgets", 16, 18, 8, 13),
    BUTTON_LEFT_UP("widgets", 0, 31, 8, 13),
    BUTTON_LEFT_HOVER("widgets", 8, 31, 8, 13),
    BUTTON_LEFT_LIMIT("widgets", 16, 31, 8, 13)
    ;

    public final ResourceLocation resourceLocation;
    public int startX, startY;
    public int width, height;
    private GuiTextures(String location, int startX, int startY, int width, int height) {
        this(UKONCore.ID, location, startX, startY, width, height);
    }
    private GuiTextures(String namespace, String location, int startX, int startY, int width, int height) {
        this.resourceLocation = new ResourceLocation(namespace, "textures/gui/" + location + ".png");
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
    }

    @OnlyIn(Dist.CLIENT)
    public void bind() {
        RenderSystem.setShaderTexture(0, resourceLocation);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void render(PoseStack stack, int x, int y) {
        bind();
        GuiComponent.blit(stack, x, y, 0, startX, startY, width, height, 256, 256);
    }
}
