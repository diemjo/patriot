package moe.karpador.patriot;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class PatriotSoundHandler {
    //private static int size;
    public static SoundEvent explosion;
    public static SoundEvent kyaa;

    public static void init() {
        //size = SoundEvent.REGISTRY.getKeys().size();
        explosion = register("explosion");
        kyaa = register("kyaa");
    }

    private static SoundEvent register(String name) {
        ResourceLocation location = new ResourceLocation(Patriot.MODID, name);
        SoundEvent event = new SoundEvent(location);
        //size++;
        return event;
    }
}
