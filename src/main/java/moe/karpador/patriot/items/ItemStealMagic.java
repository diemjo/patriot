package moe.karpador.patriot.items;

import moe.karpador.patriot.Patriot;
import moe.karpador.patriot.PatriotSoundHandler;
import moe.karpador.patriot.mana.IMana;
import moe.karpador.patriot.mana.ManaProvider;
import moe.karpador.patriot.network.PantsuMessage;
import moe.karpador.patriot.network.PatriotPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemStealMagic extends Item {
    public static final String NAME = "item_steal_magic";
    private long lastUsageTime = 0;

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
        long systemTime = System.currentTimeMillis();
        if (systemTime - lastUsageTime < 200) {
            return super.onItemRightClick(world, player, hand);
        }
        IMana playerMana = player.getCapability(ManaProvider.MANA_CAP, null);
        if (player.isSneaking()) {
            if (!world.isRemote) {
                String pantsu = (playerMana!=null && playerMana.hasPantsu()) ? "" : "not ";
                player.sendMessage(new TextComponentString("You are currently "+pantsu+"wearing pantsu"));
            }
            return super.onItemRightClick(world, player, hand);
        }
        if (playerMana==null || (playerMana.getMana() < playerMana.getMaxMana()/3 && !player.isCreative())) {
            if (!world.isRemote)
                player.sendMessage(new TextComponentString("You are exhausted from casting magic and need to rest..."));
            return super.onItemRightClick(world, player, hand);
        }
        if (world.isRemote) {
            RayTraceResult res = Minecraft.getMinecraft().objectMouseOver;
            if (res != null && res.typeOfHit == RayTraceResult.Type.ENTITY) {
                if (res.entityHit instanceof EntityPlayer) {
                    IMana targetMana = res.entityHit.getCapability(ManaProvider.MANA_CAP, null);
                    if (!targetMana.hasPantsu()) {
                        player.sendMessage(new TextComponentString(res.entityHit.getName()+" is not wearing pantsu right now"));
                        return super.onItemRightClick(world, player, hand);
                    }
                    if (getNumberOfMeguminClothes((EntityPlayer) res.entityHit) == 4) {
                        PatriotPacketHandler.wrapper.sendToServer(new PantsuMessage(true, (EntityPlayer) res.entityHit));
                    }
                    else {
                        PatriotPacketHandler.wrapper.sendToServer(new PantsuMessage(false, (EntityPlayer) res.entityHit));
                    }
                    if (!player.isCreative())
                        playerMana.setMana(playerMana.getMana()-playerMana.getMaxMana()/3, true);
                    targetMana.setPantsu(false);
                    world.playSound(player, res.entityHit.getPosition(), PatriotSoundHandler.kyaa, SoundCategory.PLAYERS, 1, 1);
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
