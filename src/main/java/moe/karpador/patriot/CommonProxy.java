package moe.karpador.patriot;

import moe.karpador.patriot.network.PatriotPacketHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        ModItems.init();
        PatriotPacketHandler.setWrapper();
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postEvent(FMLPostInitializationEvent event) {

    }

}
