package moe.karpador.patriot.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

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
        public ExplosionMessage onMessage(ExplosionMessage message, MessageContext messageContext) {
            BlockPos pos = new BlockPos(message.x, message.y, message.z);

            if (messageContext.side.isServer()) {
                WorldServer world = messageContext.getServerHandler().player.getServerWorld();

                world.addScheduledTask(() -> {
                    world.createExplosion(null, message.x, message.y, message.z, message.s, true);
                    world.spawnEntity(new EntityLightningBolt(world, pos.getX(), pos.getY(), pos.getZ(), true));
                });
                PatriotPacketHandler.wrapper.sendToAll(message);
            } else {
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    World world = Minecraft.getMinecraft().world;
                    world.spawnEntity(new EntityLightningBolt(world, pos.getX(), pos.getY(), pos.getZ(), true));
                });
            }
            return null;
        }
    }
}