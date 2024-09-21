package ma.core.element;

import java.util.Arrays;
import java.util.Stack;
import java.util.function.Consumer;
import net.minecraft.block.AbstractBlock.Settings;

class BlockPropertyManager {
    private final Stack<BlockProperty> properties = new Stack<>();

    @SafeVarargs
    final void pushProp(boolean inherit, boolean removeOnGet, Consumer<Settings> ... modifiers) {
        BlockProperty parentBp = (inherit && !properties.empty()) ? properties.peek() : null;
        BlockProperty bp = new BlockProperty(removeOnGet, parentBp);
        bp.modifiers.addAll(Arrays.asList(modifiers));
        properties.push(bp);
    }

    void popProp() {
        properties.pop();
    }

    void dupProp() {
        properties.push(properties.peek().dup());
    }

    Settings build() {
        if (properties.empty()) {
            return Settings.create();
        }
        BlockProperty bp = properties.peek();
        if (bp.removeOnGet) {
            properties.pop();
        }
        return bp.get();
    }
}
