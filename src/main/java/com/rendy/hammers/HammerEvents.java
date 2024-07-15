package com.rendy.hammers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Hammers.MOD_ID)
public class HammerEvents {

    @SubscribeEvent
    public static void onBlockBreak(final BlockEvent.BreakEvent event) {
        IBlockState state = event.getState();
        if (state.getBlock().isToolEffective("pickaxe", state) || state.getBlock().isToolEffective("shovel", state) ) {
            EntityPlayer player = event.getPlayer();
            ItemStack item = player.getHeldItemMainhand();

            if (item.getItem() instanceof HammerItem && item.getItemDamage() + 1 < item.getMaxDamage()) {
                World world = player.world;
                float hardness = state.getBlockHardness(world, event.getPos());
                boolean notCreativeMode = !player.capabilities.isCreativeMode;

                if (!player.isSneaking()) {
                    for (BlockPos pos : getAffectedPos(player)) {
                        IBlockState targetState = world.getBlockState(pos);

                        if (hardness * 2 >= targetState.getBlockHardness(world, pos)
                                && isBestTool(targetState, world, pos, item)
                                && targetState.getBlockHardness(world, pos) >= 0f
                                && item.getItemDamage() + 1 < item.getMaxDamage()) {
                            if (notCreativeMode) {
                                targetState.getBlock().harvestBlock(world, player, pos, targetState, world.getTileEntity(pos), item);
                            }
                            world.setBlockToAir(pos);
                        }
                    }
                }
            }
        }
    }

    private static boolean isBestTool(IBlockState target, World world, BlockPos pos, ItemStack stack) {
        return stack.getItem() instanceof HammerItem
                && (target.getBlock().isToolEffective("pickaxe", target) || target.getBlock().isToolEffective("shovel", target));
    }

    public static RayTraceResult rayTrace(World world, EntityPlayer player) {
        float pitch = player.rotationPitch;
        float yaw = player.rotationYaw;

        // Calculate the direction vector based on yaw and pitch
        float pitchCos = MathHelper.cos(-pitch * ((float)Math.PI / 180F));
        float pitchSin = MathHelper.sin(-pitch * ((float)Math.PI / 180F));
        float yawCos = MathHelper.cos(-yaw * ((float)Math.PI / 180F));
        float yawSin = MathHelper.sin(-yaw * ((float)Math.PI / 180F));

        Vec3d direction = new Vec3d((double)(yawSin * pitchCos), (double)pitchSin, (double)(yawCos * pitchCos));
        Vec3d startVec = player.getPositionEyes(1.0F);
        Vec3d endVec = startVec.add(direction.scale(4.5));  // 4.5 is the distance of the ray trace

        // Perform the ray trace
        return world.rayTraceBlocks(startVec, endVec, false, true, false);
    }

    public static List<BlockPos> getAffectedPos(EntityPlayer player) {
        List<BlockPos> list = new ArrayList<>();
        RayTraceResult rayTrace = rayTrace(player.world, player);

        if (rayTrace != null && rayTrace.typeOfHit == RayTraceResult.Type.BLOCK) {
            BlockPos center = rayTrace.getBlockPos();
            switch (rayTrace.sideHit) {
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
                    list.add(center.up());
                    list.add(center.down());
                    list.add(center.west());
                    list.add(center.east());
                    list.add(center.west().up());
                    list.add(center.west().down());
                    list.add(center.east().up());
                    list.add(center.east().down());
                    break;
                case EAST:
                case WEST:
                    list.add(center.up());
                    list.add(center.down());
                    list.add(center.north());
                    list.add(center.south());
                    list.add(center.north().up());
                    list.add(center.north().down());
                    list.add(center.south().up());
                    list.add(center.south().down());
                    break;
            }
        }

        return list;
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll(Hammer.HAMMERS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onModelRegister(RegistryEvent.Register<Item> event){
        for(Item item : Hammer.HAMMERS){
            if(item instanceof IHasModel){
               ((IHasModel)item).registerModels();
            }
        }
    }
}
