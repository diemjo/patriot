package moe.karpador.patriot.mana;

import net.minecraft.entity.player.EntityPlayer;

public interface IMana {
    public boolean enoughMana();
    public void increaseMana(int amount);
    public int getMana();
    public int getMaxMana();
    public void setMana(int value, boolean sendMessageToServer);
    public boolean hasUltimateExplosion();
    public void setUltimateExplosion(boolean value);
    public boolean hasPantsu();
    public void setPantsu(boolean value);
    public int getPantsuCooldownCounter();
    public void setPantsuCooldownCounter(int value);
    public void pantsuTick(EntityPlayer target);
    public boolean isExhausted();
    public void setExhausted(boolean value);
    public boolean isFirstJoin();
    public void setIsFirstJoin(boolean value);
}
