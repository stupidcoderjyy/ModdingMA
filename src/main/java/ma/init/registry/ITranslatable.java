package ma.init.registry;

import ma.init.Mod;
import ma.util.datagen.DataProviders;
import net.minecraft.util.Identifier;

public interface ITranslatable<D extends AbstractDef<D>> {
    D setName(String en_us, String zh_cn);

    default D genLanguage(String prefix, Identifier loc, String en_us, String zh_cn) {
        return genLanguage(prefix + "." + Mod.MOD_ID + "." + loc.getPath(), en_us, zh_cn);
    }

    default D genLanguage(String key, String en_us, String zh_cn) {
        DataProviders.LANG_EN_US.register(key, en_us);
        DataProviders.LANG_ZH_CN.register(key, zh_cn);
        return (D) this;
    }
}
