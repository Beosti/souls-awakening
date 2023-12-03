package com.yuanno.soulsawakening.events.ability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraftforge.eventbus.api.Event;

// custom event used server side when right clicking with empty hand
public class RightClickEmptyEvent extends Event {
    private PlayerEntity player;

    public RightClickEmptyEvent(PlayerEntity player)
    {
        this.player = player;
    }

    public PlayerEntity getPlayer()
    {
        return this.player;
    }

}
