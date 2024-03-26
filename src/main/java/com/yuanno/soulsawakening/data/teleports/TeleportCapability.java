package com.yuanno.soulsawakening.data.teleports;

import com.yuanno.soulsawakening.teleport.TeleportPosition;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;

public class TeleportCapability {

    @CapabilityInject(ITeleports.class)
    public static final Capability<ITeleports> INSTANCE = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(ITeleports.class, new Capability.IStorage<ITeleports>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<ITeleports> capability, ITeleports instance, Direction side) {
                CompoundNBT props = new CompoundNBT();
                ListNBT teleports = new ListNBT();
                for (int i = 0; i < instance.getTeleportPositions().size(); i++)
                {
                    TeleportPosition teleportPosition = instance.getTeleportPositions().get(i);
                    if (teleportPosition != null)
                        teleports.add(teleportPosition.save());
                }
                props.put("teleports", teleports);
                return props;
            }

            @Override
            public void readNBT(Capability<ITeleports> capability, ITeleports instance, Direction side, INBT nbt) {
                CompoundNBT compoundNBT = (CompoundNBT) nbt;
                instance.clearTeleportPositions();

                ListNBT teleportsPositions = compoundNBT.getList("teleports", Constants.NBT.TAG_COMPOUND);
                for (int i = 0; i < teleportsPositions.size(); i++)
                {
                    try {
                        CompoundNBT nbtTeleport = teleportsPositions.getCompound(i);
                        TeleportPosition teleportPosition = new TeleportPosition();
                        teleportPosition.load(nbtTeleport);
                        instance.addTeleportsPosition(teleportPosition);
                    } catch (Exception e)
                    {
                        System.out.println("something went wrong with the loading phase of teleportation capability");
                        continue;
                    }
                }
            }
        }, TeleportBase::new);
    }

    public static ITeleports get(final LivingEntity entity)
    {
        return entity.getCapability(INSTANCE, null).orElse(new TeleportBase());
    }
}
