package com.yuanno.soulsawakening.events;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

/**
 * Server side event that is called when the player 'rescues' anything, mainly used for Objective events
 */
public class RescueEvent extends Event {
    private PlayerEntity player;
    private LivingEntity livingEntity;
    public RescueEvent(PlayerEntity player)
    {
        this.player = player;
    }
    public RescueEvent(PlayerEntity player, LivingEntity livingEntity)
    {
        this.player = player;
        this.livingEntity = livingEntity;
    }

    public PlayerEntity getPlayer() {
        return this.player;
    }
    public LivingEntity getRescued()
    {
        return this.livingEntity;
    }
}
