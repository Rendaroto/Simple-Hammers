package com.rendy.hammers;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;
import org.jetbrains.annotations.NotNull;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber
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
                final boolean notCreativeMode = !event.getPlayer().isCreative();

                if(!event.getPlayer().isShiftKeyDown()) {
                    for (BlockPos pos : getAffectedPos(event.getPlayer())) {
                        final BlockState state = level.getBlockState(pos);
                        if (hardness * 2 >= state.getDestroySpeed(level, pos) && isBestTool(state, level, pos, item, event.getPlayer()) && state.getDestroySpeed(level, pos) >= 0f) {
                            if(notCreativeMode){
                                state.getBlock().playerDestroy(level, event.getPlayer(), pos, state, level.getBlockEntity(pos), mainHand);
                            }
                            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                        }
                    }
                }
                else{

                    final BlockHitResult rayTrace = rayTrace(event.getPlayer().level(), event.getPlayer(), ClipContext.Fluid.NONE);
                    final BlockPos center = rayTrace.getBlockPos();

                    final BlockState state = level.getBlockState(center);

                    if ((hardness * 2 >= state.getDestroySpeed(level, center) && isBestTool(state, level, center, item, event.getPlayer()) && state.getDestroySpeed(level, center) >= 0f) && notCreativeMode) {
                        level.setBlockAndUpdate(center, Blocks.AIR.defaultBlockState());
                        if(notCreativeMode){
                            state.getBlock().playerDestroy(level, event.getPlayer(), center, state, level.getBlockEntity(center), mainHand);
                        }
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

    public static BlockHitResult rayTrace(final Level level, final Player player, final ClipContext.Fluid mode) {
        float pitch = player.getXRot();
        float yaw = player.getYRot();
        // Precalculate trigonometric values
        float yawCos = Mth.cos(-yaw * 0.017453292F - (float) Math.PI);
        float yawSin = Mth.sin(-yaw * 0.017453292F - (float) Math.PI);
        float pitchCos = -Mth.cos(-pitch * 0.017453292F);
        float pitchSin = Mth.sin(-pitch * 0.017453292F);
        // Compute product values
        float product = yawSin * pitchCos;
        float product2 = yawCos * pitchCos;
        double reachDistance = player.getBlockReach();

        Vec3 vec3 = player.getEyePosition(1.0F);
        // Update coordinates of vec3 instead of creating a new Vec3 instance
        vec3 = vec3.add(product * reachDistance, pitchSin * reachDistance, product2 * reachDistance);

        return level.clip(new ClipContext(player.getEyePosition(1.0F), vec3, ClipContext.Block.OUTLINE, mode, player));
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
        final BlockHitResult rayTrace = rayTrace(player.level(), player, ClipContext.Fluid.NONE);

        final BlockPos center = rayTrace.getBlockPos();
        list.add(center);
        switch (rayTrace.getDirection()) {
            case DOWN, UP -> {
                list.add(center.west());
                list.add(center.east());
                list.add(center.north());
                list.add(center.south());
                list.add(center.west().north());
                list.add(center.west().south());
                list.add(center.east().north());
                list.add(center.east().south());
            }
            case NORTH, SOUTH -> {
                list.add(center.above());
                list.add(center.below());
                list.add(center.west());
                list.add(center.east());
                list.add(center.west().above());
                list.add(center.west().below());
                list.add(center.east().above());
                list.add(center.east().below());
            }
            case EAST, WEST -> {
                list.add(center.above());
                list.add(center.below());
                list.add(center.north());
                list.add(center.south());
                list.add(center.north().above());
                list.add(center.north().below());
                list.add(center.south().above());
                list.add(center.south().below());
            }
        }

        return list;
    }
}