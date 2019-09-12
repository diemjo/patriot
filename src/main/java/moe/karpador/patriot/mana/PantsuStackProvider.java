package moe.karpador.patriot.mana;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PantsuStackProvider implements ICapabilitySerializable<NBTBase> {
    @CapabilityInject(IPantsuStack.class)
    public static final Capability<IPantsuStack> PANTSU_STACK_CAP = null;

    private IPantsuStack instance = PANTSU_STACK_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing enumFacing) {
        return capability == PANTSU_STACK_CAP;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing enumFacing) {
        return capability == PANTSU_STACK_CAP ? PANTSU_STACK_CAP.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return PANTSU_STACK_CAP.getStorage().writeNBT(PANTSU_STACK_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbtBase) {
        PANTSU_STACK_CAP.getStorage().readNBT(PANTSU_STACK_CAP, this.instance, null, nbtBase);
    }
}
