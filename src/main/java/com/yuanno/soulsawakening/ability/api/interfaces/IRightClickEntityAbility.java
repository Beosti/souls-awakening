package com.yuanno.soulsawakening.ability.api.interfaces;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

/**
 * Used for abilities that right click (with item or without) specifically an entity
 * Something then happens with the player and/or the target
 */
public interface IRightClickEntityAbility {

    default void onRightClickEntity(LivingEntity target, PlayerEntity player) {}

}
