package ma.init.element;

import ma.init.Mod;
import ma.init.registry.AbstractItemDef;
import ma.init.registry.ITranslatable;
import ma.util.datagen.DataProviders;
import ma.util.datagen.blockstate.Model;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;
import java.util.function.Function;

public class BlockDef<B extends Block> extends AbstractItemDef<BlockItem, BlockDef<B>> implements ITranslatable<BlockDef<B>> {
    private static final BlockPropertyManager bpManager = new BlockPropertyManager();
    public final B block;

    public BlockDef(Identifier loc, B block, BlockItem item) {
        super(loc, item);
        this.block = block;
        modifyCommonRegistry(reg -> reg
                .add(def -> Registry.register(Registries.BLOCK, def.loc, def.block)));
        modifyDataGenRegistry(reg -> reg
                .add(def -> DataProviders.MODEL_BLOCK.model(def.loc).parent("minecraft:block/cube_all").texture("all", def.loc))
                .add(def -> DataProviders.BLOCK_STATE.variants(def.loc).condition(state -> new Model(def.loc)))
                .add(def -> DataProviders.MODEL_ITEM.model(def.loc).parent(Mod.expandLoc("block", def.loc))));
    }

    @Override
    public BlockDef<B> setName(String en_us, String zh_cn) {
        return genLanguage("block", loc, en_us, zh_cn);
    }

    public BlockDef(Identifier loc, B block) {
        this(loc, block, new BlockItem(block, new Item.Settings()));
    }

    @Override
    public ItemStack stack(int size) {
        return new ItemStack(block, size);
    }

    public static BlockDef<Block> simple(String id) {
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
