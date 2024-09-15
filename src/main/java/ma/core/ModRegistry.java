package ma.core;

import ma.core.element.BlockDef;
import ma.core.element.ItemDef;
import ma.core.registry.ModCreativeTabs;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class ModRegistry {
    public static void registerItems() {
        for (ItemDef<?> def : ItemDef.MOD_ITEMS) {
            Registry.register(BuiltInRegistries.ITEM, def.loc, def.item);
        }
        ItemDef.MOD_ITEMS.clear();
    }

    public static void registerBlocks() {
        for (BlockDef<?> def : BlockDef.MOD_BLOCKS) {
            Registry.register(BuiltInRegistries.BLOCK, def.loc, def.block);
        }
        BlockDef.MOD_BLOCKS.clear();
    }

    public static void registerCreativeTabs() {
        ModCreativeTabs.MAIN.register();
    }
}
