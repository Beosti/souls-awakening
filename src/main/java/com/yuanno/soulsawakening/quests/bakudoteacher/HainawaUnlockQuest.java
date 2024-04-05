package com.yuanno.soulsawakening.quests.bakudoteacher;

import com.yuanno.soulsawakening.abilities.kido.bakudo.HainawaAbility;
import com.yuanno.soulsawakening.abilities.kido.bakudo.SaiAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import com.yuanno.soulsawakening.quests.Objective;
import com.yuanno.soulsawakening.quests.Quest;
import com.yuanno.soulsawakening.quests.QuestReward;
import com.yuanno.soulsawakening.quests.objectives.UseAbilityObjective;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;

public class HainawaUnlockQuest extends Quest {

    QuestReward questReward = QuestReward.builder()
            .otherReward(this::reward)
            .build();

    public boolean reward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addUnlockedAbility(HainawaAbility.INSTANCE);
        abilityData.addAbilityToBar(HainawaAbility.INSTANCE);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        return true;
    }

    public static final UseAbilityObjective.ICheckAbility CHECK_ABILITY = (((player, target) -> {
        if (!(target instanceof MobEntity))
            return false;
        MobEntity targetMod = (MobEntity) target;
        System.out.println("CHECK");
        return targetMod.isLeashed();
    }));

    private Objective objective = new UseAbilityObjective("Sai on entity on leash", "Use sai 5 times, on an entity on a leash", 5, SaiAbility.INSTANCE, CHECK_ABILITY);

    public HainawaUnlockQuest()
    {
        this.setTitle("Get lasso'd ol' entity");
        this.setDescription("Need to use sai 5 times on an entity on a leash");
        this.addObjective(objective);
        this.setQuestReward(questReward);
    }
}
