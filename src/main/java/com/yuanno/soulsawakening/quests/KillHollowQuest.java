package com.yuanno.soulsawakening.quests;

import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.entities.hollow.HollowEntity;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CGiveItemStackPacket;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.player.PlayerEntity;
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
        entityStats.setHakudaPoints(0);
        entityStats.setHohoPoints(0);
        entityStats.setZanjutsuPoints(0);
        entityStats.setReiatsuPoints(0);
        entityStats.setClassPoints(0);
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
