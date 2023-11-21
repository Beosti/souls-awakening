package com.yuanno.soulsawakening.data.misc;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class MiscDataProvider implements ICapabilitySerializable<CompoundNBT> {
    private IMiscData instance = MiscDataCapability.INSTANCE.getDefaultInstance();

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side)
    {
        return MiscDataCapability.INSTANCE.orEmpty(capability, LazyOptional.of(() -> instance));
    }

    @Override
    public CompoundNBT serializeNBT()
    {
        return (CompoundNBT) MiscDataCapability.INSTANCE.getStorage().writeNBT(MiscDataCapability.INSTANCE, instance, null);
    }

    @Override
    public void deserializeNBT(CompoundNBT compoundNBT)
    {
        MiscDataCapability.INSTANCE.getStorage().readNBT(MiscDataCapability.INSTANCE, instance, null, compoundNBT);
    }
}
