package com.yuanno.soulsawakening.abilities.elements.thunder;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.init.ModEffects;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.HoveringParticleEffect;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.EffectInstance;

public class ThunderAttackAbility extends Ability implements IAttackAbility, IParticleEffect {
    public static final ThunderAttackAbility INSTANCE = new ThunderAttackAbility();
    public static final ParticleEffect PARTICLES_HOVER = new HoveringParticleEffect(1, 3);


    public ThunderAttackAbility() {
        this.setName("Thunder Attack");
        this.setSubCategory(SubCategory.SHIKAI);
    }

    public ParticleEffect getSpawnParticles() {
        return PARTICLES_HOVER;
    };
    public ParticleType getParticleType() {
        return ModParticleTypes.THUNDER.get();
    }
    public EffectInstance addedEffect()
    {
        return new EffectInstance(ModEffects.ELECTROCUTED.get(), 40, 0);
    }
}
