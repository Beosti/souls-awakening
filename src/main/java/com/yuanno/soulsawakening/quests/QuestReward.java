package com.yuanno.soulsawakening.quests;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Way easier to handle all quest rewards in one class instead of doing it separately and other ways
 *
 * Idea taken from Block Clover that I took and modified from Mine Mine no Mi
 */
public class QuestReward {


    private List<ItemStack> itemRewards = new ArrayList<>();

    private QuestReward(Builder builder)
    {
        this.itemRewards = builder.itemRewards;
    }

    public QuestReward addItem(ItemStack item)
    {
        this.itemRewards.add(item);
        return this;
    }

    public void giveReward(PlayerEntity player)
    {
        for (ItemStack supp : this.itemRewards) {
            ItemStack stack = supp.copy();
            player.addItem(stack);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<ItemStack> itemRewards = new ArrayList<>();

        public Builder itemReward(List<ItemStack> itemRewards)
        {
            this.itemRewards = itemRewards;
            return this;
        }

        public QuestReward build()
        {
            return new QuestReward(this);
        }
    }
}
