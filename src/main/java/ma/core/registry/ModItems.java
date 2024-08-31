package ma.core.registry;

import net.minecraft.world.item.Item;

public class ModItems {
    public static final ItemDef<Item> FLUIX_CRYSTAL;

    static {
        FLUIX_CRYSTAL = ItemDef.simple("fluix_crystal");
    }

    public static void init() {
    }
}
