package jp.ukon.ukon_core.foundations.blockEntity;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public abstract class SyncedBlockEntity extends BlockEntity {
    public SyncedBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return writeToClient(new CompoundTag());
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        readFromClient(tag);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();
        readFromClient(tag == null ? new CompoundTag() : tag);
    }

    private void readFromClient(CompoundTag tag) {
        load(tag);
    }
    private CompoundTag writeToClient(CompoundTag tag) {
        saveAdditional(tag);
        return tag;
    }

    public void sendData() {
        if (level instanceof ServerLevel serverLevel)
            serverLevel.getChunkSource().blockChanged(getBlockPos());
    }

    public void sync() {
        setChanged();
        sendData();
    }

    public PacketDistributor.PacketTarget getPacketTarget() {
        return PacketDistributor.TRACKING_CHUNK.with(this::getContainedChunk);
    }

    private LevelChunk getContainedChunk() {
        return level.getChunkAt(worldPosition);
    }
}
