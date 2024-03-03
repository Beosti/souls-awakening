package com.yuanno.soulsawakening.abilities.shinso;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickEmptyAbility;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.entities.projectiles.shinso.BladeProjectile;
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
        this.setCategory(Category.ZANPAKUTO);
    }

    @Override
    public void onRightClick(PlayerEntity user)
    {
        IEntityStats entityStats = EntityStatsCapability.get(user);
        BladeProjectile bladeProjectile = new BladeProjectile(user.level, user);
        bladeProjectile.alterDamage((float) entityStats.getZanjutsuPoints());
        user.level.addFreshEntity(bladeProjectile);
        bladeProjectile.shootFromRotation(user, user.xRot, user.yRot, 0, 5f, 1);
    }
}
