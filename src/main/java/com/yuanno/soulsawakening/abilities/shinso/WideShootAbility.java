package com.yuanno.soulsawakening.abilities.shinso;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IRightClickEmptyAbility;
import com.yuanno.soulsawakening.entities.projectiles.shinso.BladeProjectile;
import com.yuanno.soulsawakening.entities.projectiles.shinso.WideBladeProjectile;
import com.yuanno.soulsawakening.init.ModResources;
import net.minecraft.entity.player.PlayerEntity;

public class WideShootAbility extends Ability implements IRightClickEmptyAbility {
    public static final WideShootAbility INSTANCE = new WideShootAbility();

    public WideShootAbility()
    {
        this.setName("Wide Shoot");
        this.setCooldown(45);
        this.setMaxCooldown(45);
        this.setActivationType(ActivationType.SHIFT_RIGHT_CLICK);
        this.setZanpakutoState(ModResources.STATE.SHIKAI);
    }

    @Override
    public void onShiftRightClick(PlayerEntity user)
    {
        WideBladeProjectile wideBladeProjectile = new WideBladeProjectile(user.level, user);
        user.level.addFreshEntity(wideBladeProjectile);
        wideBladeProjectile.shootFromRotation(user, user.xRot, user.yRot, 0, 5f, 1);
    }
}
