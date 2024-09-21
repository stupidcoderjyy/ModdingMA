package ma.core.registry;

import ma.core.element.BlockDef;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;
import java.util.function.Consumer;

public class ModBlocks {
    public static final BlockDef<Block> QUARTZ_BLOCK;

    static {
        BlockDef.pushProp(Types.QUARTZ);
        QUARTZ_BLOCK = BlockDef.cubeAll("quartz_block");
        BlockDef.popProp();
    }

    public static void init() {
    }

    private static class Types {
        public static final Consumer<AbstractBlock.Settings> QUARTZ = p -> p
                .strength(3.0f,5.0f)
                .mapColor(MapColor.OFF_WHITE)
                .sounds(BlockSoundGroup.STONE)
                .requiresTool();
    }
}
