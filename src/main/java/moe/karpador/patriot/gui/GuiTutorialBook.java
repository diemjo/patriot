package moe.karpador.patriot.gui;

import moe.karpador.patriot.Patriot;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiTutorialBook extends GuiScreen {
    private final int bookImageHeight = 192;
    private final int bookImageWidth = 192;
    private int currPage = 0;
    private static final int bookTotalPages = 6;
    private static ResourceLocation[] bookPageTextures = new ResourceLocation[bookTotalPages];
    private static String[] stringPageText = new String[bookTotalPages];
    private GuiButton buttonDone;
    private NextPageButton buttonNextPage;
    private NextPageButton buttonPreviousPage;

    public GuiTutorialBook() {
        bookPageTextures[0] = new ResourceLocation(Patriot.MODID+":textures/gui/book.png");
        bookPageTextures[1] = new ResourceLocation(Patriot.MODID+":textures/gui/book.png");
        bookPageTextures[2] = new ResourceLocation(Patriot.MODID+":textures/gui/book.png");
        bookPageTextures[3] = new ResourceLocation(Patriot.MODID+":textures/gui/book.png");
        bookPageTextures[4] = new ResourceLocation(Patriot.MODID+":textures/gui/book_megumin.png");
        bookPageTextures[5] = new ResourceLocation(Patriot.MODID+":textures/gui/book_crafting_patriot.png");

        stringPageText[0] = "hello there";
        stringPageText[1] = "general kenobi";
        stringPageText[2] = "it's over anakin I have the high ground";
        stringPageText[3] = "don't underestimate my power";
        stringPageText[4] = "";
        stringPageText[5] = "Patriot\n\n\n\n\n\n\n\nThe legendary one and only Patriot. Traditionally used to whack the head of someone who pisses you off.";
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    @Override
    public void initGui()
    {
        // DEBUG
        System.out.println("GuiMysteriousStranger initGUI()");
        buttonList.clear();
        Keyboard.enableRepeatEvents(true);

        buttonDone = new GuiButton(0, width / 2 + 2, 4 + bookImageHeight,
                98, 20, I18n.format("gui.done", new Object[0]));

        buttonList.add(buttonDone);
        int offsetFromScreenLeft = (width - bookImageWidth) / 2;
        buttonList.add(buttonNextPage = new NextPageButton(1, offsetFromScreenLeft + 120, 156, true));
        buttonList.add(buttonPreviousPage = new NextPageButton(2, offsetFromScreenLeft + 38, 156, false));
        updateScreen(); // call update to set invisible buttons as invisible
    }

    /**
     * Called from the main game loop to update the screen.
     */
    @Override
    public void updateScreen()
    {
        buttonDone.visible = (currPage == bookTotalPages - 1);
        buttonNextPage.visible = (currPage < bookTotalPages - 1);
        buttonPreviousPage.visible = currPage > 0;
    }

    /**
     * Draws the screen and all the components in it.
     */
    @Override
    public void drawScreen(int parWidth, int parHeight, float p_73863_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (currPage >= 4) {
            mc.getTextureManager().bindTexture(bookPageTextures[currPage]);
        }
        else {
            mc.getTextureManager().bindTexture(bookPageTextures[0]);
        }
        int offsetFromScreenLeft = (width - bookImageWidth ) / 2;
        drawTexturedModalRect(offsetFromScreenLeft, 2, 0, 0, bookImageWidth, bookImageHeight);
        int widthOfString;
        String stringPageIndicator = I18n.format("book.pageIndicator", new Object[] {Integer.valueOf(currPage + 1), bookTotalPages});

        widthOfString = fontRenderer.getStringWidth(stringPageIndicator);
        fontRenderer.drawString(stringPageIndicator, offsetFromScreenLeft - widthOfString + bookImageWidth - 44, 18, 0);
        fontRenderer.drawSplitString(stringPageText[currPage], offsetFromScreenLeft + 36, 34, 116, 0);
        super.drawScreen(parWidth, parHeight, p_73863_3_);
    }

    /**
     * Called when a mouse button is pressed and the mouse is moved around.
     * Parameters are : mouseX, mouseY, lastButtonClicked &
     * timeSinceMouseClick.
     */
    @Override
    protected void mouseClickMove(int parMouseX, int parMouseY, int parLastButtonClicked, long parTimeSinceMouseClick)
    {
    }

    @Override
    protected void actionPerformed(GuiButton parButton)
    {
        if (parButton == buttonDone) {
            // You can send a packet to server here if you need server to do
            // something
            mc.displayGuiScreen((GuiScreen)null);
        }
        else if (parButton == buttonNextPage) {
            if (currPage < bookTotalPages - 1) {
                ++currPage;
            }
        }
        else if (parButton == buttonPreviousPage) {
            if (currPage > 0) {
                --currPage;
            }
        }
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat
     * events
     */
    @Override
    public void onGuiClosed() {
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in
     * single-player
     */
    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    static class NextPageButton extends GuiButton
    {
        private final boolean isNextButton;
        private int textureY;

        public NextPageButton(int parButtonId, int parPosX, int parPosY, boolean parIsNextButton) {
            super(parButtonId, parPosX, parPosY, 23, 13, "");
            isNextButton = parIsNextButton;
            textureY = 192;
            if (!isNextButton) {
                textureY += 13;
            }
        }

        /**
         * Draws this button to the screen.
         */
        @Override
        public void drawButton(Minecraft mc, int parX, int parY, float partialTicks) {
            if (visible) {
                boolean isButtonPressed = (parX >= x
                        && parY >= y
                        && parX < x + width
                        && parY < y + height);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                mc.getTextureManager().bindTexture(bookPageTextures[1]);
                int textureX = 0;
                if (isButtonPressed) {
                    textureX += 23;
                }

                drawTexturedModalRect(x, y, textureX, textureY, 23, 13);
            }
        }
    }
}
