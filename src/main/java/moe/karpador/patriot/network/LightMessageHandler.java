package moe.karpador.patriot.network;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class LightMessageHandler implements IMessageHandler<LightMessage, LightMessage> {

    @Override
    public LightMessage onMessage(LightMessage lightMessage, MessageContext messageContext) {
        EntityPlayerMP player = messageContext.getServerHandler().player;
        WorldServer server = player.getServerWorld();
        BlockPos pos = new BlockPos(lightMessage.x, lightMessage.y, lightMessage.z);
        server.addScheduledTask(() -> {
            player.sendMessage(new TextComponentString("Light value of "+server.getBlockState(pos).getBlock().getLocalizedName()+" before is "+server.getLight(pos)));
            server.setLightFor(EnumSkyBlock.BLOCK, pos, lightMessage.brightness);
            server.notifyBlockUpdate(pos, server.getBlockState(pos), server.getBlockState(pos), 3);
            //server.markBlockRangeForRenderUpdate(lightMessage.x, lightMessage.y, lightMessage.z, 12, 12, 12);
            //server.notifyLightSet(pos);
            //state.getBlock().setLightLevel(15);
            //player.sendMessage(new TextComponentString("Light value of "+server.getBlockState(pos).getBlock().getLocalizedName()+" after is "+server.getLight(pos)));
            //server.setBlockState(pos, server.getBlockState(pos), 3);
        });
        return null;
    }
}
