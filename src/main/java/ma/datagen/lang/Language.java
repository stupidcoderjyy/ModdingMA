package ma.datagen.lang;

import ma.core.Mod;
import ma.core.registry.ItemDef;
import ma.core.registry.ModItems;
import ma.util.datagen.DataProviders;

public class Language {
    public static void build() {
        item(ModItems.FLUIX_CRYSTAL, "Fluix Crystal", "福鲁伊克斯水晶");
    }

    private static void item(ItemDef<?> item, String en_us, String zh_cn) {
        String prefix = "item." + Mod.MOD_ID + "." + item.loc.getPath();
        DataProviders.LANG_EN_US.register(prefix, en_us);
        DataProviders.LANG_ZH_CN.register(prefix, zh_cn);
    }
}
