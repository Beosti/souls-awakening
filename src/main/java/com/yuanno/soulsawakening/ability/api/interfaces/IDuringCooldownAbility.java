package com.yuanno.soulsawakening.ability.api.interfaces;

import net.minecraft.entity.player.PlayerEntity;

public interface IDuringCooldownAbility {

    default void onCooldown(PlayerEntity user) {}
}
