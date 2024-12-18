package ma.init.registry;

import ma.init.Mod;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.BlockEntityType.Builder;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public final class BlockEntityDef<E extends ModBlockEntity> extends AbstractDef<BlockEntityDef<E>> {
    public final BlockEntityType<E> type;
    private final Identifier loc;
    private final Block[] blockArr;

    @SafeVarargs
    private BlockEntityDef(String shortId, IBlockEntityFactory<E> factory, BlockDef<? extends ModBlockWithEntity> ... blocks) {
        this.blockArr = Arrays.stream(blocks).map(d -> d.block).toArray(Block[]::new);
        this.loc = Mod.modLoc(shortId);
        var ref = new AtomicReference<BlockEntityType<E>>();
        this.type = Builder.create((p, s) -> factory.create(ref.get(), p, s), blockArr).build(null);
        ref.set(type);
        commonRegistry().add(def -> {
            Registry.register(Registries.BLOCK_ENTITY_TYPE, loc, type);
            for (Block b : blockArr) {
                ((ModBlockWithEntity) b).setDef(this);
            }
        });
    }

    @SafeVarargs
    public static <T extends ModBlockEntity> BlockEntityDef<T> create(String shortId, IBlockEntityFactory<T> factory, BlockDef<? extends ModBlockWithEntity> ... blocks) {
        return new BlockEntityDef<>(shortId, factory, blocks);
    }

    public BlockEntityTicker<E> getServerTicker() {
        return ((world, pos, state, blockEntity) -> blockEntity.serverTick(world, pos, state));
    }

    public BlockEntityTicker<E> getClientTicker() {
        return ((world, pos, state, blockEntity) -> blockEntity.clientTick(world, pos, state));
    }
}
