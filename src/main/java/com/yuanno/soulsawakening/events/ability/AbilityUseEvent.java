package com.yuanno.soulsawakening.events.ability;

import com.yuanno.soulsawakening.ability.api.Ability;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

// custom event used server side when ability is used
public class AbilityUseEvent extends Event {
    private PlayerEntity player;
    private Ability ability;
    public AbilityUseEvent(PlayerEntity player)
    {
        this.player = player;
    }
    public AbilityUseEvent(PlayerEntity player, Ability ability)
    {
        this.player = player;
        this.ability = ability;
    }
    public PlayerEntity getPlayer()
    {
        return this.player;
    }
    public Ability getAbility()
    {
        return this.ability;
    }

}
