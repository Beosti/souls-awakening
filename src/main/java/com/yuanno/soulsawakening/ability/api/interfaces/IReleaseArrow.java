package com.yuanno.soulsawakening.ability.api.interfaces;

import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

public interface IReleaseArrow {

    default void onLooseArrow(PlayerEntity player, AbilityProjectileEntity abilityProjectileEntity, float power)
    {

    }
}
