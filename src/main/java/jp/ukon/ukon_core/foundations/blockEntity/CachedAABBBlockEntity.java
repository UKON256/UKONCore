package jp.ukon.ukon_core.foundations.blockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class CachedAABBBlockEntity extends SyncedBlockEntity {
    private AABB cachedBoundingBox;

    public CachedAABBBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public AABB getRenderBoundingBox() {
        if (cachedBoundingBox == null)
            cachedBoundingBox = createBoundingBox();
        return cachedBoundingBox;
    }

    protected AABB createBoundingBox() {
        return super.getRenderBoundingBox();
    }

    protected void invalidateBoundingBox() {
        cachedBoundingBox = null;
    }
}
