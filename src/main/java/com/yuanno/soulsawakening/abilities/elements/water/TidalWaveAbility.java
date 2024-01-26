package com.yuanno.soulsawakening.abilities.elements.water;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IRightClickEmptyAbility;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.entities.projectiles.water.TidalWaveProjectile;
import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.entity.player.PlayerEntity;

public class TidalWaveAbility extends Ability implements IRightClickEmptyAbility {
    public static final TidalWaveAbility INSTANCE = new TidalWaveAbility();

    public TidalWaveAbility()
    {
        this.setName("Tidal Wave");
        this.setCooldown(10);
        this.setMaxCooldown(10);
        this.setPassive(false);
        this.setActivationType(ActivationType.SHIFT_RIGHT_CLICK);
        this.setZanpakutoState(ModValues.STATE.SHIKAI);
        this.setCategory(Category.ZANPAKUTO);
    }

    @Override
    public void onShiftRightClick(PlayerEntity user)
    {
        IEntityStats entityStats = EntityStatsCapability.get(user);
        TidalWaveProjectile projectile = new TidalWaveProjectile(user.level, user);
        projectile.alterDamage((float) entityStats.getReiatsuPoints());
        user.level.addFreshEntity(projectile);
        projectile.shootFromRotation(user, user.xRot, user.yRot, 0, 0.5f, 1);

    }
}
