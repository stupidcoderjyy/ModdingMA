package ma.init;

import ma.init.registry.ItemDef;
import net.minecraft.item.Item;

public class ModItems {
    public static final ItemDef<Item> FLUIX_CRYSTAL;

    static {
        ItemDef.storeInto(ModItemGroups.MAIN);
        FLUIX_CRYSTAL = ItemDef.simple("fluix_crystal").setName("Fluix Crystal", "福鲁伊克斯水晶");
    }

    public static void init() {
    }
}
