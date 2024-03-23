package com.yuanno.soulsawakening.quests;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Way easier to handle all quest starts in one class instead of doing it separately and other ways
 *
 * Idea taken from Block Clover that I took and modified from Mine Mine no Mi
 */
public class QuestStart {


    private List<ItemStack> itemRewards = new ArrayList<>();
    private List<Supplier<String>> itemChanges = new ArrayList<>();
    protected IComplete onCompleteEvent = (player) -> {return true;};
    private QuestStart(Builder builder)
    {
        this.itemRewards = builder.itemRewards;
        this.onCompleteEvent = builder.onCompleteEvent;
    }

    public void giveStart(PlayerEntity player)
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

        public Builder otherStart(IComplete complete)
        {
            this.onCompleteEvent = complete;
            return this;
        }


        public QuestStart build()
        {
            return new QuestStart(this);
        }
    }

    public interface IComplete extends Serializable
    {
        boolean check(PlayerEntity player);
    }
}
