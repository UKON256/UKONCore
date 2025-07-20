package jp.ukon.ukon_core.event;

import com.mojang.blaze3d.vertex.PoseStack;
import jp.ukon.postengine.util.tween.TweenManager;
import jp.ukon.ukon_core.client.guide.InventoryToGuide;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ClientEvent {
    @SubscribeEvent
    public static void onRenderTooltip(RenderTooltipEvent.Pre event)
    {
        GuiGraphics graphics = event.getGraphics();
        ItemStack stack = event.getItemStack();
        int mouseX = event.getX();
        int mouseY = event.getY();

        InventoryToGuide.onRenderToolTip(graphics, stack, mouseX, mouseY);
    }

    @SubscribeEvent
    public static void onEndClientTick(TickEvent.ClientTickEvent event) {
        TweenManager.onEndClientTick();
    }
}
