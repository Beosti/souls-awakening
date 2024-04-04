package com.yuanno.soulsawakening.quests.bakudoteacher;

import com.yuanno.soulsawakening.abilities.kido.bakudo.SaiAbility;
import com.yuanno.soulsawakening.abilities.kido.hado.ShoAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.teleports.ITeleports;
import com.yuanno.soulsawakening.data.teleports.TeleportCapability;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import com.yuanno.soulsawakening.networking.server.SSyncTeleportPacket;
import com.yuanno.soulsawakening.quests.Quest;
import com.yuanno.soulsawakening.quests.QuestReward;
import com.yuanno.soulsawakening.quests.QuestStart;
import com.yuanno.soulsawakening.quests.UseAbilityObjective;
import com.yuanno.soulsawakening.teleport.TeleportPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;

public class BakudoUnlockQuest extends Quest {

    QuestReward questReward = QuestReward.builder()
            .otherReward(this::reward)
            .build();

    public boolean reward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addAbilityToBar(SaiAbility.INSTANCE);
        abilityData.setSelectedAbility(0);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        return true;
    }

    QuestStart questStart = QuestStart.builder()
            .otherStart(this::start)
            .build();

    public boolean start(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addUnlockedAbility(SaiAbility.INSTANCE);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        ITeleports teleports = TeleportCapability.get(player);
        TeleportPosition teleportPosition = new TeleportPosition();
        teleportPosition.setBlockPos(player.blockPosition());
        teleportPosition.setName("Bakudo teacher");
        teleportPosition.setDimension(Minecraft.getInstance().level.dimension().toString());
        teleports.addTeleportsPosition(teleportPosition);
        PacketHandler.sendTo(new SSyncTeleportPacket(player.getId(), teleports), player);
        return true;
    }
    private UseAbilityObjective objective = new UseAbilityObjective("Use Sai", "Use incantation 5 times: Ye thy stop moving Bakudo number 1 Sai", 5, SaiAbility.INSTANCE);

    public BakudoUnlockQuest()
    {
        this.setTitle("Unlocking Bakudo");
        this.setDescription("Use sai 5 times with the incantation: Ye thy stop moving Bakudo number 1 Sai");
        this.addObjective(objective);
        this.setQuestReward(questReward);
        this.setQuestStart(questStart);
    }
}
