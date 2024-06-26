package com.yuanno.soulsawakening.abilities.elements.poison;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.api.SourceElement;
import com.yuanno.soulsawakening.api.SourceType;
import com.yuanno.soulsawakening.init.ModDamageSource;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.WaveParticleEffect;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;


public class VenomousCloudAbility extends Ability implements IRightClickAbility, IWaveAbility, IParticleEffect {

    public static final VenomousCloudAbility INSTANCE = new VenomousCloudAbility();
    private final static DamageSource POISON_DAMAGE = new ModDamageSource("venomous_cloud").setSourceTypes(SourceType.SHOCKWAVE).setSourceElement(SourceElement.POISON);
    public static final ParticleEffect PARTICLES_POISON = new WaveParticleEffect(1.2);

    public VenomousCloudAbility()
    {
        this.setName("Venomous Cloud");
        this.setMaxCooldown(15);
        this.setSubCategory(SubCategory.SHIKAI);
        this.dependency = AbilityDependencies::shikaiDependance;
    }

    @Override
    public int getRadius()
    {
        return 10;
    }

    @Override
    public DamageSource getDamageSource()
    {
        return POISON_DAMAGE;
    }

    @Override
    public float getBaseDamage()
    {
        return 5;
    }

    @Override
    public EffectInstance applyEffect()
    {
        return new EffectInstance(Effects.POISON, 120, 0);
    }

    @Override
    public ParticleEffect getSpawnParticles()
    {
        return PARTICLES_POISON;
    }

    @Override
    public ParticleType getParticleType()
    {
        return ModParticleTypes.POISON.get();
    }
}
