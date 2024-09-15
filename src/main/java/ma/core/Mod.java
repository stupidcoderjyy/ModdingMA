package ma.core;

import ma.core.element.ItemDef;
import ma.core.registry.ModBlocks;
import ma.core.registry.ModCreativeTabs;
import ma.core.registry.ModItems;
import net.minecraft.resources.ResourceLocation;

public class Mod {
    public static final String MOD_ID = "ma";

    protected Mod() {
        ItemDef.pushTab(ModCreativeTabs.MAIN);
        ModItems.init();
        ModBlocks.init();
        ItemDef.popTab();
        ModRegistry.registerItems();
        ModRegistry.registerBlocks();
        ModRegistry.registerCreativeTabs();
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
