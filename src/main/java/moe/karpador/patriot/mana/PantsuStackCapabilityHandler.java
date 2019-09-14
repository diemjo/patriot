package moe.karpador.patriot.mana;

import moe.karpador.patriot.Patriot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PantsuStackCapabilityHandler {
    public static final ResourceLocation PantsuStack_CAP = new ResourceLocation(Patriot.MODID, "pantsuStack");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<ItemStack> event)
    {
        //event.addCapability(PantsuStack_CAP, new PantsuStackProvider());
    }
}
