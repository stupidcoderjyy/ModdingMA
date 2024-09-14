package ma.datagen.models;

import ma.core.registry.ItemDef;
import ma.core.registry.ModItems;
import ma.util.datagen.DataProviders;

public class ItemModels {
    public static void build() {
        simple(ModItems.FLUIX_CRYSTAL);
    }

    private static void simple(ItemDef<?> item) {
        DataProviders.MODEL_ITEM.model(item.loc)
                .parent("minecraft:item/generated")
                .texture("layer0", item.loc);
    }
}
