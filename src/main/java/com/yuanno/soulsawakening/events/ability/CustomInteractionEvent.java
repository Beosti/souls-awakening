package com.yuanno.soulsawakening.events.ability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class CustomInteractionEvent extends Event {

    private PlayerEntity player;
    private boolean interacted;
    private double distance;
    public CustomInteractionEvent(PlayerEntity player, boolean entityInteraction, double distance)
    {
        this.player = player;
        this.interacted = entityInteraction;
        this.distance = distance;
    }
    public CustomInteractionEvent(PlayerEntity player, boolean entityInteraction)
    {
        this.player = player;
        this.interacted = entityInteraction;
    }

    public CustomInteractionEvent(PlayerEntity player)
    {
        this.player = player;
        this.interacted = false;
    }


    public PlayerEntity getPlayer()
    {
        return this.player;
    }
    public boolean getInteracted()
    {
        return this.interacted;
    }
    public double getDistance()
    {
        return this.distance;
    }

}
