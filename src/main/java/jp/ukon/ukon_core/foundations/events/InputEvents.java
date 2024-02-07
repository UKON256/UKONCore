package jp.ukon.ukon_core.foundations.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class InputEvents {
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {

    }
}