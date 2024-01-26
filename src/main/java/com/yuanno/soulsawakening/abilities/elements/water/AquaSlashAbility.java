package com.yuanno.soulsawakening.abilities.elements.water;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IRightClickEmptyAbility;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.entities.projectiles.water.WaterSlashProjectile;
import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.entity.player.PlayerEntity;

public class AquaSlashAbility extends Ability implements IRightClickEmptyAbility {
    public static final AquaSlashAbility INSTANCE = new AquaSlashAbility();

    public AquaSlashAbility()
    {
        this.setName("Aqua Slash");
        this.setCooldown(10);
        this.setMaxCooldown(10);
        this.setPassive(false);
        this.setActivationType(ActivationType.RIGHT_CLICK_EMPTY);
        this.setZanpakutoState(ModValues.STATE.SHIKAI);
        this.setCategory(Category.ZANPAKUTO);
    }

    @Override
    public void onRightClick(PlayerEntity user)
    {
        IEntityStats entityStats = EntityStatsCapability.get(user);
        WaterSlashProjectile projectile = new WaterSlashProjectile(user.level, user);
        projectile.alterDamage((float) (entityStats.getReiatsuPoints()/2));
        user.level.addFreshEntity(projectile);
        projectile.shootFromRotation(user, user.xRot, user.yRot, 0, 0.5f, 1);

    }
}
