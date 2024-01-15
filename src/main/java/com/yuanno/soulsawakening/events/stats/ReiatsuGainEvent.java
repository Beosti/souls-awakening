package com.yuanno.soulsawakening.events.stats;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class ReiatsuGainEvent extends Event {
    private PlayerEntity player;
    private double amount;
    private boolean exactAmount;

    public ReiatsuGainEvent(PlayerEntity player)
    {
        this.player = player;
        this.amount = 1;
    }
    public ReiatsuGainEvent(PlayerEntity player, double amount)
    {
        this.player = player;
        this.amount = amount;
    }
    public ReiatsuGainEvent(PlayerEntity player, double amount, boolean exactAmount)
    {
        this.player = player;
        this.amount = amount;
        this.exactAmount = exactAmount;
    }
    public PlayerEntity getPlayer()
    {
        return this.player;
    }
    public double getAmount()
    {
        return this.amount;
    }
    public boolean isExactAmount()
    {
        return this.exactAmount;
    }
}
