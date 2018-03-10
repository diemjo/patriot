package moe.karpador.patriot;

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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.util.List;

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
            float maxDistance = 50;
            if (worldIn.isRemote) {
                Entity view = Minecraft.getMinecraft().getRenderViewEntity();
                RayTraceResult res = view.rayTrace(maxDistance, 1f);
                if (res!=null && res.typeOfHit == RayTraceResult.Type.BLOCK) {
                    blockPos = res.getBlockPos();
                    //playerIn.world.createExplosion(null, pos.getX()+0.5f, pos.getY()+0.5f, pos.getZ()+0.5f, 5f, true);
                    //PatriotPacketHandler.wrapper.sendToServer(new ExplosionMessage(pos.getX(), pos.getY(), pos.getZ(), 4));
                } else {
                    Vec3d vec = Minecraft.getMinecraft().getRenderViewEntity().getLookVec().scale(50);
                    blockPos = Minecraft.getMinecraft().getRenderViewEntity().getPosition().add(vec.x, vec.y, vec.z);
                }
                Entity entity = getEntityHit(view, maxDistance);
                if (entity!=null) {
                    //playerIn.sendMessage(new TextComponentString(entity.getName()));
                    BlockPos entityPos = entity.getPosition();
                    if (view.getPosition().getDistance(blockPos.getX(), blockPos.getY(), blockPos.getZ()) > view.getPosition().getDistance(entityPos.getX(), entityPos.getY(), entityPos.getZ())) {
                        blockPos = entityPos;
                    }
                }
            }
            lastUsageTime = systemTime;
            Potion potion = Potion.getPotionById(2);
            if (potion!=null)
                potion.applyAttributesModifiersToEntity(playerIn, playerIn.getAttributeMap(), 7);
            final BlockPos pos = blockPos;
            if (worldIn.isRemote) {
                PatriotPacketHandler.wrapper.sendToServer(new LightMessage(pos.getX(), pos.getY(), pos.getZ(), true));
            }
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    if (potion!=null)
                    potion.removeAttributesModifiersFromEntity(playerIn, playerIn.getAttributeMap(), 7);
                    if  (worldIn.isRemote) {
                        PatriotPacketHandler.wrapper.sendToServer(new ExplosionMessage(pos.getX(), pos.getY(), pos.getZ(), 4));
                        PatriotPacketHandler.wrapper.sendToServer(new LightMessage(pos.getX(), pos.getY(), pos.getZ(), false));
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
            //entity.sendMessage(new TextComponentString(e.getName()));
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
            //entity.sendMessage(new TextComponentString("raytracing from "+eyeVec+" with look "+entityLook.scale(distance)+" to "+e.getPositionVector()));
            if (res!=null) {
                //player.sendMessage(new TextComponentString(res.toString()));
                float d = (float) eyeVec.distanceTo(res.hitVec);
                if (d < dist) {
                    dist = d;
                    closestEntity = e;
                }
            }
        }
        return closestEntity;
    }
}
