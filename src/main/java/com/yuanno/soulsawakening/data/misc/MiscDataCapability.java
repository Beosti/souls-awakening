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
                props.putBoolean("canRenderRaceOverlay", instance.getRenderRaceOverlay());
                props.putInt("kan", instance.getKan());
                props.putString("rank", instance.getRank());
                props.putInt("spiritChain", instance.getSpiritChain());

                return props;
            }

            @Override
            public void readNBT(Capability<IMiscData> capability, IMiscData instance, Direction side, INBT nbt)
            {
                CompoundNBT props = (CompoundNBT) nbt;
                instance.setCanRenderOverlay(props.getBoolean("canrenderoverlay"));
                instance.setRenderRaceOverlay(props.getBoolean("canRenderRaceOverlay"));
                instance.setKan(props.getInt("kan"));
                instance.setRank(props.getString("rank"));
                instance.setSpiritChain(props.getInt("spiritChain"));
            }
        }, () -> new MiscDataBase());

    }
    public static IMiscData get(final LivingEntity entity)
    {
        return entity.getCapability(INSTANCE, null).orElse(new MiscDataBase());
    }
}
