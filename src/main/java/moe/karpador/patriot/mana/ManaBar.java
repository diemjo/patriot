package moe.karpador.patriot.mana;

import moe.karpador.patriot.Patriot;
import moe.karpador.patriot.items.ItemMeguminStaff;
import moe.karpador.patriot.items.ItemPantsu;
import moe.karpador.patriot.items.ItemStealMagic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ManaBar extends Gui
{
    private Minecraft mc;

    private static final ResourceLocation texture = new ResourceLocation(Patriot.MODID, "textures/gui/mana_bar_longer.png");

    public ManaBar(Minecraft mc) {
        super();
        this.mc = mc;
    }

    @SubscribeEvent(priority=EventPriority.NORMAL)
    public void onRenderExperienceBar(RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            return;
        }

        ItemStack mainhandItem = mc.player.getHeldItem(EnumHand.MAIN_HAND);
        ItemStack offhandItem = mc.player.getHeldItem(EnumHand.OFF_HAND);

        if (mc.player.isCreative())
            return;

        if (!(mainhandItem.getItem() instanceof ItemMeguminStaff) && !(offhandItem.getItem() instanceof ItemMeguminStaff) &&
                !(mainhandItem.getItem() instanceof ItemPantsu) && !(offhandItem.getItem() instanceof ItemPantsu) &&
                !(mainhandItem.getItem() instanceof ItemStealMagic) && !(offhandItem.getItem() instanceof ItemStealMagic))
            return;

        IMana mana = mc.player.getCapability(ManaProvider.MANA_CAP, null);
        if (mana == null) {
            return;
        }

        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

        int xPos = sr.getScaledWidth()/2 + 10;
        int yPos = sr.getScaledHeight() - 49;
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
        drawTexturedModalRect(xPos, yPos, 0, 0, 81, 9);

        // You can keep drawing without changing anything
        int manabarwidth = (int)(((float) mana.getMana() / mana.getMaxMana()) * 74);
        if (mana.enoughMana()) {
            if (mana.hasUltimateExplosion())
                drawTexturedModalRect(xPos + 3, yPos + 3, 0, 18, manabarwidth, 3);
            else
                drawTexturedModalRect(xPos + 3, yPos + 3, 0, 12, manabarwidth, 3);
        }
        else {
            if (mana.hasUltimateExplosion())
                drawTexturedModalRect(xPos + 3, yPos + 3, 0, 15, manabarwidth, 3);
            else
                drawTexturedModalRect(xPos + 3, yPos + 3, 0, 9, manabarwidth, 3);
        }

        String s = "Mana " + mana.getMana() + "/" + mana.getMaxMana();
        yPos += 10;
        /*this.mc.fontRenderer.drawString(s, xPos + 1, yPos, 0);
        this.mc.fontRenderer.drawString(s, xPos - 1, yPos, 0);
        this.mc.fontRenderer.drawString(s, xPos, yPos + 1, 0);
        this.mc.fontRenderer.drawString(s, xPos, yPos - 1, 0);
        this.mc.fontRenderer.drawString(s, xPos, yPos, 8453920);*/
        GlStateManager.popAttrib();
    }
}
