package moe.karpador.patriot.capability;

public interface IMana {
    public boolean enoughMana();
    public void useMana();
    public void increaseMana(int amount);
    public int getMana();
    public int getMaxMana();
    public void setMana(int value);
}
