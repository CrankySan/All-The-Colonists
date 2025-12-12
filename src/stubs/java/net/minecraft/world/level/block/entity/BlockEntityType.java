package net.minecraft.world.level.block.entity;

import java.util.function.BiFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityType<T extends BlockEntity> {
    private final BiFunction<BlockPos, BlockState, T> factory;

    private BlockEntityType(BiFunction<BlockPos, BlockState, T> factory) {
        this.factory = factory;
    }

    public T create(BlockPos pos, BlockState state) {
        return factory.apply(pos, state);
    }

    public static class Builder<T extends BlockEntity> {
        private final BiFunction<BlockPos, BlockState, T> factory;
        public Builder(BiFunction<BlockPos, BlockState, T> factory) { this.factory = factory; }
        public static <T extends BlockEntity> Builder<T> of(BiFunction<BlockPos, BlockState, T> factory, Object block) { return new Builder<>(factory); }
        public BlockEntityType<T> build(Object dataFixer) { return new BlockEntityType<>(factory); }
    }
}
