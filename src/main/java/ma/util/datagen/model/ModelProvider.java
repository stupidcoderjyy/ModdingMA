package ma.util.datagen.model;

import ma.core.Mod;
import ma.util.datagen.ModDataProvider;
import ma.util.datagen.ResourceType;
import net.minecraft.data.DataWriter;
import net.minecraft.util.Identifier;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class ModelProvider extends ModDataProvider<ModelProvider> {
    private final Map<Identifier, ModelBuilder> models = new HashMap<>();
    private final Function<Identifier, ModelBuilder> defaultModelBuilderSupplier;
    private final String prefix;

    public ModelProvider(String pathPrefix) {
        super(ResourceType.MODEL);
        this.prefix = pathPrefix;
        this.defaultModelBuilderSupplier = loc -> new ModelBuilder(pathPrefix, loc);
    }

    public ModelBuilder model(Identifier loc) {
        loc = Mod.expandLoc(prefix, loc);
        return models.computeIfAbsent(loc, defaultModelBuilderSupplier);
    }

    @Override
    public CompletableFuture<?> run(DataWriter cache) {
        return getCollectedTask(models.entrySet(),
                e -> getJsonWritingTask(e.getKey(), e.getValue().toJson(), cache));
    }

    @Override
    public String getName() {
        return getClass().getSimpleName() + "(" + prefix + ")";
    }
}
