package ma.init.registry;

import ma.init.element.ItemGroupDef;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public abstract class AbstractItemDef<I extends Item, D extends AbstractItemDef<I,D>>
        extends AbstractDef<D> implements ItemConvertible {
    private static RegistryKey<ItemGroup> curGroup;
    public final I item;
    public final Identifier loc;

    public AbstractItemDef(Identifier loc, I item) {
        this.item = item;
        this.loc = loc;
        if (curGroup != null) {
            ItemGroupEvents.modifyEntriesEvent(curGroup).register(entries -> entries.add(this));
        }
        modifyCommonRegistry(reg ->
                reg.add(def -> Registry.register(Registries.ITEM, def.loc, def.item)));
    }

    @Override
    public Item asItem() {
        return item;
    }

    public ItemStack stack(int size) {
        return new ItemStack(item, size);
    }

    public static void storeInto(ItemGroupDef itemGroup) {
        curGroup = itemGroup.key;
    }

    public static void storeInto(RegistryKey<ItemGroup> itemGroup) {
        curGroup = itemGroup;
    }

    @Override
    public String toString() {
        return "ItemDef(" + loc + ")";
    }
}
