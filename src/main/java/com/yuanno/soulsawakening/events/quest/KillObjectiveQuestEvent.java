package com.yuanno.soulsawakening.events.quest;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.data.quest.IQuestData;
import com.yuanno.soulsawakening.data.quest.QuestDataCapability;
import com.yuanno.soulsawakening.quests.objectives.KillObjective;
import com.yuanno.soulsawakening.quests.Quest;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class KillObjectiveQuestEvent {

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
}
