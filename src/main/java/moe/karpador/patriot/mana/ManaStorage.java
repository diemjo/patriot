package moe.karpador.patriot.mana;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;


public class ManaStorage implements Capability.IStorage<IMana> {
    @Override
    public NBTBase writeNBT(Capability<IMana> capability, IMana iMana, EnumFacing enumFacing) {
        return new NBTTagInt(iMana.getMana());
    }

    @Override
    public void readNBT(Capability<IMana> capability, IMana iMana, EnumFacing enumFacing, NBTBase nbtBase) {
        iMana.setMana(((NBTPrimitive)nbtBase).getInt());
    }
}
