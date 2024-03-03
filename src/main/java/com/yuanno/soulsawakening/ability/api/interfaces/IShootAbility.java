package com.yuanno.soulsawakening.ability.api.interfaces;

import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

public interface IShootAbility {

    default void onUse(PlayerEntity player) {
        AbilityProjectileEntity projectile = getProjectile(player);
        if (projectile != null) {
            player.level.addFreshEntity(projectile);
            projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 1f, 1);
        }
    }

    default AbilityProjectileEntity getProjectile(PlayerEntity player) { return null; }
}
