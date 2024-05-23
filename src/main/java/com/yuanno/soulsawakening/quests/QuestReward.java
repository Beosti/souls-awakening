package com.yuanno.soulsawakening.quests;

import com.yuanno.soulsawakening.api.challenges.ChallengeReward;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.io.Serializable;
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
    private List<Supplier<String>> itemChanges = new ArrayList<>();
    protected IComplete onCompleteEvent = (player) -> {return true;};
    private QuestReward(Builder builder)
    {
        this.itemRewards = builder.itemRewards;
        this.onCompleteEvent = builder.onCompleteEvent;
    }

    public void giveReward(PlayerEntity player)
    {
        if (player.level.isClientSide)
            return;
        for (ItemStack supp : this.itemRewards) {
            ItemStack stack = supp.copy();
            player.addItem(stack);
        }
        this.onCompleteEvent.check(player);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<ItemStack> itemRewards = new ArrayList<>();
        protected IComplete onCompleteEvent = (player) -> {return true;};
        public Builder itemReward(List<ItemStack> itemRewards)
        {
            this.itemRewards = itemRewards;
            return this;
        }

        public Builder otherReward(IComplete complete)
        {
            this.onCompleteEvent = complete;
            return this;
        }


        public QuestReward build()
        {
            return new QuestReward(this);
        }
    }

    public interface IComplete extends Serializable
    {
        boolean check(PlayerEntity player);
    }
}
