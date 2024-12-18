package ma.init.registry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ModBlockWithEntity extends Block implements BlockEntityProvider {
    protected BlockEntityDef<?> def;

    public ModBlockWithEntity(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return def.type.instantiate(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (type != def.type) {
            return null;
        }
        return (BlockEntityTicker<T>) (world.isClient ? def.getClientTicker() : def.getServerTicker());
    }

    void setDef(BlockEntityDef<?> def) {
        this.def = def;
    }
}
