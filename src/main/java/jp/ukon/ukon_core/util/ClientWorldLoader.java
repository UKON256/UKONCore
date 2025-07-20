package jp.ukon.ukon_core.util;

import jp.ukon.ukon_core.extender.IEBiomeManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class ClientWorldLoader {
    public static final Minecraft MC = Minecraft.getInstance();

    private static boolean isInitialized = false;
    private static boolean isGeneratingLevel = false;

    private static final Map<ResourceKey<Level>, ClientLevel> clientWorldCache = new HashMap<>();
    public static final Map<ResourceKey<Level>, LevelRenderer> worldRendererCache = new HashMap<>();

    public static void init() {
        ClientWorldLoader.cleanUp();
        MinecraftForge.EVENT_BUS.register(ClientWorldLoader.class);
    }

    private static void cleanUp() {
        //clientWorldCache.values().forEach(world -> world = null);
        worldRendererCache.values().forEach(ClientWorldLoader::disposeWorldRenderer);

        clientWorldCache.clear();
        worldRendererCache.clear();

        isInitialized = false;
        isGeneratingLevel = false;
    }

    private static void disposeWorldRenderer(LevelRenderer worldRenderer) {
        worldRenderer.setLevel(null);
        if (worldRenderer != MC.levelRenderer)
            worldRenderer.close();
    }

    public static void initializeIfNeeded() {
        if (isInitialized)
            return;

        ResourceKey<Level> dim = MC.level.dimension();
        clientWorldCache.put(dim, MC.level);
        worldRendererCache.put(dim, MC.levelRenderer);

        isInitialized = true;
        isGeneratingLevel = false;
    }

    @NotNull
    public static ClientLevel getClientWorld(ResourceKey<Level> dimension) {
        initializeIfNeeded();

        if (!clientWorldCache.containsKey(dimension))
            return createSecondaryClientWorld(dimension);

        return clientWorldCache.get(dimension);
    }

    private static ClientLevel createSecondaryClientWorld(ResourceKey<Level> dimension) {
        isGeneratingLevel = true;

        RegistryAccess registryManager = MC.player.connection.registryAccess();

        // FIXME: isFlatを取得する仕組みをつくる
        ClientLevel.ClientLevelData properties = new ClientLevel.ClientLevelData(MC.level.getDifficulty(), MC.level.getLevelData().isHardcore(), false);
        ResourceKey<DimensionType> dimensionTypeResourceKey = ResourceKey.create(Registries.DIMENSION_TYPE, dimension.location());
        Holder<DimensionType> dimensionType = registryManager.registryOrThrow(Registries.DIMENSION_TYPE).getHolderOrThrow(dimensionTypeResourceKey);
        int chunkLoadDistance = 3;
        int simulationDistance = MC.level.getServerSimulationDistance();

        LevelRenderer worldRenderer = new LevelRenderer(MC, MC.getEntityRenderDispatcher(), MC.getBlockEntityRenderDispatcher(), MC.renderBuffers());
        ClientLevel newWorld = new ClientLevel(MC.player.connection, properties, dimension, dimensionType, chunkLoadDistance, simulationDistance, MC::getProfiler, worldRenderer, MC.level.isDebug(), ((IEBiomeManager)MC.level.getBiomeManager()).getBiomeZoomSeed());

        worldRenderer.setLevel(newWorld);
        worldRenderer.onResourceManagerReload(MC.getResourceManager());

        clientWorldCache.put(dimension, newWorld);
        worldRendererCache.put(dimension, worldRenderer);

        isGeneratingLevel = false;

        return newWorld;
    }

    public static LevelRenderer getWorldRenderer(ResourceKey<Level> dimension) {
        initializeIfNeeded();
        LevelRenderer result = worldRendererCache.get(dimension);

        if (result == null) {
            getClientWorld(dimension);
            result = worldRendererCache.get(dimension);
        }

        return result;
    }
}
