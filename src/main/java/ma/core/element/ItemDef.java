package ma.core.element;

import ma.core.Mod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ItemDef<I extends Item> implements ItemConvertible {
    public static final List<ItemDef<?>> MOD_ITEMS = new ArrayList<>();
    private static final Stack<RegistryKey<ItemGroup>> TABS = new Stack<>();
    public final I item;
    public final Identifier loc;

    public ItemDef(Identifier loc, I item) {
        this.item = item;
        this.loc = loc;
        MOD_ITEMS.add(this);
        for (var tab : TABS) {
            ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> entries.add(this));
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
        return new ItemDef<>(Mod.modLoc(id), new Item(new Item.Settings()));
    }

    public static void pushTab(ModCreativeTab tab) {
        TABS.push(tab.key);
    }

    public static void pushTab(RegistryKey<ItemGroup> tab) {
        TABS.push(tab);
    }

    public static void popTab() {
        TABS.pop();
    }
}
