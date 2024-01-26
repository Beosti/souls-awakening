package com.yuanno.soulsawakening.ability.api;

import net.minecraft.entity.player.PlayerEntity;

public interface IKidoAbility {

    default void onUse(PlayerEntity player) {}
}
