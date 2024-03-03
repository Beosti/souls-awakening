package com.yuanno.soulsawakening.ability.api.interfaces;

import net.minecraft.entity.player.PlayerEntity;

public interface IPassiveAbility {

    default void onStartAbility(PlayerEntity user) {};
    default void onContinuousAbility(PlayerEntity user) {};
    default void onEndContinuousAbility(PlayerEntity user) {};
}
