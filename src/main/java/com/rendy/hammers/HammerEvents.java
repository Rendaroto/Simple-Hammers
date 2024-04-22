package com.rendy.hammers;

import com.rendy.hammers.HammerItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.PickaxeItem;
import net.neoforged.fml.common.Mod;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class HammerEvents {

    @SubscribeEvent
    public static void onBlockBreak(@NotNull final BlockEvent.BreakEvent event)
    {
        if (event.getState().canOcclude())
        {
            final ItemStack item = event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND);
            if (item.getItem() instanceof HammerItem)
            {
                final ItemStack mainHand = event.getPlayer().getMainHandItem();
                final Level level = event.getPlayer().getCommandSenderWorld();
                final double hardness = event.getState().getDestroySpeed(level, event.getPos());
                if(!event.getPlayer().isShiftKeyDown()) {
                    for (BlockPos pos : getAffectedPos(event.getPlayer())) {
                        final BlockState state = level.getBlockState(pos);
                        if (hardness * 2 >= state.getDestroySpeed(level, pos) && isBestTool(state, level, pos, item, event.getPlayer()) && state.getDestroySpeed(level, pos) >= 0f) {
                            state.getBlock().playerDestroy(level, event.getPlayer(), pos, state, level.getBlockEntity(pos), mainHand);
                            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                        }
                    }
                }
                else{

                    final HitResult rayTrace = rayTrace(event.getPlayer().level(), event.getPlayer(), ClipContext.Fluid.NONE);
                    final BlockHitResult rayTraceResult = (BlockHitResult) rayTrace;
                    final BlockPos center = rayTraceResult.getBlockPos();

                    final BlockState state = level.getBlockState(center);

                    if (hardness * 2 >= state.getDestroySpeed(level, center) && isBestTool(state, level, center, item, event.getPlayer()) && state.getDestroySpeed(level, center) >= 0f) {
                        state.getBlock().playerDestroy(level, event.getPlayer(), center, state, level.getBlockEntity(center), mainHand);
                        level.setBlockAndUpdate(center, Blocks.AIR.defaultBlockState());
                    }
                }
            }
        }
    }

    private static boolean isBestTool(final BlockState target, final LevelAccessor level, final BlockPos pos, final ItemStack stack, final Player player)
    {

        if (stack.getItem() instanceof PickaxeItem && (stack.isCorrectToolForDrops(target) || target.getTags().toList().contains(BlockTags.MINEABLE_WITH_SHOVEL)))
        {
            return true;
        }

        return stack.isCorrectToolForDrops(target);
    }

    public static BlockHitResult rayTrace(final Level level, final Player player, final ClipContext.Fluid mode)
    {
        float pitch = player.getXRot();
        float yaw = player.getYRot();
        Vec3 vec3 = player.getEyePosition(1.0F);
        float cosYaw = Mth.cos(-yaw * 0.017453292F - 3.1415927F);
        float sinYaw = Mth.sin(-yaw * 0.017453292F - 3.1415927F);
        float cosPitch = -Mth.cos(-pitch * 0.017453292F);
        float sinPitch = Mth.sin(-pitch * 0.017453292F);
        float product = sinYaw * cosPitch;
        float product2 = cosYaw * cosPitch;
        double reachDistance = player.getBlockReach();
        Vec3 vec32 = vec3.add((double) product * reachDistance, (double) sinPitch * reachDistance, (double) product2 * reachDistance);
        return level.clip(new ClipContext(vec3, vec32, ClipContext.Block.OUTLINE, mode, player));
    }

    /**
     * Get all affected pos for a player with a tool.
     *
     * @param player the player.
     * @return the list of affected positions.
     */
    public static List<BlockPos> getAffectedPos(@NotNull final Player player)
    {
        final List<BlockPos> list = new ArrayList<>();
        final HitResult rayTrace = rayTrace(player.level(), player, ClipContext.Fluid.NONE);

        if (rayTrace instanceof BlockHitResult)
        {
            final BlockHitResult rayTraceResult = (BlockHitResult) rayTrace;
            final BlockPos center = rayTraceResult.getBlockPos();
            list.add(center);
            switch (rayTraceResult.getDirection())
            {
                case DOWN:
                case UP:
                    list.add(center.west());
                    list.add(center.east());
                    list.add(center.north());
                    list.add(center.south());

                    list.add(center.west().north());
                    list.add(center.west().south());
                    list.add(center.east().north());
                    list.add(center.east().south());
                    break;
                case NORTH:
                case SOUTH:
                    list.add(center.above());
                    list.add(center.below());
                    list.add(center.west());
                    list.add(center.east());

                    list.add(center.west().above());
                    list.add(center.west().below());
                    list.add(center.east().above());
                    list.add(center.east().below());
                    break;
                case EAST:
                case WEST:
                    list.add(center.above());
                    list.add(center.below());
                    list.add(center.north());
                    list.add(center.south());

                    list.add(center.north().above());
                    list.add(center.north().below());
                    list.add(center.south().above());
                    list.add(center.south().below());
                    break;
            }
        }

        return list;
    }
}