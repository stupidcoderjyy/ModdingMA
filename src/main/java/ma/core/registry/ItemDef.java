package ma.core.registry;

import ma.core.Mod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;

public class ItemDef<I extends Item> implements ItemLike {
    public static final List<ItemDef<?>> MOD_ITEMS = new ArrayList<>();
    public final I item;
    public final ResourceLocation loc;

    public ItemDef(ResourceLocation loc, I item) {
        this.item = item;
        this.loc = loc;
        MOD_ITEMS.add(this);
    }

    public ItemDef(String id, I item) {
        this(Mod.modLoc(id), item);
    }

    @Override
    public Item asItem() {
        return item;
    }

    public static ItemDef<Item> simple(String id) {
        return new ItemDef<>(id, new Item(new Item.Properties()));
    }
}
