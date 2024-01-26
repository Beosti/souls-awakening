package com.yuanno.soulsawakening.ability.api;

import net.minecraft.entity.player.PlayerEntity;

public interface IShootAbility {

    default void onUse(PlayerEntity player) {}
}
