package com.yuanno.soulsawakening.events.stats;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class HollowGainEvent extends Event {
    private PlayerEntity player;
    private int amount;
    private boolean exactAmount;
    public HollowGainEvent(PlayerEntity player)
    {
        this.player = player;
        this.amount = 1;
        this.exactAmount = false;
    }
    public HollowGainEvent(PlayerEntity player, int amount)
    {
        this.player = player;
        this.amount = amount;
        this.exactAmount = false;
    }
    public HollowGainEvent(PlayerEntity player, int amount, boolean exactAmount)
    {
        this.player = player;
        this.amount = amount;
        this.exactAmount = exactAmount;
    }


    public PlayerEntity getPlayer()
    {
        return this.player;
    }
    public int getAmount()
    {
        return this.amount;
    }
    public boolean isExactAmount()
    {
        return this.exactAmount;
    }

}
