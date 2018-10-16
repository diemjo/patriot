package moe.karpador.patriot.items;

import mcp.MethodsReturnNonnullByDefault;
import moe.karpador.patriot.Patriot;
import moe.karpador.patriot.PatriotSoundHandler;
import moe.karpador.patriot.mana.IMana;
import moe.karpador.patriot.mana.ManaProvider;
import moe.karpador.patriot.network.ExplosionMessage;
import moe.karpador.patriot.network.LightMessage;
import moe.karpador.patriot.network.PatriotPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemMeguminStaff extends Item {

    public static final String NAME = "item_megumin_staff";
    private long lastUsageTime;

    public ItemMeguminStaff() {
        setMaxStackSize(1);
        setCreativeTab(Patriot.PATRIOT_TAB);
        setRegistryName(new ResourceLocation(Patriot.MODID, NAME));
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
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        long systemTime = System.currentTimeMillis();
        if (lastUsageTime + 500 > systemTime) {
            return super.onItemRightClick(world, player, hand);
        }
        lastUsageTime = systemTime;

        IMana mana = player.getCapability(ManaProvider.MANA_CAP, null);
        if (mana.enoughMana() || player.isCreative()) {
            castExplosion(world, player, mana);
            if (!player.isCreative() && world.isRemote) {
                mana.setMana(0, true);
            }
        } else {
            if (world.isRemote) {
                //playerIn.sendMessage(new TextComponentTranslation(String.format("%s needs to recharge", playerIn.getHeldItemMainhand().getDisplayName())));
                player.sendMessage(new TextComponentString("You are exhausted from casting magic and need to rest..."));
            }
        }

        return super.onItemRightClick(world, player, hand);
    }

    private void castExplosion(World world, EntityPlayer player, IMana mana) {
        BlockPos blockPos = null;
        int blockBrightness = 0;
        float maxDistance = 50;
        if (world.isRemote) {
            blockPos = getCollisionBlockPos(maxDistance);
            blockBrightness = world.getLightFor(EnumSkyBlock.BLOCK, blockPos);
        }
        /*final Potion potion = Potion.getPotionById(2);
        if (potion!=null)
            potion.applyAttributesModifiersToEntity(player, player.getAttributeMap(), 7);
        */
        mana.setExhausted(true);
        final BlockPos pos = blockPos;
        final int brightness = blockBrightness;
        if (world.isRemote) {
            PatriotPacketHandler.wrapper.sendToServer(new LightMessage(pos.getX(), pos.getY(), pos.getZ(), 15));
        }
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                /*if (potion!=null)
                    potion.removeAttributesModifiersFromEntity(player, player.getAttributeMap(), 7);
                */
                mana.setExhausted(false);
                if  (world.isRemote) {
                    PatriotPacketHandler.wrapper.sendToServer(new LightMessage(pos.getX(), pos.getY(), pos.getZ(), brightness));
                    int strength = getExplosionStrength(player);
                    PatriotPacketHandler.wrapper.sendToServer(new ExplosionMessage(pos.getX(), pos.getY(), pos.getZ(), strength));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        world.playSound(player, player.getPosition(), PatriotSoundHandler.explosion, SoundCategory.PLAYERS, 1, 1);
    }

    @SideOnly(Side.CLIENT)
    @MethodsReturnNonnullByDefault
    private BlockPos getCollisionBlockPos(float maxDistance) {
        BlockPos blockPos;
        Entity view = Minecraft.getMinecraft().getRenderViewEntity();
        RayTraceResult res = view.rayTrace(maxDistance, 1f);
        if (res!=null && res.typeOfHit == RayTraceResult.Type.BLOCK) {
            blockPos = res.getBlockPos();
        } else {
            Vec3d vec = view.getLookVec().scale(50);
            blockPos = view.getPosition().add(vec.x, vec.y, vec.z);
        }
        Entity entity = getEntityHit(view, maxDistance);
        if (entity!=null) {
            BlockPos entityPos = entity.getPosition();
            if (view.getPosition().getDistance(blockPos.getX(), blockPos.getY(), blockPos.getZ()) > view.getPosition().getDistance(entityPos.getX(), entityPos.getY(), entityPos.getZ())) {
                blockPos = entityPos;
            }
        }
        return blockPos;
    }

    @SideOnly(Side.CLIENT)
    private Entity getEntityHit(Entity player, float distance) {
        Entity closestEntity = null;
        Vec3d entityLook = player.getLookVec();
        Vec3d eyeVec = player.getPositionEyes(1f);
        AxisAlignedBB theViewBoundingBox = new AxisAlignedBB(
                player.posX-0.5D,
                player.posY-0.0D,
                player.posZ-0.5D,
                player.posX+0.5D+entityLook.x*distance,
                player.posY+1.5D+entityLook.y*distance,
                player.posZ+0.5D+entityLook.z*distance
        );

        float dist = distance;
        List<Entity> list = player.world.getEntitiesWithinAABBExcludingEntity(player, theViewBoundingBox);
        list.removeIf(e -> !e.canBeCollidedWith());
        for (Entity e : list) {
            float bordersize = player.getCollisionBorderSize();
            AxisAlignedBB aabb = new AxisAlignedBB(
                    e.posX-e.width/2-bordersize,
                    e.posY-bordersize,
                    e.posZ-e.width/2-bordersize,
                    e.posX+e.width/2+bordersize,
                    e.posY+e.height+bordersize,
                    e.posZ+e.width/2+bordersize);
            //aabb.expand(bordersize, bordersize, bordersize);
            RayTraceResult res = aabb.calculateIntercept(eyeVec, eyeVec.add(entityLook.scale(distance)));
            if (res!=null) {
                float d = (float) eyeVec.distanceTo(res.hitVec);
                if (d < dist) {
                    dist = d;
                    closestEntity = e;
                }
            }
        }
        return closestEntity;
    }

    private int getExplosionStrength(EntityPlayer player) {
        int count = 0;
        IMana mana = player.getCapability(ManaProvider.MANA_CAP, null);
        for (ItemStack item : player.getArmorInventoryList()) {
            if (item.getItem() instanceof ItemMeguminCloths) {
                count++;
            }
        }
        if(mana.hasUltimateExplosion()) {
            mana.setUltimateExplosion(false);
            return (1+2*count)*2;
        }
        return 1+2*count;
    }
}
