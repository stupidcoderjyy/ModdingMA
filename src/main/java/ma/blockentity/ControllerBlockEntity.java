package ma.blockentity;

import ma.init.Mod;
import ma.init.registry.ModBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ControllerBlockEntity extends ModBlockEntity {
    private ItemStack stack = ItemStack.EMPTY;

    public ControllerBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void clientTick(World world, BlockPos pos, BlockState state) {
        if ((world.getTimeOfDay() + 10) % 20 == 0) {
            MinecraftClient.getInstance().getMessageHandler().onGameMessage(
                    Text.literal(stack.toString()).formatted(Formatting.RED), true);
        }
    }

    @Override
    public void serverTick(World world, BlockPos pos, BlockState state) {
        if (world.getTimeOfDay() % 20 == 0) {
            world.getServer().getPlayerManager().getPlayerList().forEach(p ->
                    p.sendMessageToClient(Text.literal(stack.toString()).formatted(Formatting.AQUA), true));
            spawnItemEntity((ServerWorld) world);
        }
    }

    public void setItem(@NotNull ItemStack stack) {
        if (!this.stack.equals(stack)) {
            this.stack = stack.copy();
            markDirty();
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        if (nbt.contains("stack", NbtElement.COMPOUND_TYPE)) {
            stack = ItemStack.fromNbt(nbt.getCompound("stack"));
            Mod.getLogger().info("read:{}", stack);
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        var child = new NbtCompound();
        stack.writeNbt(child);
        nbt.put("stack", child);
        Mod.getLogger().info("stored:{}", stack);
    }

    private void spawnItemEntity(ServerWorld world) {
        ItemEntity itemEntity = new ItemEntity(world, getPos().getX() + 0.5, getPos().getY() + 1.0, getPos().getZ() + 0.5, stack.copy());
        float vx = (world.random.nextFloat() - 0.5f) * 0.5f;
        float vz = (world.random.nextFloat() - 0.5f) * 0.5f;
        Vec3d velocity = new Vec3d(vx, 1, vz);
        itemEntity.setVelocity(velocity);
        world.spawnEntity(itemEntity);
        world.getChunkManager().markForUpdate(getPos());
    }
}
