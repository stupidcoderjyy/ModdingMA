package ma.init;

import ma.block.ControllerBlock;
import ma.init.registry.BlockDef;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;

import java.util.function.Consumer;

public class ModBlocks {
    public static final BlockDef<Block> QUARTZ_BLOCK;
    public static final BlockDef<ControllerBlock> CONTROLLER;

    static {
        BlockDef.pushProp(Types.QUARTZ);
        QUARTZ_BLOCK = BlockDef.simple("quartz_block").setName("Quartz Block", "赛特斯石英块");
        CONTROLLER = BlockDef.block("controller", ControllerBlock::new).setName("Controller", "控制器");
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
