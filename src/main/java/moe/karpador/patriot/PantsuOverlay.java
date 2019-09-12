package moe.karpador.patriot;

import moe.karpador.patriot.items.ItemGenericPantsu;
import moe.karpador.patriot.items.ItemMeguminPantsu;
import moe.karpador.patriot.items.ItemPantsu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PantsuOverlay extends Gui {
    private Minecraft mc;

    private static final ResourceLocation texture_generic = new ResourceLocation(Patriot.MODID, "textures/gui/pantsu_generic_overlay.png");
    private static final ResourceLocation texture_megumin = new ResourceLocation(Patriot.MODID, "textures/gui/pantsu_megumin_overlay.png");

    public PantsuOverlay(Minecraft mc) {
        super();
        this.mc = mc;
    }

    @SubscribeEvent(priority= EventPriority.NORMAL)
    public void onRenderExperienceBar(RenderGameOverlayEvent.Post event) {

        if (event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            return;
        }

        ResourceLocation texture = null;
        for (ItemStack item : mc.player.inventory.armorInventory) {
            if (item.getItem() instanceof ItemGenericPantsu) {
                texture = texture_generic;
            }
            else if (item.getItem() instanceof ItemMeguminPantsu) {
                texture = texture_megumin;
            }
        }
        if (texture==null)
            return;

        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        mc.getTextureManager().bindTexture(texture);

        // Add this block of code before you draw the section of your texture containing transparency
        GlStateManager.pushAttrib();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableLighting();
        // alpha test and blend needed due to vanilla or Forge rendering bug
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        // Here we draw the background bar which contains a transparent section; note the new size
        //drawTexturedModalRect(xPos, yPos, 0, 0, 56, 9);
        //drawTexturedModalRect(0, 0, 0, 0, sr.getScaledWidth(), 50);
        drawScaledCustomSizeModalRect(0, 0, 0, 0, 1280, 720, sr.getScaledWidth(), sr.getScaledHeight(), 1280, 720);
        //mc.fontRenderer.drawString("GUI ("+sr.getScaledWidth()+"x"+sr.getScaledHeight()+"): " + (texture==texture_generic?"TextureGeneric":(texture==texture_megumin?"TextureMegumin":"none")), 0, 150, 1);
        GlStateManager.popAttrib();
    }
}
