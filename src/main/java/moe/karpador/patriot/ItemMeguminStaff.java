package moe.karpador.patriot;

import moe.karpador.patriot.network.ExplosionMessage;
import moe.karpador.patriot.network.PatriotPacketHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemMeguminStaff extends Item {

    public static final String NAME = "item_megumin_staff";
    private long lastUsageTime;

    public ItemMeguminStaff() {
        setMaxStackSize(1);
        setCreativeTab(Patriot.PATRIOT_TAB);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return String.format("item.%s%s", Patriot.RESOURCE_PREFIX, NAME); //item.patriot:item_megumin_staff
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        long systemTime = System.currentTimeMillis();
        if (systemTime-lastUsageTime>3000) {
            if (worldIn.isRemote) {
                RayTraceResult res = Minecraft.getMinecraft().getRenderViewEntity().rayTrace(50, 1f);
                if (res!=null && res.typeOfHit == RayTraceResult.Type.BLOCK) {
                    BlockPos pos = res.getBlockPos();
                    //playerIn.world.createExplosion(null, pos.getX()+0.5f, pos.getY()+0.5f, pos.getZ()+0.5f, 5f, true);
                    PatriotPacketHandler.wrapper.sendToServer(new ExplosionMessage(pos.getX(), pos.getY(), pos.getZ(), 4));
                } else {
                    Vec3d vec = Minecraft.getMinecraft().getRenderViewEntity().getLookVec().scale(50);
                    BlockPos pos = Minecraft.getMinecraft().getRenderViewEntity().getPosition().add(vec.x, vec.y, vec.z);
                    PatriotPacketHandler.wrapper.sendToServer(new ExplosionMessage(pos.getX(), pos.getY(), pos.getZ(), 4));
                }
            }
            lastUsageTime = systemTime;
        } else {
            if (worldIn.isRemote) {
                playerIn.sendMessage(new TextComponentString(String.format("%s needs to recharge", getRegistryName())));
            }
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
