package moe.karpador.patriot.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.collection.parallel.ParIterableLike;

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
        public LightMessage onMessage(LightMessage lightMessage, MessageContext context) {

            if (context.side.isServer()) {
                handleMessageOnServer(lightMessage, context);
            }
            else {
                handleMessageOnClient(lightMessage, context);
            }
            return null;
        }

        @SideOnly(Side.SERVER)
        private void handleMessageOnServer(LightMessage message, MessageContext context) {
            EntityPlayerMP player = context.getServerHandler().player;
            WorldServer world = player.getServerWorld();
            world.addScheduledTask(() -> {
                BlockPos pos = new BlockPos(message.x, message.y, message.z);
                //world.setLightFor(EnumSkyBlock.BLOCK, pos, lightMessage.brightness);
                /*world.setLightFor(EnumSkyBlock.BLOCK, pos.up(), lightMessage.brightness);
                world.setLightFor(EnumSkyBlock.BLOCK, pos.down(), lightMessage.brightness);
                world.setLightFor(EnumSkyBlock.BLOCK, pos.west(), lightMessage.brightness);
                world.setLightFor(EnumSkyBlock.BLOCK, pos.east(), lightMessage.brightness);
                world.setLightFor(EnumSkyBlock.BLOCK, pos.north(), lightMessage.brightness);
                world.setLightFor(EnumSkyBlock.BLOCK, pos.south(), lightMessage.brightness);*/

            });
            PatriotPacketHandler.wrapper.sendToAll(message);
        }

        @SideOnly(Side.CLIENT)
        private void handleMessageOnClient(LightMessage message, MessageContext context) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                BlockPos pos = new BlockPos(message.x, message.y, message.z);
                EntityPlayerSP player = Minecraft.getMinecraft().player;
                World world = player.world;
                //world.setLightFor(EnumSkyBlock.BLOCK, pos, message.brightness);
                world.spawnEntity(new EntityLightningBolt(world, pos.getX(), pos.getY(), pos.getZ(), true));
                //IBlockState state = world.getBlockState(pos.up());
                //world.setBlockState(pos.up(), Blocks.STONE.getDefaultState(), 3);
                //world.setBlockState(pos.up(), state, 3);


                /*world.setLightFor(EnumSkyBlock.BLOCK, pos.up(), lightMessage.brightness);
                world.setLightFor(EnumSkyBlock.BLOCK, pos.down(), lightMessage.brightness);
                world.setLightFor(EnumSkyBlock.BLOCK, pos.west(), lightMessage.brightness);
                world.setLightFor(EnumSkyBlock.BLOCK, pos.east(), lightMessage.brightness);
                world.setLightFor(EnumSkyBlock.BLOCK, pos.north(), lightMessage.brightness);
                world.setLightFor(EnumSkyBlock.BLOCK, pos.south(), lightMessage.brightness);*/
            });
        }
    }

}
