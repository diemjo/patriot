package moe.karpador.patriot;

import moe.karpador.patriot.capability.IMana;
import moe.karpador.patriot.capability.ManaProvider;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
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
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
        // some example code
        // logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postEvent(event);
    }

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        ModItems.registerItems(event);
    }

    @SubscribeEvent
    public static void onPlayerTickEvent(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        if (event.phase == TickEvent.Phase.START) {
            IMana mana = player.getCapability(ManaProvider.MANA_CAP, null);
            if(mana != null)
                mana.increaseMana();
        }
    }

    public static final CreativeTabs PATRIOT_TAB = new CreativeTabs(Patriot.MODID) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.itemPatriot);
        }
    };
}
