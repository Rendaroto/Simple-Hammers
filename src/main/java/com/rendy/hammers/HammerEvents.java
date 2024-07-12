package com.rendy.hammers;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Hammers.MOD_ID)
public class HammerEvents {

    @SubscribeEvent
    public static void onBlockBreak(final BlockEvent.BreakEvent event) {
        if (event.getState().canOcclude()) {
            PlayerEntity player = event.getPlayer();
            ItemStack item = player.getItemInHand(Hand.MAIN_HAND);

            if (item.getItem() instanceof HammerItem && item.getDamageValue() + 1 < item.getMaxDamage()) {
                World world = player.level;
                double hardness = event.getState().getDestroySpeed(world, event.getPos());
                boolean notCreativeMode = !player.isCreative();

                if (!player.isShiftKeyDown()) {
                    for (BlockPos pos : getAffectedPos(player)) {
                        BlockState state = world.getBlockState(pos);

                        if (hardness * 2 >= state.getDestroySpeed(world, pos)
                                && isBestTool(state, world, pos, item, player)
                                && state.getDestroySpeed(world, pos) >= 0f
                                && item.getDamageValue() + 1 < item.getMaxDamage()) {
                            if (notCreativeMode) {
                                state.getBlock().destroy(world, pos, state);
                            }
                            world.setBlock(pos, Blocks.AIR.getBlock().defaultBlockState(), 3);
                        }
                    }
                }
            }
        }
    }

    private static boolean isBestTool(BlockState target, World world, BlockPos pos, ItemStack stack, PlayerEntity player) {
        return stack.getItem() instanceof HammerItem  && (stack.getToolTypes().contains(ToolType.PICKAXE) || target.getHarvestTool() == ToolType.SHOVEL);
    }

    public static BlockRayTraceResult rayTrace(World world, PlayerEntity player, RayTraceContext.FluidMode mode) {
        float pitch = player.xRot;
        float yaw = player.yRot;

        // Calculate the direction vector based on yaw and pitch
        float pitchCos = MathHelper.cos(-pitch * ((float)Math.PI / 180F));
        float pitchSin = MathHelper.sin(-pitch * ((float)Math.PI / 180F));
        float yawCos = MathHelper.cos(-yaw * ((float)Math.PI / 180F));
        float yawSin = MathHelper.sin(-yaw * ((float)Math.PI / 180F));

        Vector3d direction = new Vector3d((double)(yawSin * pitchCos), (double)pitchSin, (double)(yawCos * pitchCos));
        Vector3d startVec = player.getEyePosition(1.0F);
        Vector3d endVec = startVec.add(direction.scale(4.5));  // 4.5 is the distance of the ray trace

        // Perform the ray trace
        RayTraceContext context = new RayTraceContext(startVec, endVec, RayTraceContext.BlockMode.OUTLINE, mode, player);
        return world.clip(context);
    }

    public static List<BlockPos> getAffectedPos(PlayerEntity player) {
        List<BlockPos> list = new ArrayList<>();
        BlockRayTraceResult rayTrace = rayTrace(player.level, player, net.minecraft.util.math.RayTraceContext.FluidMode.NONE);

        BlockPos center = rayTrace.getBlockPos();
        switch (rayTrace.getDirection()) {
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

        return list;
    }
}
