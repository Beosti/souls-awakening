package com.yuanno.soulsawakening.ability.elements.fire;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.entities.projectiles.fire.FireBallProjectile;
import com.yuanno.soulsawakening.init.ModResources;
import net.minecraft.entity.player.PlayerEntity;

public class FireBallAbility extends Ability {
    public static final FireBallAbility INSTANCE = new FireBallAbility();

    public FireBallAbility()
    {
        this.setName("Fireball");
        this.setPassive(false);
        this.setActivationType(ActivationType.RIGHT_CLICK_EMPTY);
        this.setZanpakutoState(ModResources.STATE.SHIKAI);
    }

    @Override
    public void onRightClick(PlayerEntity user)
    {
        FireBallProjectile projectile = new FireBallProjectile(user.level, user);
        user.level.addFreshEntity(projectile);
        projectile.shootFromRotation(user, user.xRot, user.yRot, 0, 0.5f, 1);

    }
}
