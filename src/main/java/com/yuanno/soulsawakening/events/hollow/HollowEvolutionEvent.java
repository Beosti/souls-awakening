package com.yuanno.soulsawakening.events.hollow;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class HollowEvolutionEvent extends Event {

    private PlayerEntity player;

    public HollowEvolutionEvent(PlayerEntity player)
    {
        this.player = player;
    }

    public PlayerEntity getPlayer()
    {
        return this.player;
    }
}
