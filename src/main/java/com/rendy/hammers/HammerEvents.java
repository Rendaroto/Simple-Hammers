package com.rendy.hammers;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
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
                    for (BlockPos pos : getAffectedPos(event.getPlayer(), 1)) { //I get all the block to break
                        final BlockState state = level.getBlockState(pos);
                        /*
                        *
                        * I check that
                        * I can break the block, and it is not too hard compared to the one I actually broke
                        * I check that the block is breakable
                        * I check if I can actually break it with my usage
                        *
                         */
                        if (hardness * 2 >= state.getDestroySpeed(level, pos) && isBestTool(state, item) && state.getDestroySpeed(level, pos) >= 0f && item.getItem().getDamage(item)+i < item.getMaxDamage()) {
                            if(notCreativeMode){
                                state.getBlock().playerDestroy(level, event.getPlayer(), pos, state, level.getBlockEntity(pos), mainHand); //set the action to the block
                                state.getBlock().getExpDrop(state,level,pos,null, event.getPlayer(), mainHand);
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

    //Also Google, but I know that I know, I don't know, but I'm sure that I know that I check if the block is right for drop
    private static boolean isBestTool(final BlockState target,final ItemStack stack)
    {
        if (stack.getItem() instanceof HammerItem && (stack.isCorrectToolForDrops(target) || target.getTags().toList().contains(BlockTags.MINEABLE_WITH_SHOVEL)))
            return true;

        return stack.isCorrectToolForDrops(target);
    }

    //I calculate what I'm seeing to get the blocks around, I guess I copied from Google xD
    public static BlockHitResult rayTrace(final Level level, final Player player) {
        return level.clip(
                new ClipContext(player.getEyePosition(1f),
                (player.getEyePosition(1f).add(player.getViewVector(1f).scale(6f))),
                ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
    }

    /**
     * Get all affected pos for a player with a tool.
     *
     * @param player the player.
     * @return the list of affected positions.
     */
    public static List<BlockPos> getAffectedPos(@NotNull final Player player, int range)
    {
        final List<BlockPos> list = new ArrayList<>();
        final BlockHitResult rayTrace = rayTrace(player.level(), player);

        final BlockPos center = rayTrace.getBlockPos();
        switch (rayTrace.getDirection()) {
            case DOWN, UP -> {
                for (int x = -range; x <= range; x++) {
                    for (int y = -range; y <= range; y++) {
                        list.add(new BlockPos(center.getX() + x, center.getY(), center.getZ() + y));
                    }
                }
            }
            case NORTH, SOUTH -> {
                for (int x = -range; x <= range; x++) {
                    for (int y = -range; y <= range; y++) {
                        list.add(new BlockPos(center.getX() + x, center.getY() + y, center.getZ()));
                    }
                }
            }
            case EAST, WEST -> {
                for (int x = -range; x <= range; x++) {
                    for (int y = -range; y <= range; y++) {
                        list.add(new BlockPos(center.getX(), center.getY() + y, center.getZ() + x));
                    }
                }
            }
        }
        return list;
    }
}