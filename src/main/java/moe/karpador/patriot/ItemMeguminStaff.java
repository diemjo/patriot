package moe.karpador.patriot;

import com.sun.istack.internal.NotNull;
import mcp.MethodsReturnNonnullByDefault;
import moe.karpador.patriot.network.ExplosionMessage;
import moe.karpador.patriot.network.PatriotPacketHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.LanguageMap;
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
    public String getUnlocalizedName() {
        return String.format("item.%s%s", Patriot.RESOURCE_PREFIX, NAME); //item.patriot:item_megumin_staff
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        long systemTime = System.currentTimeMillis();
        if (systemTime-lastUsageTime>3000) {
            BlockPos blockPos = null;
            if (worldIn.isRemote) {
                RayTraceResult res = Minecraft.getMinecraft().getRenderViewEntity().rayTrace(50, 1f);
                if (res!=null && res.typeOfHit == RayTraceResult.Type.BLOCK) {
                    blockPos = res.getBlockPos();
                    //playerIn.world.createExplosion(null, pos.getX()+0.5f, pos.getY()+0.5f, pos.getZ()+0.5f, 5f, true);
                    //PatriotPacketHandler.wrapper.sendToServer(new ExplosionMessage(pos.getX(), pos.getY(), pos.getZ(), 4));
                } else {
                    Vec3d vec = Minecraft.getMinecraft().getRenderViewEntity().getLookVec().scale(50);
                    blockPos = Minecraft.getMinecraft().getRenderViewEntity().getPosition().add(vec.x, vec.y, vec.z);
                }
            }
            lastUsageTime = systemTime;
            Potion potion = Potion.getPotionById(2);
            potion.applyAttributesModifiersToEntity(playerIn, playerIn.getAttributeMap(), 7);
            final BlockPos pos = blockPos;
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    potion.removeAttributesModifiersFromEntity(playerIn, playerIn.getAttributeMap(), 7);
                    if  (worldIn.isRemote) {
                        PatriotPacketHandler.wrapper.sendToServer(new ExplosionMessage(pos.getX(), pos.getY(), pos.getZ(), 4));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            worldIn.playSound(playerIn, playerIn.getPosition(), PatriotSoundHandler.explosion, SoundCategory.MUSIC, 1, 1);
        } else {
            if (worldIn.isRemote) {
                //playerIn.sendMessage(new TextComponentTranslation(String.format("%s needs to recharge", playerIn.getHeldItemMainhand().getDisplayName())));
                playerIn.sendMessage(new TextComponentString("You are exhausted from casting magic and need to rest..."));
            }
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
