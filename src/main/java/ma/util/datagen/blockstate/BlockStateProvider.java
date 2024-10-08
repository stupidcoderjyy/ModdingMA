package ma.util.datagen.blockstate;

import ma.util.datagen.ModDataProvider;
import ma.util.datagen.ResourceType;
import ma.util.datagen.blockstate.multipart.MultiPartBlockStateBuilder;
import ma.util.datagen.blockstate.variants.VariantsBlockStateBuilder;
import net.minecraft.data.DataWriter;
import net.minecraft.util.Identifier;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class BlockStateProvider extends ModDataProvider<BlockStateProvider> {
    private final Map<Identifier, IBuilderElement> stateBuilders = new HashMap<>();

    public BlockStateProvider() {
        super(ResourceType.BLOCK_STATE);
    }

    /**
     * 创建一个枚举方块状态文件。枚举方块状态文件定义了方块所有属性的所有可能情况
     * @param loc 方块资源路径
     * @return 构造器
     */
    public VariantsBlockStateBuilder variants(Identifier loc) {
        ensureUnlocked();
        var builder = new VariantsBlockStateBuilder();
        stateBuilders.put(loc, builder);
        return builder;
    }

    public MultiPartBlockStateBuilder multipart(Identifier loc) {
        ensureUnlocked();
        var builder = new MultiPartBlockStateBuilder();
        stateBuilders.put(loc, builder);
        return builder;
    }

    @Override
    public CompletableFuture<?> run(DataWriter cache) {
        return getCollectedTask(stateBuilders.entrySet(),
                e -> getJsonWritingTask(e.getKey(), e.getValue().toJson(), cache));
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
