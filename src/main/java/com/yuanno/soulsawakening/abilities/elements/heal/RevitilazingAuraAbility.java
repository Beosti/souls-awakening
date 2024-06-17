package com.yuanno.soulsawakening.abilities.elements.heal;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.WaveParticleEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class RevitilazingAuraAbility extends Ability implements IRightClickAbility, IWaveAbility, IParticleEffect, IReiatsuAbility {
    public static final RevitilazingAuraAbility INSTANCE = new RevitilazingAuraAbility();
    public static final ParticleEffect PARTICLES_WAVE = new WaveParticleEffect(1.4);

    public RevitilazingAuraAbility()
    {
        this.setName("Revitilazing Aura");
        this.setMaxCooldown(20);
        this.setSubCategory(SubCategory.SHIKAI);
        this.dependency = AbilityDependencies::shikaiDependance;
    }

    @Override
    public float healAmount() {
        return 4;
    }

    @Override
    public float addedVariable(PlayerEntity player) {
        return (float) EntityStatsCapability.get(player).getReiatsuPoints()/4;
    }

    @Override
    public int getRadius() {
        return 10;
    }
    @Override
    public EffectInstance applyEffect() {
        return new EffectInstance(Effects.REGENERATION, 240, 0);
    }
    @Override
    public ParticleEffect getSpawnParticles() {
        return PARTICLES_WAVE;
    }
    @Override
    public ParticleType getParticleType() {
        return ModParticleTypes.HEALING.get();
    }

    @Override
    public boolean getShift()
    {
        return true;
    }
}
