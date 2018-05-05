package moe.karpador.patriot.mana;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ManaProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IMana.class)
    public static final Capability<IMana> MANA_CAP = null;

    private IMana instance = MANA_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing enumFacing) {
        return capability == MANA_CAP;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing enumFacing) {
        return capability == MANA_CAP ? MANA_CAP.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return MANA_CAP.getStorage().writeNBT(MANA_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbtBase) {
        MANA_CAP.getStorage().readNBT(MANA_CAP, this.instance, null, nbtBase);
    }
}
