package moe.karpador.patriot;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemMeguminStaff extends Item {

    public static final String NAME = "item_meguminstaff";
    private long lastUsageTime;

    public ItemMeguminStaff() {
        setMaxStackSize(1);
        setCreativeTab(Patriot.PATRIOT_TAB);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "item." + Patriot.RESOURCE_PREFIX +NAME; //item.patriot:item_meguminstaff
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        long systemTime = System.currentTimeMillis();
        if (systemTime-lastUsageTime>1000 && !worldIn.isRemote) {
            playerIn.sendMessage(new TextComponentString("Megumin best girl!"));
            lastUsageTime = systemTime;
        }
        if (worldIn.isRemote) {
            RayTraceResult res = Minecraft.getMinecraft().getRenderViewEntity().rayTrace(50, 1f);
            if (res!=null && res.typeOfHit == RayTraceResult.Type.BLOCK) {
                IBlockState blockState = worldIn.getBlockState(res.getBlockPos());
                playerIn.sendMessage(new TextComponentString(blockState.getBlock().getLocalizedName()));
            } else {
                playerIn.sendMessage(new TextComponentString("Raytrace missed"));
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
