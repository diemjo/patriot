package moe.karpador.patriot.capability;

public interface IMana {
    public boolean enoughMana();
    public void useMana();
    public void increaseMana();
    public int getMana();
    public int getMaxMana();
    public void setMana(int value);
}
