package ma.core;

import ma.core.element.BlockDef;
import ma.core.element.ItemDef;
import ma.core.registry.ModCreativeTabs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModRegistry {
    public static void registerItems() {
        for (ItemDef<?> def : ItemDef.MOD_ITEMS) {
            Registry.register(Registries.ITEM, def.loc, def.item);
        }
        ItemDef.MOD_ITEMS.clear();
    }

    public static void registerBlocks() {
        for (BlockDef<?> def : BlockDef.MOD_BLOCKS) {
            Registry.register(Registries.BLOCK, def.loc, def.block);
        }
        BlockDef.MOD_BLOCKS.clear();
    }

    public static void registerCreativeTabs() {
        ModCreativeTabs.MAIN.register();
    }
}
