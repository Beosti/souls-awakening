package com.yuanno.soulsawakening.abilities.elements.heal;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IParticleEffect;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IWaveAbility;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.WaveParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.List;

public class RevitilazingAuraAbility extends Ability implements IRightClickAbility, IWaveAbility, IParticleEffect {
    public static final RevitilazingAuraAbility INSTANCE = new RevitilazingAuraAbility();
    public static final ParticleEffect PARTICLES_WAVE = new WaveParticleEffect(1.4);

    public RevitilazingAuraAbility()
    {
        this.setName("Revitilazing Aura");
        this.setCooldown(20);
        this.setMaxCooldown(20);
        this.setPassive(false);
        this.setActivationType(ActivationType.SHIFT_RIGHT_CLICK);
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public int getRadius() {
        return 10;
    }
    @Override
    public void applyEffect(LivingEntity target) {
        if (!target.hasEffect(Effects.REGENERATION))
            target.addEffect(new EffectInstance(Effects.REGENERATION, 240, 2));
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
