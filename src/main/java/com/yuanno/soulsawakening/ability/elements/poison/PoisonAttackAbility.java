package com.yuanno.soulsawakening.ability.elements.poison;

import com.yuanno.soulsawakening.ability.api.Ability;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class PoisonAttackAbility extends Ability {
    public PoisonAttackAbility()
    {
        this.setName("Poison Attack");
        this.setCooldown(0);
        this.setActivationType(ActivationType.ATTACK);
    }

    @Override
    public void activate(LivingEntity livingEntityTarget)
    {
        livingEntityTarget.addEffect(new EffectInstance(Effects.POISON, 80, 0));
    }
}
