package moe.karpador.patriot;

import moe.karpador.patriot.items.ModItems;
import moe.karpador.patriot.mana.IMana;
import moe.karpador.patriot.mana.ManaProvider;
import moe.karpador.patriot.network.PatriotPacketHandler;
import moe.karpador.patriot.network.RestoreManaMessage;
import moe.karpador.patriot.network.RestorePantsuMessage;
import moe.karpador.patriot.proxy.CommonProxy;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
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

    @SidedProxy(clientSide = "moe.karpador.patriot.proxy.ClientProxy", serverSide = "moe.karpador.patriot.proxy.CommonProxy")
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
        proxy.postInit(event);
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
            if(mana != null) {
                mana.increaseMana(1);


                mana.pantsuTick(event.player);
            }
            // prevent player from moving when exhausted
            if(mana.isExhausted() && event.player.world.isRemote) {
                KeyBinding.unPressAllKeys();
            }
        }
    }

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if(!event.getWorld().isRemote && event.getEntity() instanceof EntityPlayer) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    syncManaWithNewPlayer(event); // sync mana later to give client time to load world
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    // server synchronizes the mana capability with the new player and current players
    private static void syncManaWithNewPlayer(EntityJoinWorldEvent event) {
        EntityPlayer newPlayer = (EntityPlayer) event.getEntity();
        // send save of the mana capability of this player
        IMana newPlayerMana = newPlayer.getCapability(ManaProvider.MANA_CAP, null);
        if(newPlayerMana != null) {
            PatriotPacketHandler.wrapper.sendTo(new RestoreManaMessage(newPlayerMana), (EntityPlayerMP) newPlayer);
        }

        RestorePantsuMessage newPlayerPantsuMsg = new RestorePantsuMessage(newPlayerMana, newPlayer);
        // send pantsu state from the mana capability of all currently playing players to the new player
        for (EntityPlayer player : event.getWorld().playerEntities) {
            IMana playerMana = player.getCapability(ManaProvider.MANA_CAP, null);
            if(playerMana != null) {
                PatriotPacketHandler.wrapper.sendTo(new RestorePantsuMessage(playerMana, player), (EntityPlayerMP)newPlayer);
            }
            // send the pantsu state of new player to all currently playing players
            PatriotPacketHandler.wrapper.sendTo(newPlayerPantsuMsg, (EntityPlayerMP) player); // on server you can just cast to EntityPlayerMP
        }
    }
    /*// Allows for the capability to persist after death.
    @SubscribeEvent
    public static void clonePlayer(PlayerEvent.Clone event) {
        event.getEntity().sendMessage(new TextComponentString("cloned player"));
        final IMana original = event.getOriginal().getCapability(ManaProvider.MANA_CAP, null);
        final IMana clone = event.getEntity().getCapability(ManaProvider.MANA_CAP, null);
        clone.setMana(original.getMana(), false);
    }*/

    @SubscribeEvent
    public static void onPlayerWakeUp(PlayerWakeUpEvent event) {
        new Thread(() -> {
            try {
                Thread.sleep(1000); // when regenerating immediately, clients time is still night
                regenerateManaAfterSleep(event.getEntityPlayer());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void regenerateManaAfterSleep(EntityPlayer player) {
        if(player.world.getWorldTime()%24000 < 1000) {
            IMana mana = player.getCapability(ManaProvider.MANA_CAP, null);
            if (mana != null) {
                mana.increaseMana((int) Math.ceil(mana.getMaxMana() * 0.75));
            }
        }
    }

    public static final CreativeTabs PATRIOT_TAB = new CreativeTabs(Patriot.MODID) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.itemPatriot);
        }
    };
}
