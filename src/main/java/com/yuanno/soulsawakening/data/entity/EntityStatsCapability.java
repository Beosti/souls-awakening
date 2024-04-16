package com.yuanno.soulsawakening.data.entity;

import com.yuanno.soulsawakening.data.entity.hollow.HollowStats;
import com.yuanno.soulsawakening.data.entity.shinigami.ShinigamiStats;
import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.*;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

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
                if (instance.getRace().equals(ModValues.SHINIGAMI) && instance.hasShinigamiStats())
                    props.put("shinigami", instance.getShinigamiStats().save());
                if (instance.getRace().equals(ModValues.HOLLOW) && instance.hasHollowStats())
                    props.put("hollow", instance.getHollowStats().save());
                props.putDouble("reiatsupoints", instance.getReiatsuPoints());


                return props;
            }

            @Override
            public void readNBT(Capability<IEntityStats> capability, IEntityStats instance, Direction side, INBT nbt)
            {
                CompoundNBT props = (CompoundNBT) nbt;
                instance.setRace(props.getString("race"));
                instance.setRank(props.getString("rank"));
                if (props.getString("race").equals(ModValues.SHINIGAMI)) {
                    CompoundNBT compoundNBT = props.getCompound("shinigami");
                    ShinigamiStats shinigamiStats = new ShinigamiStats();
                    shinigamiStats.load(compoundNBT);
                    instance.setShinigamiStats(shinigamiStats);
                }
                if (props.getString("race").equals(ModValues.HOLLOW))
                {
                    CompoundNBT compoundNBT = props.getCompound("hollow");
                    HollowStats hollowStats = new HollowStats();
                    hollowStats.load(compoundNBT);
                    instance.setHollowStats(hollowStats);
                }
                instance.setReiatsuPoints(props.getDouble("reiatsupoints"));
            }
        }, EntityStatsBase::new);

    }
    public static IEntityStats get(final LivingEntity entity)
    {
        return entity.getCapability(INSTANCE, null).orElse(new EntityStatsBase());
    }
}
