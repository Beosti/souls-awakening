package com.yuanno.soulsawakening.api;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;

public class AbilityDamageSource extends ModEntityDamageSource
{
	private Ability ability;

	private boolean isProjectileSource = false;
	
	private LivingEntity thrower;
		
	public AbilityDamageSource(String damageType, Entity source, Ability ability) {
		super(damageType, source);
		
		this.ability = ability;
		

	}
	
	public AbilityDamageSource(String damageType, AbilityProjectileEntity source, Ability ability) {
		super(damageType, source);
		
		this.ability = ability;
		

		
		this.isProjectileSource = true;
		this.thrower = source.getThrower();
	}
	
	public Ability getAbilitySource() {
		return this.ability;
	}

	@Override
	@Nullable
	public Entity getDirectEntity() {
		return this.entity;
	}

	/**
	 * Retrieves the true causer of the damage, e.g. the player who fired an arrow, the shulker who fired the bullet,
	 * etc.
	 */
	@Override
	@Nullable
	public Entity getEntity() {
		if (this.isProjectileSource && this.thrower != null) {
			return this.thrower;
		}
		
		return super.getEntity();
	}

	@Override
	public ITextComponent getLocalizedDeathMessage(LivingEntity entityLivingBaseIn) {
		String s = "death.attack." + this.msgId;
		
		return new TranslationTextComponent(s, entityLivingBaseIn.getDisplayName(), this.getEntity().getDisplayName(), this.ability.getName());
	}

	@Override
	public String toString() {
		return "AbilityDamageSource (" + this.msgId + ") from ability: " + this.ability.getName();
	}
}
