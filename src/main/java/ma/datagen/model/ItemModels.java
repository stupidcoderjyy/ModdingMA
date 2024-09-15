package ma.datagen.model;

import ma.core.Mod;
import ma.core.registry.BlockDef;
import ma.core.registry.ItemDef;
import ma.core.registry.ModBlocks;
import ma.core.registry.ModItems;
import ma.util.datagen.DataProviders;

public class ItemModels {
    public static void build() {
        simple(ModItems.FLUIX_CRYSTAL);

        //Blocks
        blockItem(ModBlocks.QUARTZ_BLOCK);
    }

    private static void simple(ItemDef<?> item) {
        DataProviders.MODEL_ITEM.model(item.loc)
                .parent("minecraft:item/generated")
                .texture("layer0", item.loc);
    }

    private static void blockItem(BlockDef<?> block) {
        DataProviders.MODEL_ITEM.model(block.loc)
                .parent(Mod.expandLoc("block", block.loc));
    }
}
