package com.yuanno.soulsawakening.ability.api.interfaces;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

/**
 * Used for abilities that do something when hitting an enemy, hook an 'event' on it
 */
public interface IAttackAbility {

    default void activate(LivingEntity entityTarget, PlayerEntity player) {};
}
