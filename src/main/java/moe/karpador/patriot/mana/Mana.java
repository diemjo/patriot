package moe.karpador.patriot.mana;

import moe.karpador.patriot.ModConfig;

public class Mana implements IMana {
    //3 minutes cooldown
    public static int maxMana = 20*ModConfig.meguminStaff.cooldown;
    public int mana = maxMana;
    private boolean ultimateExplosion = false;
    //5 minutes cooldown
    private final int pantsuCooldown = 20*300;
    private int pantsuCooldownCounter = 0;
    private boolean hasPantsu = true;

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

    @Override
    public boolean hasUltimateExplosion() {
        return ultimateExplosion;
    }

    @Override
    public void setUltimateExplosion(boolean value) {
        ultimateExplosion = value;
    }

    @Override
    public boolean hasPantsu() {
        return hasPantsu;
    }

    @Override
    public void setPantsu(boolean value) {
        hasPantsu = value;
        pantsuCooldownCounter = 0;
    }

    @Override
    public void pantsuTick() {
        if(!hasPantsu) {
            pantsuCooldownCounter++;
            if(pantsuCooldownCounter >= pantsuCooldown) {
                hasPantsu = true;
            }
        }
    }
}

