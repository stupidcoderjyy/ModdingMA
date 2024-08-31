package ma.core;

import ma.core.registry.ModItems;
import net.minecraft.resources.ResourceLocation;

public class Mod {
    public static final String MOD_ID = "ma";

    protected Mod() {
        ModItems.init();
        ModRegistry.registerItems();
    }

    public static ResourceLocation modLoc(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static ResourceLocation vanillaLoc(String path) {
        return new ResourceLocation(path);
    }

    public static ResourceLocation expandLoc(String prefix, ResourceLocation loc) {
        String path = loc.getPath();
        if (path.indexOf('/') > 0) {
            return loc;
        }
        return new ResourceLocation(loc.getNamespace(), prefix + "/" + path);
    }
}
