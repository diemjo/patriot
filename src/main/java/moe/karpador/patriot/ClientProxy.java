package moe.karpador.patriot;

import moe.karpador.patriot.capability.ManaBar;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        MinecraftForge.EVENT_BUS.register(new ManaBar(Minecraft.getMinecraft()));
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        ModItems.initClient(Minecraft.getMinecraft().getRenderItem().getItemModelMesher());
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

}
