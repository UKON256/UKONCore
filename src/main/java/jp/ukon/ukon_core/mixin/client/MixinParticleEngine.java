package jp.ukon.ukon_core.mixin.client;

import jp.ukon.ukon_core.extender.IEParticleEngine;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ParticleEngine.class)
public abstract class MixinParticleEngine implements IEParticleEngine {
    @Shadow
    protected ClientLevel level;

    @Override
    public void setWorld(ClientLevel level) {
        this.level = level;
    }
}
