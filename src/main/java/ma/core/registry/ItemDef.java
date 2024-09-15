package ma.core.registry;

import ma.core.Mod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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

    @Override
    public Item asItem() {
        return item;
    }

    public ItemStack stack(int size) {
        return new ItemStack(item, size);
    }

    public static ItemDef<Item> simpleItem(String id) {
        return new ItemDef<>(Mod.modLoc(id), new Item(new Item.Properties()));
    }
}
