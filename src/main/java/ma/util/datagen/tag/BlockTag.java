package ma.util.datagen.tag;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import net.minecraft.block.Block;

public class BlockTag extends Tag<BlockTag, Block> {
    @Override
    JsonElement serialize(Block block) {
        return new JsonPrimitive(block.getRegistryEntry().registryKey().getValue().toString());
    }
}
