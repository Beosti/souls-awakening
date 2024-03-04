package com.yuanno.soulsawakening.events.ability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class CustomInteractionEvent extends Event {

    private PlayerEntity player;
    public CustomInteractionEvent(PlayerEntity player)
    {
        this.player = player;

    }


    public PlayerEntity getPlayer()
    {
        return this.player;
    }

}
