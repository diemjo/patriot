package moe.karpador.patriot.proxy;

import moe.karpador.patriot.items.ModItems;
import moe.karpador.patriot.PatriotSoundHandler;
import moe.karpador.patriot.mana.*;
import moe.karpador.patriot.network.PatriotPacketHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        ModItems.init();
        PatriotSoundHandler.init();
        PatriotPacketHandler.setWrapper();
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
        MinecraftForge.EVENT_BUS.register(new PantsuStackCapabilityHandler());
        CapabilityManager.INSTANCE.register(IMana.class, new ManaStorage(), Mana::new);
        CapabilityManager.INSTANCE.register(IPantsuStack.class, new PantsuStackStorage(), PantsuStack::new);
    }

    public void init(FMLInitializationEvent event) {


    }

    public void postInit(FMLPostInitializationEvent event) {

    }

}
