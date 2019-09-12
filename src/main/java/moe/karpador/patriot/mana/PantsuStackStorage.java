package moe.karpador.patriot.mana;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class PantsuStackStorage implements Capability.IStorage<IPantsuStack> {
    private static final String ownerName = "OWNERNAME";

    @Override
    public NBTBase writeNBT(Capability<IPantsuStack> capability, IPantsuStack iPantsuStack, EnumFacing enumFacing) {
        NBTTagCompound saveData = new NBTTagCompound();
        saveData.setString(ownerName, iPantsuStack.getOwnerName());
        return saveData;
    }

    @Override
    public void readNBT(Capability<IPantsuStack> capability, IPantsuStack iPantsuStack, EnumFacing enumFacing, NBTBase nbtBase) {
        NBTTagCompound saveData = (NBTTagCompound) nbtBase;
        iPantsuStack.setOwnerName(saveData.getString(ownerName));
    }
}
