package com.yuanno.soulsawakening.abilities.elements.poison;

import com.yuanno.soulsawakening.ability.api.Ability;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class PoisonAttackAbility extends Ability {
    public static final PoisonAttackAbility INSTANCE = new PoisonAttackAbility();
    public PoisonAttackAbility()
    {
        this.setName("Poison Attack");
        this.setPassive(true);
        this.setActivationType(ActivationType.ATTACK);
    }

    @Override
    public void activate(LivingEntity livingEntityTarget, PlayerEntity player)
    {
        livingEntityTarget.addEffect(new EffectInstance(Effects.POISON, 80, 0));
    }
}
