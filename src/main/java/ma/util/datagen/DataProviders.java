package ma.util.datagen;

import ma.util.datagen.blockstate.BlockStateProvider;
import ma.util.datagen.lang.LocalizationProvider;
import ma.util.datagen.model.ModelProvider;
import ma.util.datagen.tag.BlockTag;
import ma.util.datagen.tag.TagsProvider;
import net.minecraft.util.Identifier;

/**
 * 模组数据提供器
 */
public class DataProviders {
    /**
     * 物品模型文件的生成器
     * <p>使用{@link ModelProvider#model}创建一个新的模型文件
     *
     * @see ma.util.datagen.model.ModelBuilder
     */
    public static final ModelProvider MODEL_ITEM = new ModelProvider("item");

    /**
     * 方块模型文件的生成器
     * <p>使用{@link ModelProvider#model}创建一个新的模型文件
     * @see ma.util.datagen.model.ModelBuilder
     */
    public static final ModelProvider MODEL_BLOCK = new ModelProvider("block");

    /**
     * 方块状态文件生成器
     * <p>使用{@link BlockStateProvider#variants(Identifier)}创建一个枚举方块状态文件
     */
    public static final BlockStateProvider BLOCK_STATE = new BlockStateProvider();

    public static final LocalizationProvider LANG_ZH_CN = new LocalizationProvider("zh_cn");

    public static final LocalizationProvider LANG_EN_US = new LocalizationProvider("en_us");

    public static final TagsProvider<BlockTag> BLOCK_TAGS = new TagsProvider<>("blocks", BlockTag::new);

    public static void init() {
    }
}
