package ma.core;

import ma.core.element.ItemDef;
import ma.core.registry.ModBlocks;
import ma.core.registry.ModCreativeTabs;
import ma.core.registry.ModItems;
import net.minecraft.util.Identifier;

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

    public static Identifier modLoc(String path) {
        return new Identifier(MOD_ID, path);
    }

    public static Identifier vanillaLoc(String path) {
        return new Identifier(path);
    }

    public static Identifier expandLoc(String prefix, Identifier loc) {
        String path = loc.getPath();
        if (path.indexOf('/') > 0) {
            return loc;
        }
        return new Identifier(loc.getNamespace(), prefix + "/" + path);
    }
}
