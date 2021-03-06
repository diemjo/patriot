package moe.karpador.patriot.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ExplosionMessage implements IMessage {

    public float x, y, z, s;

    public ExplosionMessage() {}

    public ExplosionMessage(float x, float y, float z, float s) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.s = s;
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        x = byteBuf.readFloat();
        y = byteBuf.readFloat();
        z = byteBuf.readFloat();
        s = byteBuf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeFloat(x);
        byteBuf.writeFloat(y);
        byteBuf.writeFloat(z);
        byteBuf.writeFloat(s);
    }

    public static class ExplosionMessageHandler implements IMessageHandler<ExplosionMessage, ExplosionMessage> {
        @Override
        public ExplosionMessage onMessage(ExplosionMessage message, MessageContext context) {

            if (context.side.isServer()) {
                handleMessageOnServer(message, context);
            } else {
                handleMessageOnClient(message, context);
            }
            return null;
        }

        private void handleMessageOnServer(ExplosionMessage message, MessageContext context) {
            WorldServer world = context.getServerHandler().player.getServerWorld();
            BlockPos pos = new BlockPos(message.x, message.y, message.z);
            world.addScheduledTask(() -> {
                world.createExplosion(null, message.x, message.y, message.z, message.s, true);
                doDamage(world, message.x, message.y, message.z, message.s);
                world.spawnEntity(new EntityLightningBolt(world, pos.getX(), pos.getY(), pos.getZ(), true));
            });
            PatriotPacketHandler.wrapper.sendToAll(message);
        }

        @SideOnly(Side.CLIENT)
        private void handleMessageOnClient(ExplosionMessage message, MessageContext context) {
            BlockPos pos = new BlockPos(message.x, message.y, message.z);
            Minecraft.getMinecraft().addScheduledTask(() -> {
                World world = Minecraft.getMinecraft().world;
                doDamage(world, message.x, message.y, message.z, message.s);
                world.spawnEntity(new EntityLightningBolt(world, pos.getX(), pos.getY(), pos.getZ(), true));
            });
        }

        private void doDamage(World world, float x, float y, float z, float s) {
            AxisAlignedBB aabb = new AxisAlignedBB(
                    x-s-1,
                    y-s-1,
                    z-s-1,
                    x+s+1,
                    y+s+1,
                    z+s+3
            );
            List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, aabb);
            for (EntityLivingBase e : entities) {
                e.attackEntityFrom(DamageSource.MAGIC, 20);
            }
        }

    }
}