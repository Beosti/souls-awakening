package com.yuanno.soulsawakening.abilities.elements.water;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.entities.projectiles.fire.FireBallProjectile;
import com.yuanno.soulsawakening.entities.projectiles.water.TidalWaveProjectile;
import com.yuanno.soulsawakening.init.ModResources;
import net.minecraft.entity.player.PlayerEntity;

public class TidalWaveAbility extends Ability {
    public static final TidalWaveAbility INSTANCE = new TidalWaveAbility();

    public TidalWaveAbility()
    {
        this.setName("Tidal Wave");
        this.setCooldown(10);
        this.setMaxCooldown(10);
        this.setPassive(false);
        this.setActivationType(ActivationType.SHIFT_RIGHT_CLICK);
        this.setZanpakutoState(ModResources.STATE.SHIKAI);
    }

    @Override
    public void onRightClick(PlayerEntity user)
    {
        TidalWaveProjectile projectile = new TidalWaveProjectile(user.level, user);
        user.level.addFreshEntity(projectile);
        projectile.shootFromRotation(user, user.xRot, user.yRot, 0, 0.5f, 1);

    }
}
