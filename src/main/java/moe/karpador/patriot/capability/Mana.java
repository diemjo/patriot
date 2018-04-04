package moe.karpador.patriot.capability;

public class Mana implements IMana {
    final public int maxMana = 200;
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
    public void increaseMana() {
        mana = Math.min(mana+1, maxMana);
    }

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public void setMana(int value) {
        mana = Math.max(0, Math.min(value, maxMana));
    }
}

