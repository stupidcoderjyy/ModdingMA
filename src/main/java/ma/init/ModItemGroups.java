package ma.init;

import ma.init.registry.ItemGroupDef;

public class ModItemGroups {
    public static final ItemGroupDef MAIN;

    static {
        MAIN = new ItemGroupDef("main", () -> ModBlocks.QUARTZ_BLOCK)
                .setName("Fabric Modding Basic Tutorial", "Fabric模组基本教程");
    }

    public static void init() {
    }
}