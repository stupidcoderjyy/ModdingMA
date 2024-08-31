package ma.core;

import ma.core.registry.ItemDef;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class ModRegistry {
    public static void registerItems() {
        for (ItemDef<?> def : ItemDef.MOD_ITEMS) {
            Registry.register(BuiltInRegistries.ITEM, def.loc, def.item);
        }
    }
}
