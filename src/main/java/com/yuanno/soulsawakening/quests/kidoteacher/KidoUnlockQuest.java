package com.yuanno.soulsawakening.quests.kidoteacher;

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

public class KidoUnlockQuest extends Quest {

    QuestReward questReward = QuestReward.builder()
            .otherReward(this::reward)
            .build();

    public boolean reward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addAbilityToBar(ShoAbility.INSTANCE);
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
        abilityData.addUnlockedAbility(ShoAbility.INSTANCE);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        ITeleports teleports = TeleportCapability.get(player);
        TeleportPosition teleportPosition = new TeleportPosition();
        teleportPosition.setBlockPos(player.blockPosition());
        teleportPosition.setName("Kido and Hado teacher");
        teleportPosition.setDimension(Minecraft.getInstance().level.dimension().toString());
        teleports.addTeleportsPosition(teleportPosition);
        PacketHandler.sendTo(new SSyncTeleportPacket(player.getId(), teleports), player);
        return true;
    }
    private UseAbilityObjective objective = new UseAbilityObjective("Use Sho", "Use incantation 10 times: Push back, repel the vile knave! Hadou number 1 Sho", 10, ShoAbility.INSTANCE);

    public KidoUnlockQuest()
    {
        this.setTitle("Unlocking Kido");
        this.setDescription("Use sho 10 times with the incantation: Push back, repel the vile knave! Hadou number 1 Sho");
        this.addObjective(objective);
        this.setQuestReward(questReward);
        this.setQuestStart(questStart);
    }
}
