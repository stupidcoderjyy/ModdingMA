package ma.init.element;

import com.google.common.base.Preconditions;
import ma.init.Mod;
import ma.init.registry.AbstractDef;
import ma.init.registry.ITranslatable;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ItemGroupDef extends AbstractDef<ItemGroupDef> implements ITranslatable<ItemGroupDef> {
    public static final List<ItemGroupDef> ITEM_GROUPS = new ArrayList<>();
    @Nullable private List<ItemConvertible> displayItems = new ArrayList<>();
    private final Supplier<ItemConvertible> iconSupplier;
    public final RegistryKey<ItemGroup> key;
    public final String translationKey;

    public ItemGroupDef(String id, Supplier<ItemConvertible> icon) {
        this.iconSupplier = icon;
        this.translationKey = "tab." + Mod.MOD_ID + "." + id;
        this.key = RegistryKey.of(RegistryKeys.ITEM_GROUP, Mod.modLoc(id));
        ITEM_GROUPS.add(this);
        modifyCommonRegistry(r -> r.add(ItemGroupDef::commonRegister));
    }

    @Override
    public ItemGroupDef setName(String en_us, String zh_cn) {
        return genLanguage(translationKey, en_us, zh_cn);
    }

    public void add(ItemConvertible item) {
        Preconditions.checkNotNull(displayItems, "closed");
        displayItems.add(item);
    }

    private void commonRegister() {
        Preconditions.checkNotNull(displayItems, "closed");
        final var list = displayItems;
        displayItems = null;
        var tab = FabricItemGroup.builder()
                .displayName(Text.translatable(translationKey))
                .icon(() -> new ItemStack(iconSupplier.get()))
                .entries((p, out) -> list.forEach(out::add))
                .build();
        Registry.register(Registries.ITEM_GROUP, key, tab);
    }
}
