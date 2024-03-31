package com.yuanno.soulsawakening.events.ability;

import com.yuanno.soulsawakening.ability.api.Ability;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

// custom event used server side when ability is used
public class AbilityUseEvent extends Event {
    private PlayerEntity player;
    private Ability ability;

    public AbilityUseEvent(PlayerEntity player, Ability ability)
    {
        this.player = player;
        this.ability = ability;
    }

    /**
     * This is called when the ability does all the checks if it can fire or be used or not
     */
    public static class Pre extends AbilityUseEvent
    {
        public Pre(PlayerEntity player, Ability ability)
        {
            super(player, ability);
        }
    }
    /**
     * This is called when the ability does everything it has to do
     * Note: for the continuous abilities it is the starting and the continuous state in this event
     */
    public static class Per extends AbilityUseEvent
    {
        public Per(PlayerEntity player, Ability ability)
        {
            super(player, ability);
        }
    }

    /**
     * This is called when the ability does everything it had to do, it did that
     * In here the ability goes in cooldown etc.
     */
    public static class Post extends AbilityUseEvent
    {
        public Post(PlayerEntity player, Ability ability)
        {
            super(player, ability);
        }
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
