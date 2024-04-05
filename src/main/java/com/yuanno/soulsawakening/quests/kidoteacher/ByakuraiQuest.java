package com.yuanno.soulsawakening.quests.kidoteacher;

import com.yuanno.soulsawakening.abilities.kido.hado.ByakuraiAbility;
import com.yuanno.soulsawakening.abilities.kido.hado.ShoAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import com.yuanno.soulsawakening.quests.Quest;
import com.yuanno.soulsawakening.quests.QuestReward;
import com.yuanno.soulsawakening.quests.QuestStart;
import com.yuanno.soulsawakening.quests.objectives.UseAbilityObjective;
import net.minecraft.entity.player.PlayerEntity;

public class ByakuraiQuest extends Quest {

    QuestReward questReward = QuestReward.builder()
            .otherReward(this::reward)
            .build();

    public boolean reward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addAbilityToBar(ByakuraiAbility.INSTANCE);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        return true;
    }

    QuestStart questStart = QuestStart.builder()
            .otherStart(this::start)
            .build();

    public boolean start(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addUnlockedAbility(ByakuraiAbility.INSTANCE);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        return true;
    }

    private UseAbilityObjective objectiveSho = new UseAbilityObjective("Use Sho Again", "Use the sho spell 10 times again", 10, ShoAbility.INSTANCE);
    private UseAbilityObjective objectiveByakurai = new UseAbilityObjective("Use Byakurai", "Use incantation 5 times: Oh ye, pale lightning may you smitten thy enemy as thy Hadou number 4 Byakurai", 5, ByakuraiAbility.INSTANCE);

    public ByakuraiQuest()
    {
        this.setTitle("Unlocking Byakurai");
        this.setDescription("Unlocking byakurai by using the incantation 5 times: 'Oh ye, pale lightning may you smitten thy enemy as the lightning smitten me! Hadou number 4 Byakurai' and using sho 10 times");
        this.addObjective(objectiveSho);
        this.addObjective(objectiveByakurai);
        this.setQuestReward(questReward);
        this.setQuestStart(questStart);
    }
}
