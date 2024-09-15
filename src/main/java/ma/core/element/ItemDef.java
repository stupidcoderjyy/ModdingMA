package ma.core.element;

import ma.core.Mod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ItemDef<I extends Item> implements ItemLike {
    public static final List<ItemDef<?>> MOD_ITEMS = new ArrayList<>();
    private static final Stack<ResourceKey<CreativeModeTab>> TABS = new Stack<>();
    public final I item;
    public final ResourceLocation loc;

    public ItemDef(ResourceLocation loc, I item) {
        this.item = item;
        this.loc = loc;
        MOD_ITEMS.add(this);
        for (var tab : TABS) {
            ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> entries.accept(this));
        }
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

    public static void pushTab(ModCreativeTab tab) {
        TABS.push(tab.key);
    }

    public static void pushTab(ResourceKey<CreativeModeTab> tab) {
        TABS.push(tab);
    }

    public static void popTab() {
        TABS.pop();
    }
}
