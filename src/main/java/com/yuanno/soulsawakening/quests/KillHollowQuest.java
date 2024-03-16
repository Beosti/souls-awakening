package com.yuanno.soulsawakening.quests;

import com.yuanno.soulsawakening.entities.hollow.HollowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.Arrays;

public class KillHollowQuest extends Quest {

    QuestReward questReward = QuestReward.builder()
            .itemReward(new ArrayList<>(Arrays.asList(new ItemStack(Items.ENCHANTED_GOLDEN_APPLE))))
            .build();
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
    }
}
