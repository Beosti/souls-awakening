package com.yuanno.soulsawakening.abilities.elements.thunder;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IAttackAbility;
import com.yuanno.soulsawakening.init.ModEffects;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.HoveringParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;

public class ThunderAttackAbility extends Ability implements IAttackAbility {
    public static final ThunderAttackAbility INSTANCE = new ThunderAttackAbility();
    public static final ParticleEffect PARTICLES_HOVER = new HoveringParticleEffect(1, 3);


    public ThunderAttackAbility() {
        this.setName("Thunder Attack");
        this.setActivationType(ActivationType.SHIFT_RIGHT_CLICK);
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public void activate(LivingEntity livingEntityTarget, PlayerEntity player)
    {
        PARTICLES_HOVER.spawn(livingEntityTarget.level, livingEntityTarget.getX(), livingEntityTarget.getY(), livingEntityTarget.getZ(), 0, 0, 0, ModParticleTypes.THUNDER.get());
        if (!livingEntityTarget.hasEffect(ModEffects.ELECTROCUTED.get()))
            livingEntityTarget.addEffect(new EffectInstance(ModEffects.ELECTROCUTED.get(), 40, 0));
    }
}
