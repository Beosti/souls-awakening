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

	/*
	public float getBonusDamage(float damage) {
		if (this.getEntity() == null || !(this.getEntity() instanceof LivingEntity)) {
			return damage;
		}
		
		LivingEntity attacker = (LivingEntity) this.getEntity();
		
		if (attacker == null || damage <= 0) {
			return 0;
		}
	
		boolean hasSlashBonus = (this.isSlash() || this.isBlunt()) && !attacker.getMainHandItem().isEmpty() && ItemsHelper.isSword(attacker.getMainHandItem());
		boolean hasFistBonus = this.isFistDamage() && attacker.getMainHandItem().isEmpty();
		
		// Such a filthy patch...but ye I dug my own grave by making brawlers use this ability while holding cannon balls...
		if(EntityStatsCapability.get(attacker).isBrawler() && attacker.getMainHandItem().getItem() == ModItems.CANNON_BALL.get() && this.getAbilitySource().equals(GenkotsuMeteorAbility.INSTANCE)) {
			hasFistBonus = true;
		}
		
		// NOTE Ability projectiles should NOT be affected by this unless specifically marked as such
		boolean hasSniperBonus = this.isProjectile() && !attacker.getMainHandItem().isEmpty() && ItemsHelper.isBow(attacker.getMainHandItem());
		
		if (hasSlashBonus) {
			damage += ItemsHelper.getItemDamage(attacker.getMainHandItem());
		} else if (hasFistBonus) {
			ModifiableAttributeInstance punchAttr = attacker.getAttribute(ModAttributes.PUNCH_DAMAGE.get());
			ModifiableAttributeInstance attackDamageAttr = attacker.getAttribute(Attributes.ATTACK_DAMAGE);
			
			if (punchAttr != null) {
				damage += punchAttr.getValue();
			}
			
			if (attackDamageAttr != null) {
				damage += attackDamageAttr.getValue();
			}
		}

		boolean hasHardeningActive = HakiHelper.hasHardeningActive(attacker, false, true);
		boolean hasImbuingActive = HakiHelper.hasImbuingActive(attacker, false, true);
		boolean hasHardeningBonus = this.getHakiNature() == SourceHakiNature.HARDENING && hasHardeningActive;
		boolean hasImbuingBonus = this.getHakiNature() == SourceHakiNature.IMBUING && hasImbuingActive;
		boolean hasSpecialBonus = this.getHakiNature() == SourceHakiNature.SPECIAL && (hasHardeningActive || hasImbuingActive);
		boolean hasInfusion = this.getHakiNature() != SourceHakiNature.UNKNOWN && HakiHelper.hasInfusionActive(attacker);

		if (hasHardeningBonus || hasImbuingBonus || hasSpecialBonus) {
			damage += HakiHelper.getHakiDamageBoost(attacker);
		}
		
		if (hasInfusion) {
			damage += HaoshokuHakiInfusionAbility.getDamageBoost(attacker);
		}

		return damage;
	}

	 */
}
