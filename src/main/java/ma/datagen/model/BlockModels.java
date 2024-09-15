package ma.datagen.model;

import ma.core.registry.BlockDef;
import ma.core.registry.ModBlocks;
import ma.util.datagen.DataProviders;

public class BlockModels {
    public static void build() {
        cubeAll(ModBlocks.QUARTZ_BLOCK);
    }

    private static void cubeAll(BlockDef<?> block) {
        DataProviders.MODEL_BLOCK.model(block.loc)
                .parent("minecraft:block/cube_all")
                .texture("all", block.loc);
    }
}
