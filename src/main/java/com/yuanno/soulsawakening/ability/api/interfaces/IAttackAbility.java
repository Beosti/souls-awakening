package com.yuanno.soulsawakening.ability.api.interfaces;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

/**
 * Used for abilities that do something when hitting an enemy, handled in {@link #activate(LivingEntity, PlayerEntity)} that is triggered
 * @see com.yuanno.soulsawakening.events.ability.AbilityEvents#onAttackEvent(AttackEntityEvent)
 * {@link #secondsOnFire()} if you want to put the entity targeted on fire
 * {@link #addedEffect()} if you want to add an effect to the entity targeted
 */
public interface IAttackAbility {

    default void activate(LivingEntity entityTarget, PlayerEntity player) {
        if (secondsOnFire() > secondsOnFire())
            entityTarget.setSecondsOnFire(secondsOnFire());
        if (addedEffect() != null && !entityTarget.hasEffect(addedEffect().getEffect()))
            entityTarget.addEffect(addedEffect());
    };

    default int secondsOnFire()
    {
        return 0;
    }

    default EffectInstance addedEffect()
    {
        return null;
    }
}
