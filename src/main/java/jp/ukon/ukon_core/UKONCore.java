package jp.ukon.ukon_core;

import jp.ukon.ukon_core.foundations.networking.PacketHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(UKONCore.ID)
public class UKONCore {
    public static final String ID = "ukon_core";
    public static final String NAME = "UKON's core";
    public static final String VERSION = "0.1.2";

    public UKONCore() { load(); }

    public void load()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        AllUCItems.register(bus);
        PacketHelper.register();
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(ID, path);
    }
}
