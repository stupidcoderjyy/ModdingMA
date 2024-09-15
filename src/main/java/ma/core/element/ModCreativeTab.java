package ma.core.element;

import com.google.common.base.Preconditions;
import ma.core.Mod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModCreativeTab {
    @Nullable private List<ItemLike> displayItems = new ArrayList<>();
    private final Supplier<ItemLike> iconSupplier;
    public final ResourceKey<CreativeModeTab> key;
    public final String translationKey;

    public ModCreativeTab(String id, Supplier<ItemLike> icon) {
        this.iconSupplier = icon;
        this.translationKey = "tab." + Mod.MOD_ID + "." + id;
        this.key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, Mod.modLoc(id));
    }

    public void add(ItemLike item) {
        Preconditions.checkNotNull(displayItems, "closed");
        displayItems.add(item);
    }

    public void register() {
        Preconditions.checkNotNull(displayItems, "closed");
        final var list = displayItems;
        displayItems = null;
        var tab = FabricItemGroup.builder()
                .title(Component.translatable(translationKey))
                .icon(() -> new ItemStack(iconSupplier.get()))
                .displayItems((p, out) -> list.forEach(out::accept))
                .build();
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, tab);
    }
}
