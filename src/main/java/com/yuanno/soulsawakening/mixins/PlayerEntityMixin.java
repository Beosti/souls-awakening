package com.yuanno.soulsawakening.mixins;

import com.google.common.base.Strings;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.AttributeHelper;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.EntityDamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(value = PlayerEntity.class, priority = 990)
public abstract class PlayerEntityMixin
{		
	@ModifyConstant(method = "attack(Lnet/minecraft/entity/Entity;)V", constant = @Constant(doubleValue = 9.0))
	private double getActualAttackRange(final double attackRange)
	{
		PlayerEntity player = ((PlayerEntity) (Object) this);
		return AttributeHelper.getSquaredAttackRangeDistance(player, attackRange);
	}

	/*
	@Inject(method = "getDimensions", at = @At("HEAD"), cancellable = true)
	public void getSize(Pose pose, CallbackInfoReturnable<EntitySize> callback)
	{
		PlayerEntity player = ((PlayerEntity) (Object) this);
		IDevilFruit props = DevilFruitCapability.get(player);
		if (!Strings.isNullOrEmpty(props.getZoanPoint()))
		{
			MorphInfo info = MorphHelper.getZoanInfo(player);
			if(info == null)
				return;
			Map<Pose, EntitySize> poses = info.getSizes();
			if(poses != null && poses.containsKey(player.getPose()) && poses.get(player.getPose()) != null)
				callback.setReturnValue(poses.get(player.getPose()));
		}
	}
	*/
	@Inject(
		method = "attack", 
		at = @At(
			value = "INVOKE", 
			target = "Lnet/minecraft/entity/player/PlayerEntity;sweepAttack()V",
			shift = At.Shift.AFTER
		)
	)
	public void attackTargetEntityWithCurrentItem(Entity targetEntity, CallbackInfo callback)
	{
		PlayerEntity player = ((PlayerEntity) (Object) this);
		targetEntity.invulnerableTime = 0;
		targetEntity.hurt(new EntityDamageSource("sweep_damage", player), 0f);
	}


}
