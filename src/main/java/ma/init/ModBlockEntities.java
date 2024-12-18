package ma.init;

import ma.blockentity.ControllerBlockEntity;
import ma.init.registry.BlockEntityDef;

public class ModBlockEntities {
    public static final BlockEntityDef<?> CONTROLLER;

    static {
        CONTROLLER = BlockEntityDef.create("controller", ControllerBlockEntity::new, ModBlocks.CONTROLLER);
    }

    public static void init() {
    }
}
