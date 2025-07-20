package jp.ukon.ukon_core.mixin.common;

import jp.ukon.ukon_core.extender.IEBiomeManager;
import net.minecraft.world.level.biome.BiomeManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BiomeManager.class)
public abstract class MixinBiomeManager implements IEBiomeManager {
    @Final
    @Shadow
    @Mutable
    private long biomeZoomSeed;

    @Override
    public long getBiomeZoomSeed() {
        return biomeZoomSeed;
    }
}
