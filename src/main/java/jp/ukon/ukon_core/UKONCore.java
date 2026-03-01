package jp.ukon.ukon_core;

import jp.ukon.ukon_core.init.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(UKONCore.ID)
public class UKONCore {
    public static final String ID = "ukon_core";
    public static final String NAME = "UKON's core";
    public static final String VERSION = "0.1.5";

    public static final Logger LOGGER = LoggerFactory.getLogger(StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass());

    public UKONCore() {
        load();
    }

    private void load() {
        ModLoadingContext modLoadingContext = ModLoadingContext.get();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
        MinecraftForge.EVENT_BUS.register(UKONCore.class);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> UKONCoreClient.initClient(modEventBus, forgeEventBus));
    }

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        //UCCommands.register(event.getDispatcher());
    }
}
