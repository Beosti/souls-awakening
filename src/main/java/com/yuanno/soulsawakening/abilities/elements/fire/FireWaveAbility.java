package com.yuanno.soulsawakening.abilities.elements.fire;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IReiatsuAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IParticleEffect;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IWaveAbility;
import com.yuanno.soulsawakening.api.SourceElement;
import com.yuanno.soulsawakening.api.SourceType;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.init.ModDamageSource;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.WaveParticleEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.DamageSource;

public class FireWaveAbility extends Ability implements IRightClickAbility, IWaveAbility, IParticleEffect, IReiatsuAbility {
    public static final FireWaveAbility INSTANCE = new FireWaveAbility();
    private final static DamageSource FIRE_DAMAGE = new ModDamageSource("fire_wave").setSourceTypes(SourceType.SHOCKWAVE).setSourceElement(SourceElement.FIRE);
    public static final ParticleEffect PARTICLES_FIRE = new WaveParticleEffect(1.6);

    public FireWaveAbility()
    {
        this.setName("Fire Wave");
        this.setDescription("Emit a wave of fire, damaging and putting enemies on fire");
        this.setMaxCooldown(15);
        this.setSubCategory(SubCategory.SHIKAI);
    }
    @Override
    public int getRadius()
    {
        return 10;
    }
    @Override
    public int putOnFire()
    {
        return 3;
    }
    @Override
    public DamageSource getDamageSource()
    {
        return FIRE_DAMAGE;
    }
    @Override
    public float getBaseDamage()
    {
        return 5;
    }
    @Override
    public ParticleEffect getSpawnParticles()
    {
        return PARTICLES_FIRE;
    }
    @Override
    public ParticleType getParticleType()
    {
        return ModParticleTypes.FIRE.get();
    }
    @Override
    public float addedVariable(PlayerEntity player) { return (float) EntityStatsCapability.get(player).getReiatsuPoints()/4; }
    @Override
    public boolean getShift()
    {
        return true;
    }
}
