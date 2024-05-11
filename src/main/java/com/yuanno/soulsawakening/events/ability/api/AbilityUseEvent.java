package com.yuanno.soulsawakening.events.ability.api;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

/**
 * Custom event that handles everything when an ability effectively HAPPENS
 */
public class
AbilityUseEvent extends Event {
    private PlayerEntity player;
    private Ability ability;
    public AbilityUseEvent(PlayerEntity player, Ability ability)
    {
        this.player = player;
        this.ability = ability;
    }
    public AbilityUseEvent(PlayerEntity player)
    {
        this.player = player;
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
        private LivingEntity target;
        private AbilityProjectileEntity projectile;
        private float power;
        public Per(PlayerEntity player, Ability ability)
        {
            super(player, ability);
        }
        public Per(PlayerEntity player, Ability ability, LivingEntity target)
        {
            super(player, ability);
            this.target = target;
        }
        public Per(PlayerEntity player, Ability ability, AbilityProjectileEntity projectile, float power)
        {
            super(player, ability);
            this.projectile = projectile;
            this.power = power;
        }
        public LivingEntity getTarget()
        {
            return this.target;
        }
        public AbilityProjectileEntity getProjectile()
        {
            return this.projectile;
        }
        public float getPower()
        {
            return this.power;
        }
    }

    /**
     * This is called when the ability does everything it had to do, it did that
     * In here the ability goes in cooldown etc.
     */
    public static class Post extends AbilityUseEvent
    {
        private LivingEntity target;
        public Post(PlayerEntity player, Ability ability)
        {
            super(player, ability);
        }
        public Post(PlayerEntity player, Ability ability, LivingEntity target)
        {
            super(player, ability);
            this.target = target;
        }
        public LivingEntity getTarget()
        {
            return this.target;
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
