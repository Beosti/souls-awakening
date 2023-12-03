package com.yuanno.soulsawakening.ability.api;

import net.minecraft.entity.player.PlayerEntity;

public interface IContinuousAbility {

    default void onStartAbility(PlayerEntity user) {};
    default void onContinuousAbility(PlayerEntity user) {};
    default void onEndContinuousAbility(PlayerEntity user) {};
}
