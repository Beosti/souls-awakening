package com.yuanno.soulsawakening.data.misc;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.Constants;

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
                props.putBoolean("canRenderZanpakutoOverlay", instance.getRenderZanpakutoOverlay());
                props.putString("setZanpakutoStyle", instance.getZanpakutoStyle());

                ListNBT unlockedOverlays = new ListNBT();
                for (int i = 0; i < instance.getUnlockedZanpakutoStyle().size(); i++)
                {
                    CompoundNBT unlockedStyle = new CompoundNBT();
                    unlockedStyle.putString("name", instance.getUnlockedZanpakutoStyle().get(i));
                    unlockedOverlays.add(unlockedStyle);
                }
                props.put("unlocked_zanpakuto_style", unlockedOverlays);
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
                instance.setRenderZanpakutoOverlay(props.getBoolean("canRenderZanpakutoOverlay"));
                instance.setZanpakutoStyle(props.getString("setZanpakutoStyle"));

                instance.clearUnlockedZanpakutoStyles();
                ListNBT unlockedZanpakutoStyles = props.getList("unlocked_zanpakuto_style", Constants.NBT.TAG_COMPOUND);
                for (int i = 0; i < unlockedZanpakutoStyles.size(); i++)
                {
                    CompoundNBT compoundNBTStyle = unlockedZanpakutoStyles.getCompound(i);
                    String zanpakutoStyle = compoundNBTStyle.getString("name");
                    instance.addUnlockedZanpakutoStyle(zanpakutoStyle);
                }
            }
        }, () -> new MiscDataBase());

    }
    public static IMiscData get(final LivingEntity entity)
    {
        return entity.getCapability(INSTANCE, null).orElse(new MiscDataBase());
    }
}
