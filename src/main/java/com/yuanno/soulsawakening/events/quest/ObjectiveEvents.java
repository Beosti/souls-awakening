package com.yuanno.soulsawakening.events.quest;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.data.quest.IQuestData;
import com.yuanno.soulsawakening.data.quest.QuestDataCapability;
import com.yuanno.soulsawakening.events.RescueEvent;
import com.yuanno.soulsawakening.events.ability.AbilityUseEvent;
import com.yuanno.soulsawakening.quests.Objective;
import com.yuanno.soulsawakening.quests.Quest;
import com.yuanno.soulsawakening.quests.UseAbilityObjective;
import com.yuanno.soulsawakening.quests.objectives.KillObjective;
import com.yuanno.soulsawakening.quests.objectives.RescueObjective;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ObjectiveEvents {

    @SubscribeEvent
    public static void killEvent(LivingDeathEvent event)
    {
        if (!(event.getSource().getEntity() instanceof PlayerEntity))
            return;

        PlayerEntity player = (PlayerEntity) event.getSource().getEntity();
        IQuestData questData = QuestDataCapability.get(player);
        for (int i = 0; i < questData.getQuests().size(); i++)
        {
            Quest quest = questData.getQuests().get(i);
            for (int ia = 0; ia < quest.getObjectives().size(); ia++)
            {
                if (quest.getObjectives().get(ia) instanceof KillObjective && ((KillObjective) quest.getObjectives().get(ia)).getKill().test(player, event.getEntityLiving(), event.getSource()))
                    quest.getObjectives().get(ia).alterProgress(1);
            }
        }
    }

    /**
     * When an ability is used, checks if the player has a quest > objective that it can progress and if it can it does
     * @param event AbilityUseEvent.Post that is fired after an ability is used
     */
    @SubscribeEvent
    public static void onUseEvent(AbilityUseEvent.Post event)
    {
        if (event.getPlayer().level.isClientSide)
            return;
        PlayerEntity player = event.getPlayer();
        IQuestData questData = QuestDataCapability.get(player);
        for (int i = 0; i < questData.getQuests().size(); i++)
        {
            if (!questData.getQuests().get(i).getIsInProgress())
                continue;
            List<Objective> objectives = questData.getQuests().get(i).getObjectives();
            for (int ia = 0; ia < objectives.size(); ia++)
            {
                if (!(objectives.get(ia) instanceof UseAbilityObjective))
                    continue;
                UseAbilityObjective objective = (UseAbilityObjective) objectives.get(ia);
                if (!event.getAbility().getName().equals(objective.getAbility().getName()))
                    continue;
                if (objective.getProgress() < objective.getMaxProgress())
                    objective.alterProgress(1);
            }
        }
    }
    @SubscribeEvent
    public static void onRescueEvent(RescueEvent event)
    {
        PlayerEntity player = event.getPlayer();
        IQuestData questData = QuestDataCapability.get(player);
        for (int i = 0; i < questData.getQuests().size(); i++)
        {
            if (!questData.getQuests().get(i).getIsInProgress())
                continue;
            List<Objective> objectives = questData.getQuests().get(i).getObjectives();
            for (int ia = 0; ia < objectives.size(); ia++)
            {
                if (!(objectives.get(ia) instanceof RescueObjective))
                    continue;
                RescueObjective rescueObjective = (RescueObjective) objectives.get(ia);
                if (rescueObjective.getRescue().test(player, event.getRescued()))
                    rescueObjective.alterProgress(1);
            }
        }
    }
}
