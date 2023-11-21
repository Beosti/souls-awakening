package com.yuanno.soulsawakening.data.misc;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class MiscDataCapability {

    @CapabilityInject(IMiscData.class)
    public static final Capability<IMiscData> INSTANCE = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IMiscData.class, new Capability.IStorage<IMiscData>()
        {
            @Override
            public INBT writeNBT(Capability<IMiscData> capability, IMiscData instance, Direction side)
            {
                CompoundNBT props = new CompoundNBT();
                props.putBoolean("canrenderoverlay", instance.getCanRenderOverlay());
                return props;
            }

            @Override
            public void readNBT(Capability<IMiscData> capability, IMiscData instance, Direction side, INBT nbt)
            {
                CompoundNBT props = (CompoundNBT) nbt;
                instance.setCanRenderOverlay(props.getBoolean("canrenderoverlay"));
            }
        }, () -> new MiscDataBase());

    }
    public static IMiscData get(final LivingEntity entity)
    {
        return entity.getCapability(INSTANCE, null).orElse(new MiscDataBase());
    }
}
