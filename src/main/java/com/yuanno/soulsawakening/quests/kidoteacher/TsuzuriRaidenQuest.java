package com.yuanno.soulsawakening.quests.kidoteacher;

import com.yuanno.soulsawakening.abilities.kido.hado.TsuzuriRaidenAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.init.ModTags;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import com.yuanno.soulsawakening.quests.Quest;
import com.yuanno.soulsawakening.quests.QuestReward;
import com.yuanno.soulsawakening.quests.objectives.KillObjective;
import net.minecraft.entity.player.PlayerEntity;

public class TsuzuriRaidenQuest extends Quest {

    QuestReward questReward = QuestReward.builder()
            .otherReward(this::reward)
            .build();
    public boolean reward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addUnlockedAbility(TsuzuriRaidenAbility.INSTANCE);
        abilityData.addAbilityToBar(TsuzuriRaidenAbility.INSTANCE);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        return true;
    }
    public static final KillObjective.ICheckKill METAL_CHECK = ((player, target, source) ->
    {
        return player.getMainHandItem().getItem().is(ModTags.Items.CONDUCTOR);
    });
    private KillObjective killObjective = new KillObjective("Kill 10 entities with a conductor", "Killing with metal", 10, METAL_CHECK);

    public TsuzuriRaidenQuest()
    {
        this.setTitle("Tsuzuri Raiden");
        this.setDescription("Envelops your hand and item in lightning");
        this.addObjective(killObjective);
        this.setQuestReward(questReward);
    }
}
