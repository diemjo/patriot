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
        mana=mana<maxMana? mana++ : maxMana;
    }

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public void setMana(int value) {
        mana = value;
    }
}

