package moe.karpador.patriot.mana;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;


public class ManaStorage implements Capability.IStorage<IMana> {
    private static final String mana = "MANA";
    private static final String ultimateExplosion = "ULTIMATEEXPLOSION";
    private static final String hasPantsu = "HASPANTSU";
    @Override
    public NBTBase writeNBT(Capability<IMana> capability, IMana iMana, EnumFacing enumFacing) {
        NBTTagCompound saveData = new NBTTagCompound();
        saveData.setInteger(mana, iMana.getMana());
        saveData.setBoolean(ultimateExplosion, iMana.hasUltimateExplosion());
        saveData.setBoolean(hasPantsu, iMana.hasPantsu());
        return saveData;
        //return new NBTTagInt(iMana.getMana());
    }

    @Override
    public void readNBT(Capability<IMana> capability, IMana iMana, EnumFacing enumFacing, NBTBase nbtBase) {
        if(nbtBase instanceof NBTTagCompound) {
            NBTTagCompound saveData = (NBTTagCompound) nbtBase;
            iMana.setMana(saveData.getInteger(mana));
            iMana.setUltimateExplosion(saveData.getBoolean(ultimateExplosion));
            iMana.setPantsu(saveData.getBoolean(hasPantsu));
        }
        else if(nbtBase instanceof NBTTagInt) { // restore data from old save
            iMana.setMana(((NBTPrimitive)nbtBase).getInt());
        }
        // iMana.setMana(((NBTPrimitive)nbtBase).getInt());
    }
}
