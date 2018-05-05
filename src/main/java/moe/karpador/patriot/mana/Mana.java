package moe.karpador.patriot.mana;

import moe.karpador.patriot.ModConfig;

public class Mana implements IMana {
    //3 minutes cooldown
    public static int maxMana = 20*ModConfig.meguminStaff.cooldown;
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

