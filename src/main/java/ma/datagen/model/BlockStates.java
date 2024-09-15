package ma.datagen.model;

import ma.core.element.BlockDef;
import ma.core.registry.ModBlocks;
import ma.util.datagen.DataProviders;
import ma.util.datagen.blockstate.Model;

public class BlockStates {
    public static void build() {
        simple(ModBlocks.QUARTZ_BLOCK);
    }

    private static void simple(BlockDef<?> block) {
        DataProviders.BLOCK_STATE.variants(block.loc).condition(state -> new Model(block.loc));
    }
}
