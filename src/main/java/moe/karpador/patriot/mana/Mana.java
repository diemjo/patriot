package moe.karpador.patriot.mana;

import moe.karpador.patriot.ModConfig;
import moe.karpador.patriot.network.PantsuMessage;
import moe.karpador.patriot.network.PatriotPacketHandler;
import moe.karpador.patriot.network.RestoreManaMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class Mana implements IMana {
    //3 minutes cooldown
    public static int maxMana = 20*ModConfig.meguminStaff.cooldown;
    public int mana;
    private boolean ultimateExplosion = false;
    //5 minutes cooldown
    private final int pantsuCooldown = 20*300;
    private int pantsuCooldownCounter = 0;
    private boolean hasPantsu = true;
    private boolean exhausted = false;
    private boolean isFirstJoin = true;

    @Override
    public boolean enoughMana() {
        return mana >= maxMana;
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
    public void setMana(int value, boolean sendManaToServer) {
        mana = Math.max(0, Math.min(value, maxMana));
        if(sendManaToServer) {
            PatriotPacketHandler.wrapper.sendToServer(new RestoreManaMessage(this));
        }
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
    public int getPantsuCooldownCounter() {
        return pantsuCooldownCounter;
    }

    @Override
    public void setPantsuCooldownCounter(int value) {
        pantsuCooldownCounter = value;
    }

    @Override
    public void pantsuTick(EntityPlayer target) {
        if(!hasPantsu) {
            pantsuCooldownCounter++;
            if(pantsuCooldownCounter >= pantsuCooldown) {
                //PatriotPacketHandler.wrapper.sendToServer(new PantsuMessage(true, false, target));
                setPantsu(true);
            }
        }
    }

    @Override
    public boolean isExhausted() {
        return exhausted;
    }

    @Override
    public void setExhausted(boolean value) {
        exhausted = value;
    }

    @Override
    public boolean isFirstJoin() {
        return isFirstJoin;
    }

    @Override
    public void setIsFirstJoin(boolean value) {
        isFirstJoin = value;
    }

}

