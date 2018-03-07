package moe.karpador.patriot.network;

import moe.karpador.patriot.Patriot;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PatriotMessageHandler implements IMessageHandler<ExplosionMessage, IMessage> {
    @Override
    public IMessage onMessage(ExplosionMessage message, MessageContext messageContext) {
        Patriot.logger.info("Message received");
        WorldServer server = messageContext.getServerHandler().player.getServerWorld();
        server.addScheduledTask(() -> server.createExplosion(null, message.x, message.y, message.z, message.s, true));
        return null;
    }
}
