package ma.block;

import ma.blockentity.ControllerBlockEntity;
import ma.init.registry.ModBlockWithEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ControllerBlock extends ModBlockWithEntity {
    public ControllerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        var be = world.getBlockEntity(pos);
        if (be instanceof ControllerBlockEntity cbe) {
            if (!world.isClient) {
                cbe.setItem(player.getStackInHand(hand));
                return ActionResult.SUCCESS;
            }
            return ActionResult.CONSUME;
        }
        return ActionResult.PASS;
    }
}
