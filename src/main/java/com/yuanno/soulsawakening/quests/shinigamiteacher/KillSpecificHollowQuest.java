package com.yuanno.soulsawakening.quests.shinigamiteacher;

import com.yuanno.soulsawakening.abilities.SoulSocietyKeyAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.misc.IMiscData;
import com.yuanno.soulsawakening.data.misc.MiscDataCapability;
import com.yuanno.soulsawakening.data.teleports.ITeleports;
import com.yuanno.soulsawakening.data.teleports.TeleportCapability;
import com.yuanno.soulsawakening.entities.hollow.BeastEntity;
import com.yuanno.soulsawakening.entities.hollow.HollowEntity;
import com.yuanno.soulsawakening.init.ModAdvancements;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CGiveItemStackPacket;
import com.yuanno.soulsawakening.networking.client.CSyncAbilityDataPacket;
import com.yuanno.soulsawakening.networking.client.CSyncTeleportPacket;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import com.yuanno.soulsawakening.networking.server.SSyncMiscDataPacket;
import com.yuanno.soulsawakening.quests.Objective;
import com.yuanno.soulsawakening.quests.Quest;
import com.yuanno.soulsawakening.quests.QuestReward;
import com.yuanno.soulsawakening.quests.QuestStart;
import com.yuanno.soulsawakening.quests.objectives.KillObjective;
import com.yuanno.soulsawakening.teleport.TeleportPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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
       return target instanceof BeastEntity;
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
