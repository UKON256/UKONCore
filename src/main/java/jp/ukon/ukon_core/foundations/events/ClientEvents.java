package jp.ukon.ukon_core.foundations.events;

import com.mojang.blaze3d.vertex.PoseStack;
import jp.ukon.ukon_core.foundations.guide.InventoryToGuide;
import jp.ukon.ukon_core.utils.helpers.InventoryHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.client.event.ScreenEvent.KeyPressed;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onInventoryKeyInput(KeyPressed.Post event)
    {
        int keyCode = event.getKeyCode();
    }

    @SubscribeEvent
    public static void onRenderTooltip(RenderTooltipEvent.Pre event)
    {
        PoseStack ms = event.getPoseStack();
        ItemStack stack = event.getItemStack();
        int mouseX = event.getX();
        int mouseY = event.getY();

        InventoryToGuide.onRenderToolTip(ms, stack, mouseX, mouseY);
    }
}
