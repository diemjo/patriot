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
        wrapper.registerMessage(LightMessageHandler.class, LightMessage.class, getID(), Side.SERVER);
        wrapper.registerMessage(ExplosionMessageHandler.class, ExplosionMessage.class, getID(), Side.SERVER);
        Patriot.logger.info("NetworkWrapper registered");
    }
}


