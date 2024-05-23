package com.yuanno.soulsawakening.data.ability;

import com.yuanno.soulsawakening.ability.api.Ability;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;

public class AbilityDataCapability {
    
    @CapabilityInject(IAbilityData.class)
    public static final Capability<IAbilityData> INSTANCE = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IAbilityData.class, new Capability.IStorage<IAbilityData>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<IAbilityData> capability, IAbilityData instance, Direction side) {
                CompoundNBT props = new CompoundNBT();
                props.putInt("selected_integer", instance.getSelectionAbility());

                try {
                    ListNBT unlockedAbilities = new ListNBT();
                    for (int i = 0; i < instance.getUnlockedAbilities().size(); i++)
                    {
                        Ability ability = instance.getUnlockedAbilities().get(i);
                        CompoundNBT nbtAbility = ability.save();
                        unlockedAbilities.add(nbtAbility);
                    }
                    props.put("unlocked_abilities", unlockedAbilities);
                    ListNBT abilitiesInBar = new ListNBT();
                    for (int i = 0; i < instance.getAbilitiesInBar().size(); i++)
                    {
                        Ability ability = instance.getAbilitiesInBar().get(i);
                        CompoundNBT nbtAbility = ability.save();
                        abilitiesInBar.add(nbtAbility);
                    }
                    props.put("abilities_bar", abilitiesInBar);
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
                return props;
            }

            @Override
            public void readNBT(Capability<IAbilityData> capability, IAbilityData instance, Direction side, INBT nbt) {
                CompoundNBT compoundNBT = (CompoundNBT) nbt;
                instance.setSelectedAbility(compoundNBT.getInt("selected_integer"));
                try {
                    instance.clearUnlockedAbilities();

                    ListNBT unlockedAbilities = compoundNBT.getList("unlocked_abilities", Constants.NBT.TAG_COMPOUND);
                    for (int i = 0; i < unlockedAbilities.size(); i++)
                    {
                        CompoundNBT nbtAbility = unlockedAbilities.getCompound(i);

                        try
                        {
                            Ability ability = (Ability) GameRegistry.findRegistry(Ability.class).getValue(new ResourceLocation(nbtAbility.getString("id")));
                            if (ability == null)
                                continue;
                            ability.load(nbtAbility);
                            instance.loadUnlockedAbility(ability);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }

                    ListNBT abilitiesInBar = compoundNBT.getList("abilities_bar", Constants.NBT.TAG_COMPOUND);
                    for (int i = 0; i < abilitiesInBar.size(); i++)
                    {
                        CompoundNBT nbtAbility = abilitiesInBar.getCompound(i);

                        try
                        {
                            Ability ability = (Ability) GameRegistry.findRegistry(Ability.class).getValue(new ResourceLocation(nbtAbility.getString("id")));
                            if (ability == null)
                                continue;
                            ability.load(nbtAbility);
                            instance.loadAbilityInBar(ability);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }

            }
        }, AbilityDataBase::new);
    }

    public static IAbilityData get(final LivingEntity entity)
    {
        return entity.getCapability(INSTANCE, null).orElse(new AbilityDataBase());
    }
}
