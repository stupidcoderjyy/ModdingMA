package ma.core.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

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
        public static final Consumer<BlockBehaviour.Properties> QUARTZ = p -> p
                .strength(3.0f,5.0f)
                .mapColor(MapColor.QUARTZ)
                .sound(SoundType.STONE)
                .requiresCorrectToolForDrops();
    }
}
