package moe.karpador.patriot;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Patriot.MODID, name = Patriot.NAME, version = Patriot.VERSION)
@Mod.EventBusSubscriber
public class Patriot {
    public static final String MODID = "patriot";
    public static final String NAME = "Patriot";
    public static final String RESOURCE_PREFIX = String.format("%s:", Patriot.MODID);
    public static final String VERSION = "1.0";

    public static Logger logger;

    @SidedProxy(clientSide = "moe.karpador.patriot.ClientProxy", serverSide = "moe.karpador.patriot.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        logger = event.getModLog();
        proxy.preInit(event);
        logger.info("##### I GET TO THIS CODE IN PREINIT");
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
        // some example code
        // logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        proxy.init(event);
        logger.info("##### I GET TO THIS CODE IN INIT");
        
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postEvent(event);
    }

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        Patriot.logger.info("##### I GOT TO THE REGISTER EVENT");
        ModItems.registerItems(event);
    }

    public static final CreativeTabs PATRIOT_TAB = new CreativeTabs(Patriot.MODID) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.itemPatriot);
        }
    };
}
