package moe.karpador.patriot;
import moe.karpador.patriot.mana.Mana;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

//@Config(modid = Patriot.MODID)
//@Config.LangKey(Patriot.MODID+".config.title")
public class ModConfig {

    @Config.Name("Megumin Staff")
    public static final MeguminStaff meguminStaff = new MeguminStaff();

    public static class MeguminStaff {

        @Config.Name("§3Mana §frecovery time")
        @Config.Comment("The time required to replenish your mana (in seconds)")
        @Config.RangeInt(min=1, max=3600)
        public int cooldown = 180;
    }

    @Mod.EventBusSubscriber(modid = Patriot.MODID)
    private static class EventHandler {

        /**
         * Inject the new values and save to the config file when the config has been changed from the GUI.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(Patriot.MODID)) {
                ConfigManager.sync(Patriot.MODID, Config.Type.INSTANCE);
                if (meguminStaff.cooldown<=10 && Mana.maxMana > 10) {
                    Minecraft.getMinecraft().player.sendMessage(new TextComponentString("You are making it too easy with that cooldown, don't you think?"));
                    Minecraft.getMinecraft().player.sendMessage(new TextComponentString("how unrealistic... and you call yourself a §cMEGUMIN §ffan..."));
                }
                Mana.maxMana = 20*meguminStaff.cooldown;
            }
        }
    }
}