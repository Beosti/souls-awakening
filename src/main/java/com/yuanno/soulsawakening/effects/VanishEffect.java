package com.yuanno.soulsawakening.effects;

import com.yuanno.soulsawakening.api.IVanishEffect;
import com.yuanno.soulsawakening.api.ModEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;

public class VanishEffect extends ModEffect implements IVanishEffect {
	public VanishEffect() {
		super(EffectType.BENEFICIAL, 8356754);
	}
	
	@Override
	public boolean shouldUpdateClient() {
		return true;
	}

	@Override
	public boolean isVanished(LivingEntity entity, int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean shouldRenderHUD(EffectInstance effect)
	{
		return false;
	}

	@Override
	public boolean disableParticles() {
		return true;
	}
}
