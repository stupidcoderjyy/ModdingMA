package ma.init.registry;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class ModBlockEntity extends BlockEntity {
    public ModBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public abstract void clientTick(World world, BlockPos pos, BlockState state);

    public abstract void serverTick(World world, BlockPos pos, BlockState state);
}
