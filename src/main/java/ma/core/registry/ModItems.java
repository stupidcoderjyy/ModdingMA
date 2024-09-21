package ma.core.registry;

import ma.core.element.ItemDef;
import net.minecraft.item.Item;

public class ModItems {
    public static final ItemDef<Item> FLUIX_CRYSTAL;

    static {
        FLUIX_CRYSTAL = ItemDef.simpleItem("fluix_crystal");
    }

    public static void init() {
    }
}
