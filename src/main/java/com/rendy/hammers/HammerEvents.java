package com.rendy.hammers;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber
public class HammerEvents {

    @SubscribeEvent
    public static void onBlockBreak(@NotNull final BlockEvent.BreakEvent event)
    {
        //IDK
        if (event.getState().canOcclude())
        {
            //I check if the item in my hand is a Hammer
            final ItemStack item = event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND);
            int i = 0; //Iterator

            //I check if it's a hammer and I have at least 1 usage
            if (item.getItem() instanceof HammerItem && item.getItem().getDamage(item)+1 < item.getMaxDamage())
            {
                EquipmentSlot equipmentSlot = item.getEquipmentSlot(); assert equipmentSlot != null; //IntelliJ wants this
                final ItemStack mainHand = event.getPlayer().getMainHandItem();

                //I get the block
                final Level level = event.getPlayer().getCommandSenderWorld();
                final double hardness = event.getState().getDestroySpeed(level, event.getPos());

                // just to get that Vanilla touch when breaking in creative
                final boolean notCreativeMode = !event.getPlayer().isCreative();

                if(!event.getPlayer().isShiftKeyDown()) {
                    for (BlockPos pos : getAffectedPos(event.getPlayer())) { //I get all the block to break
                        final BlockState state = level.getBlockState(pos);
                        /*
                        *
                        * I check that
                        * I can break the block, and it is not too hard compared to the one I actually broke
                        * I check that the block is breakable
                        * I check if I can actually break it with my usage
                        *
                         */
                        if (hardness * 2 >= state.getDestroySpeed(level, pos) && isBestTool(state, level, pos, item, event.getPlayer()) && state.getDestroySpeed(level, pos) >= 0f && item.getItem().getDamage(item)+1 < item.getMaxDamage()) {
                            if(notCreativeMode){
                                state.getBlock().playerDestroy(level, event.getPlayer(), pos, state, level.getBlockEntity(pos), mainHand); //set the action to the block
                                i+=1; //This makes sense later
                            }
                            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState()); //I destroy it
                        }
                    }
                    item.hurtAndBreak(i, event.getPlayer(), equipmentSlot); //remove the usage
                    item.hurtAndBreak(-1,event.getPlayer(), equipmentSlot); //even if it's zero the vanilla shrink later should do the trick
                }
            }
        }
    }

    //Also google, but I know that I know, what, I don't know but i'm sure that I know that I check if the block is right for drop
    private static boolean isBestTool(final BlockState target, final LevelAccessor level, final BlockPos pos, final ItemStack stack, final Player player)
    {
        return stack.isCorrectToolForDrops(target);
    }

    //I calculate what I'm seeing to get the blocks around, I guess I copied from google xD
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

        Vec3 vec3 = player.getEyePosition(1.0F);
        // Update coordinates of vec3 instead of creating a new Vec3 instance
        vec3 = vec3.add(product * 4.5, pitchSin * 4.5, product2 * 4.5);

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