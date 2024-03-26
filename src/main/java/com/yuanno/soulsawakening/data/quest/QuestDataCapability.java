package com.yuanno.soulsawakening.data.quest;

import com.yuanno.soulsawakening.quests.Quest;
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

public class QuestDataCapability {

    @CapabilityInject(IQuestData.class)
    public static final Capability<IQuestData> INSTANCE = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IQuestData.class, new Capability.IStorage<IQuestData>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<IQuestData> capability, IQuestData instance, Direction side) {
                CompoundNBT props = new CompoundNBT();
                ListNBT quests = new ListNBT();
                for (int i = 0; i < instance.getQuests().size(); i++)
                {
                    Quest quest = instance.getQuests().get(i);
                    if (quest != null)
                        quests.add(quest.save());
                }
                props.put("quests", quests);

                return props;
            }

            @Override
            public void readNBT(Capability<IQuestData> capability, IQuestData instance, Direction side, INBT nbt) {
                CompoundNBT compoundNBT = (CompoundNBT) nbt;
                instance.clearQuests();

                ListNBT quests = compoundNBT.getList("quests", Constants.NBT.TAG_COMPOUND);
                for (int i = 0; i < quests.size(); i++)
                {
                    try {
                        CompoundNBT nbtQuest = quests.getCompound(i);
                        Quest quest = GameRegistry.findRegistry(Quest.class).getValue(new ResourceLocation(nbtQuest.getString("id"))); // could do it without passing through the registry
                        if (quest == null)
                            continue;
                        quest.load(nbtQuest);
                        instance.addQuest(quest);
                    } catch (Exception e)
                    {
                        continue;
                    }
                }
            }
        }, QuestDataBase::new);
    }

    public static IQuestData get(final LivingEntity entity)
    {
        return entity.getCapability(INSTANCE, null).orElse(new QuestDataBase());
    }
}
