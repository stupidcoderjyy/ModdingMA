package ma.util.datagen.lang;

import com.google.gson.JsonObject;
import ma.core.Mod;
import ma.util.datagen.ModDataProvider;
import ma.util.datagen.ResourceType;
import net.minecraft.data.DataWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

public class LocalizationProvider extends ModDataProvider<LocalizationProvider> {
    private final String fileName;
    private final Map<String, String> localizations = new HashMap<>();

    public LocalizationProvider(String langType) {
        super(ResourceType.LANG);
        this.fileName = langType;
    }

    public void register(String key, String text) {
        ensureUnlocked();
        localizations.put(key, text);
    }

    public <T extends Enum<T> & ILocalizationEnum> void register(Class<T> localizedEnum) {
        for (var e : localizedEnum.getEnumConstants()) {
            register(e.translationKey(), e.defaultName());
        }
    }

    @Override
    public CompletableFuture<?> run(DataWriter cachedOutput) {
        JsonObject obj = new JsonObject();
        var sorted = new TreeMap<>(localizations);
        sorted.forEach(obj::addProperty);
        return getJsonWritingTask(Mod.modLoc(fileName), obj, cachedOutput);
    }

    @Override
    public String getName() {
        return "LocalizationProvider(" + fileName + ")";
    }
}
