package com.yuanno.soulsawakening.abilities.shinso;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IRightClickEmptyAbility;
import com.yuanno.soulsawakening.entities.projectiles.shinso.BladeProjectile;
import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.entity.player.PlayerEntity;

public class ShootAbility extends Ability implements IRightClickEmptyAbility {
    public static final ShootAbility INSTANCE = new ShootAbility();

    public ShootAbility()
    {
        this.setName("Shoot");
        this.setCooldown(4);
        this.setMaxCooldown(4);
        this.setPassive(false);
        this.setActivationType(Ability.ActivationType.RIGHT_CLICK_EMPTY);
        this.setZanpakutoState(ModValues.STATE.SHIKAI);
    }

    @Override
    public void onRightClick(PlayerEntity user)
    {
        BladeProjectile bladeProjectile = new BladeProjectile(user.level, user);
        user.level.addFreshEntity(bladeProjectile);
        bladeProjectile.shootFromRotation(user, user.xRot, user.yRot, 0, 5f, 1);
    }
}
