package moe.karpador.patriot.capability;

public class Mana implements IMana {
    //3 minutes cooldown
    final public int maxMana = 20*60*3;
    public int mana = maxMana;

    @Override
    public boolean enoughMana() {
        return mana >= maxMana;
    }

    @Override
    public void useMana() {
        mana=0;
    }
    @Override
    public void increaseMana(int amount) {
        mana = Math.min(mana+amount, maxMana);
    }

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public int getMaxMana() {
        return maxMana;
    }

    @Override
    public void setMana(int value) {
        mana = Math.max(0, Math.min(value, maxMana));
    }
}

