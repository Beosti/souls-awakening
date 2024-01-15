package com.yuanno.soulsawakening.abilities.elements.fire;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IRightClickEmptyAbility;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.entities.projectiles.fire.FireBallProjectile;
import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.entity.player.PlayerEntity;

public class FireBallAbility extends Ability implements IRightClickEmptyAbility {
    public static final FireBallAbility INSTANCE = new FireBallAbility();

    public FireBallAbility()
    {
        this.setName("Fireball");
        this.setDescription("Shoots a fireball");
        this.setCooldown(10);
        this.setMaxCooldown(10);
        this.setPassive(false);
        this.setActivationType(ActivationType.RIGHT_CLICK_EMPTY);
        this.setZanpakutoState(ModValues.STATE.SHIKAI);
    }

    @Override
    public void onRightClick(PlayerEntity user)
    {
        FireBallProjectile projectile = new FireBallProjectile(user.level, user);
        IEntityStats entityStats = EntityStatsCapability.get(user);
        projectile.alterDamage((float) (entityStats.getReiatsuPoints()/2));
        user.level.addFreshEntity(projectile);
        projectile.shootFromRotation(user, user.xRot, user.yRot, 0, 0.5f, 1);

    }
}
