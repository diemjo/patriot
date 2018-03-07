package moe.karpador.patriot;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class ItemMeguminStaff extends Item {

    public static final String NAME = "item_megumin_staff";
    private long lastUsageTime;

    public ItemMeguminStaff() {
        setMaxStackSize(1);
        setCreativeTab(Patriot.PATRIOT_TAB);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "item." + Patriot.RESOURCE_PREFIX +NAME; //item.patriot:item_megumin_staff
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        long systemTime = System.currentTimeMillis();
        if (systemTime-lastUsageTime>1000) {
            if (worldIn.isRemote) {
                RayTraceResult res = Minecraft.getMinecraft().getRenderViewEntity().rayTrace(50, 1f);
                if (res!=null && res.typeOfHit == RayTraceResult.Type.BLOCK) {
                    BlockPos pos = res.getBlockPos();
                    IBlockState blockState = worldIn.getBlockState(pos);
                    //IMessage IMessageHandler SimpleNetworkWrapper
                    //playerIn.world.createExplosion(null, pos.getX()+0.5f, pos.getY()+0.5f, pos.getZ()+0.5f, 5mdf, true);
                    playerIn.sendMessage(new TextComponentString(blockState.getBlock().getLocalizedName()));
                } else {
                    playerIn.sendMessage(new TextComponentString("Raytrace missed"));
                }
            }
            else {
                playerIn.sendMessage(new TextComponentString("Megumin best girl!"));
            }
            lastUsageTime = systemTime;
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
