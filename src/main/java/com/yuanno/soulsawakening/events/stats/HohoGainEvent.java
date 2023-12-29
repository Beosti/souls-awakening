package com.yuanno.soulsawakening.events.stats;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class HohoGainEvent extends Event {

    private PlayerEntity player;
    private double amount;
    public HohoGainEvent(PlayerEntity player)
    {
        this.player = player;
        this.amount = 1;
    }
    public HohoGainEvent(PlayerEntity player, double amount)
    {
        this.player = player;
        this.amount = amount;
    }

    public PlayerEntity getPlayer()
    {
        return this.player;
    }
    public double getAmount()
    {
        return this.amount;
    }
}
