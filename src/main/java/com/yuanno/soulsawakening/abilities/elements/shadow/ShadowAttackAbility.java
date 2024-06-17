package com.yuanno.soulsawakening.abilities.elements.shadow;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.HoveringParticleEffect;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class ShadowAttackAbility extends Ability implements IAttackAbility, IParticleEffect {
    public static final ShadowAttackAbility INSTANCE = new ShadowAttackAbility();
    public static final ParticleEffect PARTICLES_HOVER = new HoveringParticleEffect(1, 2);


    public ShadowAttackAbility() {
        this.setName("Shadow Attack");
        this.setSubCategory(SubCategory.SHIKAI);
        this.dependency = AbilityDependencies::shikaiDependance;
    }

    @Override
    public EffectInstance addedEffect()
    {
        return new EffectInstance(Effects.CONFUSION, 40, 0);
    }
    @Override
    public ParticleEffect getSpawnParticles() {
        return PARTICLES_HOVER;
    };
    @Override
    public ParticleType getParticleType() {
        return ModParticleTypes.DARK.get();
    }


}
