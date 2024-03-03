package com.yuanno.soulsawakening.abilities.shinso;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickEmptyAbility;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.entities.projectiles.shinso.WideBladeProjectile;
import net.minecraft.entity.player.PlayerEntity;

public class WideShootAbility extends Ability implements IRightClickEmptyAbility {
    public static final WideShootAbility INSTANCE = new WideShootAbility();

    public WideShootAbility()
    {
        this.setName("Wide Shoot");
        this.setCooldown(20);
        this.setMaxCooldown(20);
        this.setActivationType(ActivationType.SHIFT_RIGHT_CLICK);
        this.setCategory(Category.ZANPAKUTO);
    }

    @Override
    public void onShiftRightClick(PlayerEntity user)
    {
        IEntityStats entityStats = EntityStatsCapability.get(user);
        WideBladeProjectile wideBladeProjectile = new WideBladeProjectile(user.level, user);
        wideBladeProjectile.alterDamage((float) (entityStats.getZanjutsuPoints()/2));
        user.level.addFreshEntity(wideBladeProjectile);
        wideBladeProjectile.shootFromRotation(user, user.xRot, user.yRot, 0, 5f, 1);
    }
}
