package moe.karpador.patriot;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@SuppressWarnings("WeakerAccess")
@ObjectHolder(Patriot.MODID)
public class PatriotSoundHandler {
    //private static int size;
    @ObjectHolder("explosion")
    public static SoundEvent explosion;
    @ObjectHolder("kyaa")
    public static SoundEvent kyaa;

    public static void init() {
        //size = SoundEvent.REGISTRY.getKeys().size();
        explosion = register("explosion");
        kyaa = register("kyaa");
    }

    private static SoundEvent register(String name) {
        ResourceLocation location = new ResourceLocation(Patriot.MODID, name);
        SoundEvent event = new SoundEvent(location).setRegistryName(location);
        //size++;
        return event;
    }

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
            event.getRegistry().registerAll(explosion,
                                            kyaa);
        }
    }
}
