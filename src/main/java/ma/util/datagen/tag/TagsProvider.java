package ma.util.datagen.tag;

import ma.core.Mod;
import ma.util.datagen.ModDataProvider;
import ma.util.datagen.ResourceType;
import net.minecraft.data.DataWriter;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class TagsProvider<T extends Tag<T,?>> extends ModDataProvider<TagsProvider<T>> {
    final Map<Identifier, T> tags = new HashMap<>();
    final String type;
    private final Supplier<T> builder;

    public TagsProvider(String type, Supplier<T> builder) {
        super(ResourceType.TAG);
        this.type = type;
        this.builder = builder;
    }

    public T of(Identifier loc) {
        return tags.computeIfAbsent(loc, k -> builder.get());
    }

    public T of(TagKey<?> key) {
        return of(key.id());
    }

    @Override
    public CompletableFuture<?> run(DataWriter cachedOutput) {
        return getCollectedTask(tags.entrySet(), entry -> {
            Identifier loc = Mod.expandLoc(type, entry.getKey());
            return getJsonWritingTask(loc, entry.getValue().toJson(), cachedOutput);
        });
    }

    @Override
    public String getName() {
        return "TagsProvider[" + type + "]";
    }
}
