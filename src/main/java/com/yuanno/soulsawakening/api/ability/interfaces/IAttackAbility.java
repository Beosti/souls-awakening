package com.yuanno.soulsawakening.api.ability.interfaces;

import com.yuanno.soulsawakening.api.ability.Ability;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

/**
 * Used for abilities that do something when hitting an enemy, handled in {@link #activate(PlayerEntity, LivingEntity)} that is triggered
 * @see AttackEntityEvent
 * {@link #secondsOnFire()} if you want to put the entity targeted on fire
 * {@link #addedEffect()} if you want to add an effect to the entity targeted
 */
public interface IAttackAbility {

    default void activateBack(PlayerEntity player, LivingEntity target, Ability ability)
    {
        activate(player, target);
    }
    default void activate(PlayerEntity player, LivingEntity target) {
        if (secondsOnFire() > 0)
            target.setSecondsOnFire(secondsOnFire());
        if (addedEffect() != null && !target.hasEffect(addedEffect().getEffect()))
            target.addEffect(addedEffect());
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
