package ma.core.element;

import ma.core.Mod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlockDef<B extends Block> extends ItemDef<BlockItem> {
    private static final BlockPropertyManager bpManager = new BlockPropertyManager();
    public static final List<BlockDef<?>> MOD_BLOCKS = new ArrayList<>();
    public final B block;

    public BlockDef(ResourceLocation loc, B block, BlockItem item) {
        super(loc, item);
        this.block = block;
        MOD_BLOCKS.add(this);
    }

    public BlockDef(ResourceLocation loc, B block) {
        this(loc, block, new BlockItem(block, new Item.Properties()));
    }

    @Override
    public ItemStack stack(int size) {
        return new ItemStack(block, size);
    }

    public static BlockDef<Block> cubeAll(String id) {
        return block(id, Block::new);
    }

    public static <T extends Block> BlockDef<T> block(String id, Function<BlockBehaviour.Properties, T> builder) {
        return new BlockDef<>(Mod.modLoc(id), builder.apply(bpManager.build()));
    }

    @SafeVarargs
    public static void pushProp(Consumer<BlockBehaviour.Properties>... modifiers) {
        bpManager.pushProp(false, false, modifiers);
    }

    @SafeVarargs
    public static void inheritProp(Consumer<BlockBehaviour.Properties> ... modifiers) {
        bpManager.pushProp(true, false, modifiers);
    }

    @SafeVarargs
    public static void pushPropDisposable(Consumer<BlockBehaviour.Properties> ... modifiers) {
        bpManager.pushProp(false, true, modifiers);
    }

    @SafeVarargs
    public static void inheritPropDisposable(Consumer<BlockBehaviour.Properties> ... modifiers) {
        bpManager.pushProp(true, true, modifiers);
    }

    public static void dupProp() {
        bpManager.dupProp();
    }

    public static void popProp() {
        bpManager.popProp();
    }
}
