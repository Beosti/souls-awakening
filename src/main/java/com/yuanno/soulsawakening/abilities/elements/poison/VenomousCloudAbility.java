package com.yuanno.soulsawakening.abilities.elements.poison;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IParticleEffect;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IWaveAbility;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.api.SourceElement;
import com.yuanno.soulsawakening.api.SourceType;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModDamageSource;
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

public class VenomousCloudAbility extends Ability implements IRightClickAbility, IWaveAbility, IParticleEffect {

    public static final VenomousCloudAbility INSTANCE = new VenomousCloudAbility();
    private final static DamageSource POISON_DAMAGE = new ModDamageSource("venomous_cloud").setSourceTypes(SourceType.SHOCKWAVE).setSourceElement(SourceElement.POISON);
    public static final ParticleEffect PARTICLES_POISON = new WaveParticleEffect(1.2);

    public VenomousCloudAbility()
    {
        this.setName("Venomous Cloud");
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
    public DamageSource getDamageSource()
    {
        return POISON_DAMAGE;
    }

    @Override
    public float getDamage()
    {
        return 5;
    }

    @Override
    public void applyEffect(LivingEntity target)
    {
        target.addEffect(new EffectInstance(Effects.POISON, 120, 0));
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
