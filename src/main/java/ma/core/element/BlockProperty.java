package ma.core.element;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;
import net.minecraft.block.AbstractBlock.Settings;

class BlockProperty {
    final List<Consumer<Settings>> modifiers = new ArrayList<>();
    final boolean removeOnGet;
    private final BlockProperty parent;

    BlockProperty(boolean removeOnGet, @Nullable BlockProperty parent) {
        this.removeOnGet = removeOnGet;
        this.parent = parent;
    }

    Settings get() {
        Settings p = Settings.create();
        BlockProperty bp = this;
        Stack<BlockProperty> properties = new Stack<>();
        while (bp != null) {
            properties.push(bp);
            bp = bp.parent;
        }
        while (!properties.empty()) {
            properties.pop().modifiers.forEach(c -> c.accept(p));
        }
        return p;
    }

    BlockProperty dup() {
        BlockProperty bp = new BlockProperty(removeOnGet, parent);
        bp.modifiers.addAll(modifiers);
        return bp;
    }
}
