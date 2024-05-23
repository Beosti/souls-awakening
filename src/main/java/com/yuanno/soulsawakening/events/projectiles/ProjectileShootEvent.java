package com.yuanno.soulsawakening.events.projectiles;

import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class ProjectileShootEvent extends Event
{
	private AbilityProjectileEntity projectile;
	private float velocity;
	private float inaccuracy;
	private LivingEntity owner;


	public ProjectileShootEvent(AbilityProjectileEntity abilityProjectileEntity, float velocity, float inaccuracy, LivingEntity owner) {
		this.projectile = abilityProjectileEntity;
		this.velocity = velocity;
		this.inaccuracy = inaccuracy;
		this.owner = owner;
	}

	public AbilityProjectileEntity getProjectile()
	{
		return this.projectile;
	}

	public float getVelocity()
	{
		return this.velocity;
	}
	
	public float getInaccuracy()
	{
		return this.inaccuracy;
	}

	public LivingEntity getOwner()
	{
		return this.owner;
	}
}
