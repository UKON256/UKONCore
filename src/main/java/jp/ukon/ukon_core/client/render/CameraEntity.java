package jp.ukon.ukon_core.client.render;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

/**
 * レンダリングに使う際、カメラのターゲットに使うEntity
 */
public class CameraEntity extends Entity {
    public CameraEntity(Level pLevel) {
        super(EntityType.PLAYER, pLevel);
    }

    @Override
    protected void defineSynchedData() {
    }
    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
    }
    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }
}
