package com.yuanno.soulsawakening.quests.bakudoteacher;

import com.yuanno.soulsawakening.abilities.kido.bakudo.SekiAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import com.yuanno.soulsawakening.quests.Objective;
import com.yuanno.soulsawakening.quests.Quest;
import com.yuanno.soulsawakening.quests.QuestReward;
import com.yuanno.soulsawakening.quests.objectives.GetHitObjective;
import net.minecraft.entity.player.PlayerEntity;

public class SekiUnlockQuest extends Quest {

    QuestReward questReward = QuestReward.builder()
            .otherReward(this::reward)
            .build();

    public boolean reward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addUnlockedAbility(SekiAbility.INSTANCE);
        abilityData.addAbilityToBar(SekiAbility.INSTANCE);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        return true;
    }

    public static final GetHitObjective.IGetHit CHECK_HIT = ((player, damageSource) -> {
        if (damageSource.isProjectile())
            return false;
        return player.isBlocking();
    });

    private Objective objective = new GetHitObjective("Use a shield", "Defend yourself 10 times from a physical attack with a shield", 10, CHECK_HIT);

    public SekiUnlockQuest()
    {
        this.setTitle("Get knocked back, scrub");
        this.setDescription("Unlock the seki ability by knocking back 10 entities by protecting yourself with a shield");
        this.addObjective(objective);
        this.setQuestReward(questReward);
    }
}
