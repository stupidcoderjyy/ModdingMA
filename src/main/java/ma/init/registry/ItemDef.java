package ma.init.registry;

import ma.init.Mod;
import ma.util.datagen.DataProviders;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ItemDef<I extends Item> extends AbstractItemDef<I, ItemDef<I>> implements ITranslatable<ItemDef<I>> {
    public ItemDef(Identifier loc, I item) {
        super(loc, item);
        modifyDataGenRegistry(r -> r
                .add(def -> DataProviders.MODEL_ITEM.model(def.loc).parent("minecraft:item/generated").texture("layer0", def.loc)));
    }

    @Override
    public ItemDef<I> setName(String en_us, String zh_cn) {
        return genLanguage("item", loc, en_us, zh_cn);
    }

    public static ItemDef<Item> simple(String id) {
        return new ItemDef<>(Mod.modLoc(id), new Item(new Item.Settings()));
    }
}
