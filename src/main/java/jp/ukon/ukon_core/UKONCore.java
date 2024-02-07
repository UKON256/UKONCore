package jp.ukon.ukon_core;

import jp.ukon.ukon_core.foundations.ModBase;
import jp.ukon.ukon_core.foundations.networking.PacketHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(UKONCore.ID)
public class UKONCore extends ModBase {
    public static final String ID = "ukon_core";
    public static final String NAME = "UKON's core";
    public static final String VERSION = "0.0.1";

    public UKONCore() { load(); }

    public void load()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        ModBase.register(this);

        PacketHelper.register();
    }

    public String getModID() {
        return ID;
    }
    public String getModName() {
        return NAME;
    }
    public String getModVersion() {
        return VERSION;
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(ID, path);
    }
}
