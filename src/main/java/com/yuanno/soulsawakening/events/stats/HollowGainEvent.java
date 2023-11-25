package com.yuanno.soulsawakening.events.stats;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class HollowGainEvent extends Event {
    private PlayerEntity player;

    public HollowGainEvent(PlayerEntity player)
    {
        this.player = player;
    }

    public PlayerEntity getPlayer()
    {
        return this.player;
    }

}
