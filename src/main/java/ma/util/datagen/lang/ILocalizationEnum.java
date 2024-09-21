package ma.util.datagen.lang;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public interface ILocalizationEnum {
    String defaultName();
    String translationKey();

    default MutableText text() {
        return Text.translatable(translationKey());
    }

    default MutableText text(Object... args) {
        return Text.translatable(translationKey(), args);
    }

    default MutableText withSuffix(String text) {
        return text().copy().append(text);
    }

    default MutableText withSuffix(Text text) {
        return text().copy().append(text);
    }
}
