package com.yuanno.soulsawakening.quests.shinigamiteacher;

import com.yuanno.soulsawakening.abilities.SoulSocietyKeyAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.teleports.ITeleports;
import com.yuanno.soulsawakening.data.teleports.TeleportCapability;
import com.yuanno.soulsawakening.entities.hollow.HollowEntity;
import com.yuanno.soulsawakening.init.ModAdvancements;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModQuests;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.*;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import com.yuanno.soulsawakening.networking.server.SSyncTeleportPacket;
import com.yuanno.soulsawakening.quests.*;
import com.yuanno.soulsawakening.quests.objectives.KillObjective;
import com.yuanno.soulsawakening.teleport.TeleportPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class KillHollowQuest extends Quest {

    QuestReward questReward = QuestReward.builder()
            .otherReward(this::reward)
            .build();
    public boolean reward(PlayerEntity player)
    {
        for (int i = 0; i < player.inventory.getContainerSize(); i++)
        {
            if (player.inventory.getItem(i).getItem().asItem().equals(ModItems.ZANPAKUTO.get()))
            {
                player.inventory.getItem(i).getTag().putString("zanpakutoState", ModValues.STATE.SEALED.name());
            }
        }
        IEntityStats entityStats = EntityStatsCapability.get(player);
        entityStats.setRace(ModValues.SHINIGAMI);
        ModAdvancements.SHINIGAMI.trigger((ServerPlayerEntity) player);
        entityStats.getShinigamiStats().setHakudaPoints(0);
        entityStats.getShinigamiStats().setHohoPoints(0);
        entityStats.getShinigamiStats().setZanjutsuPoints(0);
        entityStats.setReiatsuPoints(0);
        entityStats.getShinigamiStats().setClassPoints(0);
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
        return true;
    }

    QuestStart questStart = QuestStart.builder()
            .otherStart(this::start)
            .build();

    public boolean start(PlayerEntity player)
    {
        Item item = ModItems.ZANPAKUTO.get();
        ItemStack itemStack = new ItemStack(item);
        itemStack.getOrCreateTag().putString("owner", player.getDisplayName().getString());
        itemStack.getOrCreateTag().putBoolean("soulbound", true);
        itemStack.getTag().putString("zanpakutoType", ModValues.TYPE.getRandomType().name());
        itemStack.getTag().putString("zanpakutoState", ModValues.STATE.ASAUCHI.name());
        PacketHandler.sendToServer(new CGiveItemStackPacket(itemStack));

        IAbilityData abilityData = AbilityDataCapability.get(player);
        ITeleports teleports = TeleportCapability.get(player);
        TeleportPosition teleportPosition = new TeleportPosition();
        teleportPosition.setName("Shinigami Teacher");
        teleportPosition.setBlockPos(player.blockPosition());
        teleportPosition.setDimension(Minecraft.getInstance().level.dimension().toString());
        teleports.addTeleportsPosition(teleportPosition);
        PacketHandler.sendTo(new SSyncTeleportPacket(player.getId(), teleports), player);
        abilityData.addUnlockedAbility(SoulSocietyKeyAbility.INSTANCE);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        return true;
    }

    public static final KillObjective.ICheckKill HOLLOW_CHECK = ((player, target, source) ->
    {
       return target instanceof HollowEntity;
    });

    private Objective objective = new KillObjective("Kill a hollow", "Kill one hollow", 1, HOLLOW_CHECK);

    public KillHollowQuest()
    {
        this.setTitle("Proving yourself");
        this.setDescription("You have been tasked to kill your first hollow");
        this.addObjective(objective);
        this.setQuestReward(questReward);
        this.setQuestStart(questStart);
    }
}
