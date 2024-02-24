package com.yuanno.soulsawakening.entity.goal;

import com.yuanno.soulsawakening.init.ModAttributes;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;

public class ImprovedMeleeAttackGoal extends MeleeAttackGoal
{
	public ImprovedMeleeAttackGoal(CreatureEntity entity, double speed, boolean useLongMemory)
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
	
	@Override
	protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr)
	{
		double reach = this.mob.getBbWidth() * 2.0F * this.mob.getBbWidth() * 2.0F + enemy.getBbWidth();
		double d0 = reach + this.mob.getAttribute(ModAttributes.ATTACK_RANGE.get()).getValue() + 0.3;
		if (distToEnemySqr <= d0 && this.getTicksUntilNextAttack() <= 0)
		{
			this.resetAttackCooldown();
			this.mob.swing(Hand.MAIN_HAND);
			this.mob.doHurtTarget(enemy);
		}
	}
}
