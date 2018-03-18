package moe.karpador.patriot.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class LightMessage implements IMessage {
    public int x, y, z;
    public int brightness;

    public LightMessage() {}

    public LightMessage(int x, int y, int z, int brightness) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.brightness = brightness;
    }
    @Override
    public void fromBytes(ByteBuf byteBuf) {
        x = byteBuf.readInt();
        y = byteBuf.readInt();
        z = byteBuf.readInt();
        brightness = byteBuf.readInt();
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeInt(x);
        byteBuf.writeInt(y);
        byteBuf.writeInt(z);
        byteBuf.writeInt(brightness);
    }

    public static class LightMessageHandler implements IMessageHandler<LightMessage, LightMessage> {

        @Override
        public LightMessage onMessage(LightMessage lightMessage, MessageContext messageContext) {
            BlockPos pos = new BlockPos(lightMessage.x, lightMessage.y, lightMessage.z);
            if (messageContext.side.isServer()) {
                EntityPlayerMP player = messageContext.getServerHandler().player;
                WorldServer world = player.getServerWorld();
                world.addScheduledTask(() -> {
                    //world.spawnEntity(new EntityLightningBolt(world, pos.getX(), pos.getY(), pos.getZ(), true));

                    /*world.setLightFor(EnumSkyBlock.BLOCK, pos.up(), lightMessage.brightness);
                    world.setLightFor(EnumSkyBlock.BLOCK, pos.down(), lightMessage.brightness);
                    world.setLightFor(EnumSkyBlock.BLOCK, pos.west(), lightMessage.brightness);
                    world.setLightFor(EnumSkyBlock.BLOCK, pos.east(), lightMessage.brightness);
                    world.setLightFor(EnumSkyBlock.BLOCK, pos.north(), lightMessage.brightness);
                    world.setLightFor(EnumSkyBlock.BLOCK, pos.south(), lightMessage.brightness);*/

                });
                PatriotPacketHandler.wrapper.sendToAll(lightMessage);
            }
            else {
                EntityPlayerSP player = Minecraft.getMinecraft().player;
                World world = player.world;
                //world.spawnEntity(new EntityLightningBolt(world, pos.getX(), pos.getY(), pos.getZ(), true));

                /*world.setLightFor(EnumSkyBlock.BLOCK, pos.up(), lightMessage.brightness);
                world.setLightFor(EnumSkyBlock.BLOCK, pos.down(), lightMessage.brightness);
                world.setLightFor(EnumSkyBlock.BLOCK, pos.west(), lightMessage.brightness);
                world.setLightFor(EnumSkyBlock.BLOCK, pos.east(), lightMessage.brightness);
                world.setLightFor(EnumSkyBlock.BLOCK, pos.north(), lightMessage.brightness);
                world.setLightFor(EnumSkyBlock.BLOCK, pos.south(), lightMessage.brightness);*/

            }
            return null;
        }
    }

}
