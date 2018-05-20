package moe.karpador.patriot.mana;

public interface IMana {
    public boolean enoughMana();
    public void useMana();
    public void increaseMana(int amount);
    public int getMana();
    public int getMaxMana();
    public void setMana(int value);
    public boolean hasUltimateExplosion();
    public void setUltimateExplosion(boolean value);
    public boolean hasPantsu();
    public void setPantsu(boolean value);
    public void pantsuTick();
}
