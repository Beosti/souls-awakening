package com.yuanno.soulsawakening.ability.api.interfaces;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

public interface IShootAbility {

    default void onUse(PlayerEntity player, Ability ability) {
        AbilityProjectileEntity projectile = getProjectile(player);
        if (ability instanceof IReiatsuAbility) {
            projectile.alterDamage(((IReiatsuAbility) ability).addedVariable(player));
            projectile.alterMaxLife(((IReiatsuAbility) ability).addedLife());
        }
        if (projectile != null) {
            player.level.addFreshEntity(projectile);
            projectile.shootFromRotation(player, player.xRot, player.yRot, 0, getVelocity(), 1);
        }
    }
    default void onUse(PlayerEntity player) {
        AbilityProjectileEntity projectile = getProjectile(player);
        if (projectile != null) {
            player.level.addFreshEntity(projectile);
            projectile.shootFromRotation(player, player.xRot, player.yRot, 0, getVelocity(), 1);
        }
    }

    default AbilityProjectileEntity getProjectile(PlayerEntity player) { return null; }

    default float getVelocity()
    {
        return 1f;
    }
}
