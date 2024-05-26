package com.yuanno.soulsawakening.entity.goal;

import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.init.world.ModDimensions;
import com.yuanno.soulsawakening.util.GargantaTeleporter;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.potion.Effects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ImprovedMeleeAttackShinigamiGoal extends MeleeAttackGoal
{
	public ImprovedMeleeAttackShinigamiGoal(CreatureEntity entity, double speed, boolean useLongMemory)
	{
		super(entity, speed, useLongMemory);
	}

	@Override
	public boolean canContinueToUse()
	{
		if(!super.canContinueToUse())
			return false;
		
		LivingEntity target = this.mob.getTarget();

		boolean isInvisible = target != null && target.hasEffect(Effects.INVISIBILITY);
		return !isInvisible;
	}
	
	@Override
	public void tick()
	{
		LivingEntity target = this.mob.getTarget();
		boolean isInvisible = target != null && target.hasEffect(Effects.INVISIBILITY);
		
		if(target == null || !target.isAlive())
			return;
		
		if(isInvisible)
		{
			this.mob.setTarget((LivingEntity)null);
			return;
		}
		else
			super.tick();
	}
	private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

	@Override
	protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr)
	{
		double reach = this.mob.getBbWidth() * 2.0F * this.mob.getBbWidth() * 2.0F + enemy.getBbWidth();
		double d0 = reach + this.mob.getAttribute(ModAttributes.ATTACK_RANGE.get()).getValue() + 0.3;
		if (distToEnemySqr <= d0 && this.getTicksUntilNextAttack() <= 0)
		{
			IEntityStats entityStats = EntityStatsCapability.get(enemy);
			if (entityStats.getRace().equals(ModValues.SPIRIT))
			{
				World world = enemy.level;
				MinecraftServer minecraftServer = world.getServer();
				ServerWorld soulSociety = minecraftServer.getLevel(ModDimensions.SOUL_SOCIETY);
				if (soulSociety != null && enemy.level != soulSociety) {
					executor.schedule(() -> {
						enemy.changeDimension(soulSociety, new GargantaTeleporter(enemy.blockPosition(), false));
					}, 50, TimeUnit.MILLISECONDS);
				}
				return;
			}
			this.resetAttackCooldown();
			this.mob.swing(Hand.MAIN_HAND);
			this.mob.doHurtTarget(enemy);
		}
	}
}
