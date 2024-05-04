package jp.ukon.ukon_core;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import jp.ukon.ukon_core.foundations.gui.IScreenElement;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum AllUCGuiTextures implements IScreenElement {
    PLAYER_INVENTORY("player_inventory", 176, 97),
    PLAYER_INVENTORY_PARTIAL("player_inventory_partial", 176, 96),

    // Widgets
    BUTTON_UP("widgets", 18, 18),
    BUTTON_HOVER("widgets", 18, 0, 18, 18),
    BUTTON_DOWN("widgets", 36, 0, 18, 18),

    BUTTON_RIGHT_UP("widgets", 0, 18, 8, 13),
    BUTTON_RIGHT_HOVER("widgets", 8, 18, 8, 13),
    BUTTON_RIGHT_LIMIT("widgets", 16, 18, 8, 13),
    BUTTON_LEFT_UP("widgets", 0, 31, 8, 13),
    BUTTON_LEFT_HOVER("widgets", 8, 31, 8, 13),
    BUTTON_LEFT_LIMIT("widgets", 16, 31, 8, 13)
    ;

    public final ResourceLocation location;
    public int width, height;
    public int startX, startY;

    private AllUCGuiTextures(String location, int width, int height) {
        this(location, 0, 0, width, height);
    }
    private AllUCGuiTextures(int startX, int startY) {
        this("icons", startX * 16, startY * 16, 16, 16);
    }
    private AllUCGuiTextures(String location, int startX, int startY, int width, int height) {
        this(UKONCore.ID, location, startX, startY, width, height);
    }
    private AllUCGuiTextures(String namespace, String location, int startX, int startY, int width, int height) {
        this.location = new ResourceLocation(namespace, "textures/gui/" + location + ".png");
        this.width = width;
        this.height = height;
        this.startX = startX;
        this.startY = startY;
    }

    @OnlyIn(Dist.CLIENT)
    public void bind() {
        RenderSystem.setShaderTexture(0, location);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void render(PoseStack poseStack, int x, int y) {
        bind();
        GuiComponent.blit(poseStack, x, y, 0, startX, startY, width, height, 256, 256);
    }

    @OnlyIn(Dist.CLIENT)
    public void render(PoseStack poseStack, int x, int y, GuiComponent component) {
        bind();
        component.blit(poseStack, x, y, startX, startY, width, height);
    }
}
