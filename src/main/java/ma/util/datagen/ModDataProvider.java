package ma.util.datagen;

import com.google.common.base.Preconditions;
import com.google.gson.JsonElement;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.util.Identifier;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * 模组数据生成器
 * @param <T> 子类，便于链式调用
 */
public abstract class ModDataProvider<T extends ModDataProvider<T>> implements DataProvider {
    private static final List<ModDataProvider<?>> PROVIDERS = new ArrayList<>();
    protected DataOutput output;
    private final ResourceType resourceType;
    private boolean locked = false;

    /**
     *
     * @param resourceType 资源类型，这决定了文件的路径和后缀名
     */
    public ModDataProvider(ResourceType resourceType) {
        PROVIDERS.add(this);
        this.resourceType = resourceType;
    }

    /**
     * 初始化逻辑，当初始化完成后，数据生成器会锁定，此时无法再注册数据
     * @param output 输出路径（由MC提供）
     * @return 调用者
     */
    protected T init(DataOutput output) {
        this.output = output;
        this.locked = true;
        return (T)this;
    }

    protected CompletableFuture<?> getJsonWritingTask(
            Identifier loc,
            JsonElement obj,
            DataWriter cache) {
        Path target = output.resolvePath(resourceType.type)
                .resolve(loc.getNamespace())
                .resolve(resourceType.pathPrefix)
                .resolve(loc.getPath() + resourceType.pathSuffix);
        return DataProvider.writeToPath(cache, obj, target);
    }

    protected <U> CompletableFuture<?> getCollectedTask(
            Collection<U> c,
            Function<U, CompletableFuture<?>> supplier) {
        CompletableFuture<?>[] futures = new CompletableFuture<?>[c.size()];
        int i = 0;
        for (var e : c) {
            futures[i++] = supplier.apply(e);
        }
        return CompletableFuture.allOf(futures);
    }

    /**
     * 确保调用时生成器未锁定
     */
    protected final void ensureUnlocked() {
        Preconditions.checkState(!locked, "locked:" + getName());
    }

    public static void registerProviders(DataGenerator.Pack pack) {
        PROVIDERS.forEach(p -> pack.addProvider(p::init));
    }
}
