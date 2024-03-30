package com.yuanno.soulsawakening.quests.shinigamiteacher;

import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.misc.IMiscData;
import com.yuanno.soulsawakening.data.misc.MiscDataCapability;
import com.yuanno.soulsawakening.entity.PlusEntity;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.quests.Objective;
import com.yuanno.soulsawakening.quests.Quest;
import com.yuanno.soulsawakening.quests.QuestReward;
import com.yuanno.soulsawakening.quests.objectives.RescueObjective;
import net.minecraft.entity.player.PlayerEntity;

public class RescuePlusesQuest extends Quest {

    QuestReward questReward = QuestReward.builder()
            .otherReward(this::reward)
            .build();

    public boolean reward(PlayerEntity player)
    {
        IMiscData miscData = MiscDataCapability.get(player);
        miscData.setRank(ModValues.NON_OFFICER);
        return true;
    }

    public static final RescueObjective.ICheckRescue RESCUE_CHECK = ((player, target) ->
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (!entityStats.getRace().equals(ModValues.SHINIGAMI) && !entityStats.getRace().equals(ModValues.FULLBRINGER))
            return false;
       if (!player.getMainHandItem().getItem().equals(ModItems.ZANPAKUTO.get()))
           return false;
        return target instanceof PlusEntity;
    });

    private Objective objective = new RescueObjective("Rescue pluses", "You have to rescue 5 pluses", 5, RESCUE_CHECK);

    public RescuePlusesQuest()
    {
        this.setTitle("Rescue mission: save some pluses");
        this.setDescription("Send 5 pluses from the overworld to soul society");
        this.addObjective(objective);
        this.setQuestReward(questReward);
    }
}
