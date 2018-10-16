package moe.karpador.patriot.network;

import moe.karpador.patriot.Patriot;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PatriotPacketHandler {
    public static SimpleNetworkWrapper wrapper;
    private static int ID = 0;
    private static int getID() {
        return ID++;
    }

    public static void setWrapper() {
        wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(Patriot.MODID);
        wrapper.registerMessage(LightMessage.LightMessageHandler.class, LightMessage.class, getID(), Side.SERVER);
        wrapper.registerMessage(LightMessage.LightMessageHandler.class, LightMessage.class, getID(), Side.CLIENT);
        wrapper.registerMessage(ExplosionMessage.ExplosionMessageHandler.class, ExplosionMessage.class, getID(), Side.SERVER);
        wrapper.registerMessage(ExplosionMessage.ExplosionMessageHandler.class, ExplosionMessage.class, getID(), Side.CLIENT);
        wrapper.registerMessage(PantsuMessage.PantsuMessageHandler.class, PantsuMessage.class, getID(), Side.SERVER);
        wrapper.registerMessage(PantsuMessage.PantsuMessageHandler.class, PantsuMessage.class, getID(), Side.CLIENT);
        wrapper.registerMessage(RestoreManaMessage.RestoreManaMessageHandler.class, RestoreManaMessage.class, getID(), Side.SERVER);
        wrapper.registerMessage(RestoreManaMessage.RestoreManaMessageHandler.class, RestoreManaMessage.class, getID(), Side.CLIENT);
        Patriot.logger.info("NetworkWrapper registered");
    }
}


