package moe.karpador.patriot.mana;

import moe.karpador.patriot.Patriot;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler {
    public static final ResourceLocation MANA_CAP = new ResourceLocation(Patriot.MODID, "mana");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (!(event.getObject() instanceof EntityPlayer))
            return;
        event.addCapability(MANA_CAP, new ManaProvider());
    }
    // Allows for the capability to persist after death.

    @SubscribeEvent
    public void clonePlayer(PlayerEvent.Clone event) {

        final IMana original = event.getOriginal().getCapability(ManaProvider.MANA_CAP, null);
        final IMana clone = event.getEntity().getCapability(ManaProvider.MANA_CAP, null);
        clone.setMana(original.getMana());
    }
}
