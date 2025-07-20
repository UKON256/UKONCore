package jp.ukon.ukon_core.event;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber
public class LoadConfigEvent {
    @SubscribeEvent
    public static void onLoadConfig(ModConfigEvent.Loading event) {
        String fileName = event.getConfig().getFileName();
    }

    @SubscribeEvent
    public static void onReloadConfig(ModConfigEvent.Reloading event) {
        String fileName = event.getConfig().getFileName();
    }
}
