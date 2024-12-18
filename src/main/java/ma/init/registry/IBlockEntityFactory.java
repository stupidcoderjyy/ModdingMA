package ma.init.registry;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public interface IBlockEntityFactory<E extends BlockEntity> {
    E create(BlockEntityType<E> type, BlockPos pos, BlockState state);
}
