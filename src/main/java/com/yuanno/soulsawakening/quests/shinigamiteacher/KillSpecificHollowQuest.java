package com.yuanno.soulsawakening.quests.shinigamiteacher;

import com.yuanno.soulsawakening.data.misc.IMiscData;
import com.yuanno.soulsawakening.data.misc.MiscDataCapability;
import com.yuanno.soulsawakening.entities.hollow.ApeEntity;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncMiscDataPacket;
import com.yuanno.soulsawakening.quests.Objective;
import com.yuanno.soulsawakening.quests.Quest;
import com.yuanno.soulsawakening.quests.QuestReward;
import com.yuanno.soulsawakening.quests.objectives.KillObjective;
import net.minecraft.entity.player.PlayerEntity;

public class KillSpecificHollowQuest extends Quest {

    QuestReward questReward = QuestReward.builder()
            .otherReward(this::reward)
            .build();
    public boolean reward(PlayerEntity player)
    {
        IMiscData miscData = MiscDataCapability.get(player);
        miscData.alterKan(500);
        PacketHandler.sendTo(new SSyncMiscDataPacket(player.getId(), miscData), player);
        return true;
    }

    public static final KillObjective.ICheckKill HOLLOW_CHECK = ((player, target, source) ->
    {
       return target instanceof ApeEntity;
    });

    private Objective objective = new KillObjective("Kill a beast hollow", "Kill one beast hollow", 1, HOLLOW_CHECK);

    public KillSpecificHollowQuest()
    {
        this.setTitle("On the hunt for a specific hollow");
        this.setDescription("You have been tasked to track down a specific hollow");
        this.addObjective(objective);
        this.setQuestReward(questReward);
    }
}
