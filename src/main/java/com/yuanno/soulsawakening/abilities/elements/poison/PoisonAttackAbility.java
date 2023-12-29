package com.yuanno.soulsawakening.abilities.elements.poison;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IAttackAbility;
import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class PoisonAttackAbility extends Ability implements IAttackAbility {
    public static final PoisonAttackAbility INSTANCE = new PoisonAttackAbility();
    public PoisonAttackAbility()
    {
        this.setName("Poison Attack");
        this.setActivationType(ActivationType.ATTACK);
        this.setZanpakutoState(ModValues.STATE.SHIKAI);
    }

    @Override
    public void activate(LivingEntity livingEntityTarget, PlayerEntity player)
    {
        livingEntityTarget.addEffect(new EffectInstance(Effects.POISON, 100, 0));
    }
}
