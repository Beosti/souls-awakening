package com.yuanno.soulsawakening.abilities.elements.lunar;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IReiatsuAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IParticleEffect;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IWaveAbility;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.WaveParticleEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class LunarWaveAbility extends Ability implements IRightClickAbility, IWaveAbility, IParticleEffect, IReiatsuAbility {
    public static final LunarWaveAbility INSTANCE = new LunarWaveAbility();
    public static final ParticleEffect PARTICLES_WAVE = new WaveParticleEffect(1.4);

    public LunarWaveAbility()
    {
        this.setName("Lunar Wave");
        this.setMaxCooldown(15);
        this.setState(STATE.READY);
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public int getRadius()
    {
        return 10;
    }
    @Override
    public ParticleEffect getSpawnParticles()
    {
        return PARTICLES_WAVE;
    }
    @Override
    public ParticleType getParticleType()
    {
        return ModParticleTypes.LUNAR.get();
    }

    @Override
    public EffectInstance applyEffect()
    {
        return new EffectInstance(Effects.BLINDNESS, 120, 0);
    }

    @Override
    public boolean getShift()
    {
        return true;
    }

    @Override
    public float addedVariable(PlayerEntity player) {
        return (float) (EntityStatsCapability.get(player).getReiatsuPoints()/4);
    }
}
