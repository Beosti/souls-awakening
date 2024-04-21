package com.yuanno.soulsawakening.entity.goal;

import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.entity.shinigami.ShinigamiStats;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;

import java.util.ArrayList;
import java.util.List;

public class DeflectProjectileGoal extends Goal
{
	private final LivingEntity entity;
	private final int range;
	private List<AbilityProjectileEntity> deflectProjectiles = new ArrayList<>();
	public DeflectProjectileGoal(CreatureEntity entity, int range)
	{
		this.entity = entity;
		this.range = range;
	}

	@Override
	public boolean canUse() {
		List<AbilityProjectileEntity> abilityProjectileEntities = Beapi.getNearbyEntities(entity.blockPosition(), entity.level, this.range, null, AbilityProjectileEntity.class);
		abilityProjectileEntities.forEach(projectile ->
		{
			if (projectile.getOwner() != null && projectile.getOwner().getEntity().equals(entity))
				return;
			float damage = projectile.getDamage();
			IEntityStats entityStats = EntityStatsCapability.get(entity);
			if (!entityStats.getRace().equals(ModValues.SHINIGAMI))
				return;
			ShinigamiStats shinigamiStats = entityStats.getShinigamiStats();
			int zanjutsuValue = (int) shinigamiStats.getZanjutsuPoints();
			System.out.println(damage);
			if (zanjutsuValue > damage)
				this.deflectProjectiles.add(projectile);
		});
		if (this.deflectProjectiles.isEmpty())
			return false;
		else
			return true;
	}


	@Override
	public boolean canContinueToUse()
	{
		return true;
	}
	
	@Override
	public void start()
	{
        for (AbilityProjectileEntity deflectProjectile : this.deflectProjectiles) {
            deflectProjectile.kill();
        }
	}
}
