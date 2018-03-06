package moe.karpador.patriot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemPatriot extends Item {

    public static String NAME = "item_patriot";
    private long lastUsageTime = 0;

    public ItemPatriot() {
        setMaxStackSize(1);
        //setCreativeTab(Patriot.PATRIOT_TAB);
        setCreativeTab(Patriot.PATRIOT_TAB);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "item." + Patriot.RESOURCE_PREFIX +NAME; //item.patriot:item_patriot
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        long systemTime = System.currentTimeMillis();
        if (systemTime-lastUsageTime>200 && !worldIn.isRemote) {
            playerIn.sendMessage(new TextComponentString("Patriot best girl!"));
            lastUsageTime = systemTime;
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
