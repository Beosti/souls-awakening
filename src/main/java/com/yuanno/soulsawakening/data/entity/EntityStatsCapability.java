package com.yuanno.soulsawakening.data.entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.*;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EntityStatsCapability {

    @CapabilityInject(IEntityStats.class)
    public static final Capability<IEntityStats> INSTANCE = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IEntityStats.class, new Capability.IStorage<IEntityStats>()
        {
            @Override
            public INBT writeNBT(Capability<IEntityStats> capability, IEntityStats instance, Direction side)
            {
                CompoundNBT props = new CompoundNBT();
                props.putString("race", instance.getRace());
                props.putString("rank", instance.getRank());
                props.putInt("hollowpoints", instance.getHollowPoints());
                props.putDouble("zanjutsupoints", instance.getZanjutsuPoints());
                props.putDouble("hakudapoints", instance.getHakudaPoints());
                props.putDouble("hohopoints", instance.getHohoPoints());
                props.putInt("classlevel", instance.getClassLevel());
                props.putInt("classpoints", instance.getClassPoints());
                props.putInt("classexperience", instance.getClassExperience());

                CompoundNBT listNBT = new CompoundNBT();
                List<Double> availableStats = instance.getAvailableStats();
                ListNBT listTag = new ListNBT();
                for (Double value : availableStats)
                    listTag.add(DoubleNBT.valueOf(value));

                listNBT.put("availabeStatsData", listTag);
                props.put("availableStats", listNBT);

                return props;
            }

            @Override
            public void readNBT(Capability<IEntityStats> capability, IEntityStats instance, Direction side, INBT nbt)
            {
                CompoundNBT props = (CompoundNBT) nbt;
                instance.setRace(props.getString("race"));
                instance.setRank(props.getString("rank"));
                instance.setHollowPoints(props.getInt("hollowpoints"));
                instance.setZanjutsuPoints(props.getDouble("zanjutsupoints"));
                instance.setHakudaPoints(props.getDouble("hakudapoints"));
                instance.setHohoPoints(props.getDouble("hohopoints"));
                instance.setClassLevel(props.getInt("classlevel"));
                instance.setClassPoints(props.getInt("classpoints"));
                instance.setClassExperience(props.getInt("classexperience"));

                CompoundNBT listNBT = props.getCompound("availableStats");
                ListNBT listTag = listNBT.getList("availabeStatsData", Constants.NBT.TAG_DOUBLE);
                List<Double> epithetList = new ArrayList<>();
                for (int i = 0; i < listTag.size(); i++)
                {
                    double value = listTag.getDouble(i);
                    epithetList.add(value);
                    instance.addAvailableStats(value);
                }

            }
        }, () -> new EntityStatsBase());

    }
    public static IEntityStats get(final LivingEntity entity)
    {
        return entity.getCapability(INSTANCE, null).orElse(new EntityStatsBase());
    }
}
