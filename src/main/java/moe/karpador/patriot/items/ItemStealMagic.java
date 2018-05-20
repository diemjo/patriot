package moe.karpador.patriot.items;

import moe.karpador.patriot.Patriot;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemStealMagic extends Item {
    public static final String NAME = "item_steal_magic";
    public ItemStealMagic() {
        setMaxStackSize(1);
        setCreativeTab(Patriot.PATRIOT_TAB);
        setRegistryName(new ResourceLocation(Patriot.MODID, NAME));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return String.format("item.%s%s", Patriot.RESOURCE_PREFIX, NAME); //item.patriot:item_steal_magic
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("item.%s%s", Patriot.RESOURCE_PREFIX, NAME); //item.patriot:item_steal_magic
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (world.isRemote) {
            RayTraceResult res = Minecraft.getMinecraft().objectMouseOver;
            if (res != null && res.typeOfHit == RayTraceResult.Type.ENTITY) {
                //player.sendMessage(new TextComponentString("Entity: "+res.entityHit));
                if (res.entityHit instanceof EntityPlayer) {
                    if (getNumberOfMeguminClothes((EntityPlayer) res.entityHit) == 4) {
                        player.inventory.addItemStackToInventory(new ItemStack(ModItems.itemMeguminPantsu));
                    }
                    else {
                        player.inventory.addItemStackToInventory(new ItemStack(ModItems.itemGenericPantsu));
                    }
                }
                else {
                    player.sendMessage(new TextComponentString("you don't want to eat "+res.entityHit.getName()+" pantsu, do you?"));
                }
            }
        }
        return super.onItemRightClick(world, player, hand);
    }

    public int getNumberOfMeguminClothes(EntityPlayer player) {
        int count = 0;
        for (ItemStack item : player.getArmorInventoryList()) {
            if (item.getItem() instanceof ItemMeguminCloths) {
                count++;
            }
        }
        return count;
    }
}
