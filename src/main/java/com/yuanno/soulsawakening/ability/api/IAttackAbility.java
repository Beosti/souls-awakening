package com.yuanno.soulsawakening.ability.api;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

/**
 * Used for abilities that do something when hitting an enemy
 */
public interface IAttackAbility {

    public void activate(LivingEntity entityTarget, PlayerEntity player);
}
