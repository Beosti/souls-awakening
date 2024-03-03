package com.yuanno.soulsawakening.abilities.elements.lunar;

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
import net.minecraft.util.DamageSource;

import java.util.List;

public class LunarWaveAbility extends Ability implements IRightClickAbility, IWaveAbility, IParticleEffect {
    public static final LunarWaveAbility INSTANCE = new LunarWaveAbility();
    public static final ParticleEffect PARTICLES_WAVE = new WaveParticleEffect(1.4);

    public LunarWaveAbility()
    {
        this.setName("Lunar Wave");
        this.setCooldown(15);
        this.setMaxCooldown(15);
        this.setActivationType(ActivationType.SHIFT_RIGHT_CLICK);
        this.setState(STATE.READY);
        this.setPassive(false);
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
    public void applyEffect(LivingEntity target)
    {
        target.addEffect(new EffectInstance(Effects.BLINDNESS, 120, 0));
    }

    @Override
    public boolean getShift()
    {
        return true;
    }
}
