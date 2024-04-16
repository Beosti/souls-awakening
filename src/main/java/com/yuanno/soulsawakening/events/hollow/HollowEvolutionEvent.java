package com.yuanno.soulsawakening.events.hollow;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

/**
 * Event when a hollow evolves (could add more ways to evolve later)
 * Can hook things on it if it evolves a certain way or for API reasons
 */
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
