package moe.karpador.patriot.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class LightMessageHandler implements IMessageHandler<LightMessage, IMessage> {

    @Override
    public IMessage onMessage(LightMessage lightMessage, MessageContext messageContext) {
        EntityPlayerMP player = messageContext.getServerHandler().player;
        WorldServer server = player.getServerWorld();
        server.addScheduledTask(() -> {
            server.setLightFor(EnumSkyBlock.BLOCK, new BlockPos(lightMessage.x, lightMessage.y, lightMessage.z), lightMessage.on ? 16 : 0);
            server.markBlockRangeForRenderUpdate(lightMessage.x, lightMessage.y, lightMessage.z, 12, 12, 12);

        });
        return null;
    }
}
