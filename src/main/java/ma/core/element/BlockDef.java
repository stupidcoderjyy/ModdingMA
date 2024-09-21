package ma.core.element;

import ma.core.Mod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlockDef<B extends Block> extends ItemDef<BlockItem> {
    private static final BlockPropertyManager bpManager = new BlockPropertyManager();
    public static final List<BlockDef<?>> MOD_BLOCKS = new ArrayList<>();
    public final B block;

    public BlockDef(Identifier loc, B block, BlockItem item) {
        super(loc, item);
        this.block = block;
        MOD_BLOCKS.add(this);
    }

    public BlockDef(Identifier loc, B block) {
        this(loc, block, new BlockItem(block, new Item.Settings()));
    }

    @Override
    public ItemStack stack(int size) {
        return new ItemStack(block, size);
    }

    public static BlockDef<Block> cubeAll(String id) {
        return block(id, Block::new);
    }

    public static <T extends Block> BlockDef<T> block(String id, Function<AbstractBlock.Settings, T> builder) {
        return new BlockDef<>(Mod.modLoc(id), builder.apply(bpManager.build()));
    }

    @SafeVarargs
    public static void pushProp(Consumer<AbstractBlock.Settings>... modifiers) {
        bpManager.pushProp(false, false, modifiers);
    }

    @SafeVarargs
    public static void inheritProp(Consumer<AbstractBlock.Settings> ... modifiers) {
        bpManager.pushProp(true, false, modifiers);
    }

    @SafeVarargs
    public static void pushPropDisposable(Consumer<AbstractBlock.Settings> ... modifiers) {
        bpManager.pushProp(false, true, modifiers);
    }

    @SafeVarargs
    public static void inheritPropDisposable(Consumer<AbstractBlock.Settings> ... modifiers) {
        bpManager.pushProp(true, true, modifiers);
    }

    public static void dupProp() {
        bpManager.dupProp();
    }

    public static void popProp() {
        bpManager.popProp();
    }
}
