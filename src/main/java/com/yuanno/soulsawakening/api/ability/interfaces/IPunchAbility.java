package com.yuanno.soulsawakening.api.ability.interfaces;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public interface IPunchAbility {

    default void startContinuity(PlayerEntity player) {}
    default void onHitEntity(LivingEntity target, PlayerEntity user) {}
    default void startCooldown(PlayerEntity player) {}

}
