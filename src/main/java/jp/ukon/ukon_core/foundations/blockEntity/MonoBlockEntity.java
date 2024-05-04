package jp.ukon.ukon_core.foundations.blockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class MonoBlockEntity extends CachedAABBBlockEntity {
    public MonoBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public void onInit() {
    }

    private boolean wasInitialized = false;
    public void onTick() {
        if (!wasInitialized) {
            onInit();
            wasInitialized = true;
        }
    }

    public void onDestroy() {
    }
}
