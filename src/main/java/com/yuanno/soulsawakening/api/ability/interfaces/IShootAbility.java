package com.yuanno.soulsawakening.api.ability.interfaces;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.events.api.CustomInteractionEvent;
import com.yuanno.soulsawakening.events.ability.RightClickAbilityEvents;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

/**
 * Used for abilities that shoot a projectile.
 * Logic handle in {@link #onUse(PlayerEntity)}, triggered here:
 * @see RightClickAbilityEvents#customRightClickLogic(CustomInteractionEvent)
 * {@link #getProjectile(PlayerEntity)} get the projectile to shoot
 * {@link #getVelocity()} velocity set for the projectile, default 1
 * {@link #getInaccuracy()} innacuracy set for the projectile, default 1
 *
 * @author Beosti
 */
public interface IShootAbility {

    default void onUse(PlayerEntity player, Ability ability) {
        AbilityProjectileEntity projectile = getProjectile(player);
        if (ability instanceof IReiatsuAbility) {
            projectile.setAddedDamage(((IReiatsuAbility) ability).addedVariable(player));
            projectile.alterMaxLife(((IReiatsuAbility) ability).addedLife());
        }
        if (projectile != null) {
            player.level.addFreshEntity(projectile);
            projectile.shootFromRotation(player, player.xRot, player.yRot, 0, getVelocity(), getInaccuracy());
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
    default float getInaccuracy()
    {
        return 1;
    }
}
