package com.yuanno.soulsawakening.quests;

import com.yuanno.soulsawakening.abilities.kido.hado.ShoAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import net.minecraft.entity.player.PlayerEntity;

public class KidoUnlockQuest extends Quest {

    QuestReward questReward = QuestReward.builder()
            .otherReward(this::reward)
            .build();

    public boolean reward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addUnlockedAbility(ShoAbility.INSTANCE);
        abilityData.setSelectedAbility(0);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        return true;
    }

    private UseAbilityObjective objective = new UseAbilityObjective("Use Sho", "Use incantation 10 times: Push back, repel the vile knave! Hadou number 1 Sho", 10, ShoAbility.INSTANCE);

    public KidoUnlockQuest()
    {
        this.setTitle("Unlocking Kido");
        this.setDescription("Use sho 10 times with the incantation: Push back, repel the vile knave! Hadou number 1 Sho");
        this.addObjective(objective);
        this.setQuestReward(questReward);
    }
}
