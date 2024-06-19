package com.yuanno.soulsawakening.abilities.elements.water;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.SourceElement;
import com.yuanno.soulsawakening.api.SourceType;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.IParticleEffect;
import com.yuanno.soulsawakening.api.ability.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IWaveAbility;
import com.yuanno.soulsawakening.init.ModDamageSource;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.WaveParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.DamageSource;

public class WaterSurgeAbility extends Ability implements IRightClickAbility, IWaveAbility, IParticleEffect {
    public static final WaterSurgeAbility INSTANCE = new WaterSurgeAbility();
    private final static DamageSource WATER_DAMAGE = new ModDamageSource("water_surge").setSourceTypes(SourceType.SHOCKWAVE).setSourceElement(SourceElement.WATER);
    public static final ParticleEffect PARTICLES_WATER = new WaveParticleEffect(0.8);

    public WaterSurgeAbility()
    {
        this.setName("Water Surge");
        this.setDescription("Pushes away enemies around you and deals damage");
        this.setMaxCooldown(20);
        this.dependency = AbilityDependencies::shikaiDependance;
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public ParticleEffect getSpawnParticles()
    {
        return PARTICLES_WATER;
    }

    @Override
    public ParticleType getParticleType()
    {
        return ModParticleTypes.WATER.get();
    }

    @Override
    public DamageSource getDamageSource() {
        return WATER_DAMAGE;
    }

    @Override
    public float getBaseDamage() {
        return 4;
    }

    @Override
    public void doOnWave(LivingEntity target) {
        target.knockback(2, 2, 2);
    }

    @Override
    public int getRadius() {
        return 16;
    }

    @Override
    public boolean getShift() {
        return true;
    }
}
