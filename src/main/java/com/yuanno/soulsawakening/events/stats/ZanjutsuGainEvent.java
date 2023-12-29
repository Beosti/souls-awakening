package com.yuanno.soulsawakening.events.stats;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class ZanjutsuGainEvent extends Event {
    private PlayerEntity player;
    private double amount;
    private boolean exactAmount;
    public ZanjutsuGainEvent(PlayerEntity player)
    {
        this.player = player;
        this.amount = 0.05;
        this.exactAmount = false;
    }
    public ZanjutsuGainEvent(PlayerEntity player, double amount)
    {
        this.player = player;
        this.amount = amount;
        this.exactAmount = false;
    }
    public ZanjutsuGainEvent(PlayerEntity player, double amount, boolean exactAmount)
    {
        this.player = player;
        this.amount = amount;
        this.exactAmount = false;
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
