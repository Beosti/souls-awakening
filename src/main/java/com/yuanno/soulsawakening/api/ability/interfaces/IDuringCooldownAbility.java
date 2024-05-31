package com.yuanno.soulsawakening.api.ability.interfaces;

import net.minecraft.entity.player.PlayerEntity;

public interface IDuringCooldownAbility {

    default void onCooldown(PlayerEntity user) {}
}
