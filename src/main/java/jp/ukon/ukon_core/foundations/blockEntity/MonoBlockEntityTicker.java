package jp.ukon.ukon_core.foundations.blockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;

public class MonoBlockEntityTicker<T extends BlockEntity> implements BlockEntityTicker<T> {
    @Override
    public void tick(Level level, BlockPos pos, BlockState state, T type) {
        if (!type.hasLevel())
            type.setLevel(level);
        ((MonoBlockEntity) type).onTick();
    }
}
