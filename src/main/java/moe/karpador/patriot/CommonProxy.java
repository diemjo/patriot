package moe.karpador.patriot;

import moe.karpador.patriot.capability.CapabilityHandler;
import moe.karpador.patriot.capability.IMana;
import moe.karpador.patriot.capability.Mana;
import moe.karpador.patriot.capability.ManaStorage;
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
        MinecraftForge.EVENT_BUS.register(CapabilityHandler.class);

    }

    public void init(FMLInitializationEvent event) {
        CapabilityManager.INSTANCE.register(IMana.class, new ManaStorage(), Mana::new);

    }

    public void postEvent(FMLPostInitializationEvent event) {

    }

}
