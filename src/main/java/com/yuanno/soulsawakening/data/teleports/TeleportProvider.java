package com.yuanno.soulsawakening.data.teleports;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class TeleportProvider implements ICapabilitySerializable<CompoundNBT> {

    private ITeleports instance = TeleportCapability.INSTANCE.getDefaultInstance();

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
    {
        return TeleportCapability.INSTANCE.orEmpty(cap, LazyOptional.of(() -> instance));
    }

    @Override
    public CompoundNBT serializeNBT()
    {
        return (CompoundNBT) TeleportCapability.INSTANCE.getStorage().writeNBT(TeleportCapability.INSTANCE, instance, null);
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt)
    {
        TeleportCapability.INSTANCE.getStorage().readNBT(TeleportCapability.INSTANCE, instance, null, nbt);
    }
}
