package ma.datagen.lang;

import ma.core.Mod;
import ma.core.element.BlockDef;
import ma.core.element.ItemDef;
import ma.core.element.ModCreativeTab;
import ma.core.registry.ModBlocks;
import ma.core.registry.ModCreativeTabs;
import ma.core.registry.ModItems;
import ma.util.datagen.DataProviders;

public class Language {
    public static void build() {
        item(ModItems.FLUIX_CRYSTAL, "Fluix Crystal", "福鲁伊克斯水晶");
        block(ModBlocks.QUARTZ_BLOCK, "Quartz Block", "赛特斯石英块");
        creativeTab(ModCreativeTabs.MAIN, "Fabric Modding Basic Tutorial", "Fabric模组基本教程");
    }

    private static void item(ItemDef<?> item, String en_us, String zh_cn) {
        element("item", item.loc.getPath(), en_us, zh_cn);
    }

    private static void block(BlockDef<?> block, String en_us, String zh_cn) {
        element("block", block.loc.getPath(), en_us, zh_cn);
    }

    private static void creativeTab(ModCreativeTab tab, String en_us, String zh_cn) {
        custom(tab.translationKey, en_us, zh_cn);
    }

    private static void element(String prefix, String path, String en_us, String zh_cn) {
        custom(prefix + "." + Mod.MOD_ID + "." + path, en_us, zh_cn);
    }

    private static void custom(String key, String en_us, String zh_cn) {
        DataProviders.LANG_EN_US.register(key, en_us);
        DataProviders.LANG_ZH_CN.register(key, zh_cn);
    }
}
