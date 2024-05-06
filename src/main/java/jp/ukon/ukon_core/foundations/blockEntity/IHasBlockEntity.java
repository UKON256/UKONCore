package jp.ukon.ukon_core.foundations.blockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public interface IHasBlockEntity<T extends BlockEntity> extends EntityBlock {
    Class<T> getBlockEntityClass();
    BlockEntityType<? extends T> getBlockEntityType();

    default void withBlockEntityDo(BlockGetter world, BlockPos pos, Consumer<T> action) {
        getBlockEntityOptional(world, pos).ifPresent(action);
    }

    default InteractionResult onBlockEntityUse(BlockGetter world, BlockPos pos, Function<T, InteractionResult> action) {
        return getBlockEntityOptional(world, pos).map(action).orElse(InteractionResult.PASS);
    }

    default Optional<T> getBlockEntityOptional(BlockGetter world, BlockPos pos) {
        return Optional.ofNullable(getBlockEntity(world, pos));
    }

    /*public static void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState) {
        if (!state.hasBlockEntity())
            return;
        if (state.is(newState.getBlock()) && newState.hasBlockEntity())
            return;

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof MonoBlockEntity monoBlockEntity)
            monoBlockEntity.onDestroy();
        level.removeBlockEntity(pos);
    }*/

    @Override
    default BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return getBlockEntityType().create(pos, state);
    }

    @Nullable
    @Override
    default <E extends BlockEntity> BlockEntityTicker<E> getTicker(Level level, BlockState state, BlockEntityType<E> type) {
        if (MonoBlockEntity.class.isAssignableFrom(getBlockEntityClass()))
            return new MonoBlockEntityTicker<>();
        return null;
    }

    @Nullable
    default T getBlockEntity(BlockGetter world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        Class<T> _class = getBlockEntityClass();

        if (blockEntity == null)
            return null;
        if (!_class.isInstance(blockEntity))
            return null;

        return (T) blockEntity;
    }
}
