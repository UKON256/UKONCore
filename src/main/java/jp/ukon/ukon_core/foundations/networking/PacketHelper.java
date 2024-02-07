package jp.ukon.ukon_core.foundations.networking;

import jp.ukon.ukon_core.UKONCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHelper {
    public static final ResourceLocation CHANNEL_NAME = UKONCore.asResource("main");
    public static final int NETWORK_VERSION = 3;
    public static final String NETWORK_VERSION_STR = String.valueOf(NETWORK_VERSION);
    private static SimpleChannel channel;

    public static void register() {
        channel = NetworkRegistry.ChannelBuilder.named(CHANNEL_NAME)
                .serverAcceptedVersions(NETWORK_VERSION_STR::equals)
                .clientAcceptedVersions(NETWORK_VERSION_STR::equals)
                .networkProtocolVersion(() -> NETWORK_VERSION_STR)
                .simpleChannel();
    }

    public static SimpleChannel getChannel() {
        return channel;
    }
}
