package com.yuanno.soulsawakening.abilities.elements.lunar;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IRightClickEmptyAbility;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.entities.projectiles.lunar.LunarCrescentProjectile;
import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.entity.player.PlayerEntity;

public class LunarCrescentAbility extends Ability implements IRightClickEmptyAbility {
    public static final LunarCrescentAbility INSTANCE = new LunarCrescentAbility();

    public LunarCrescentAbility()
    {
        this.setName("Lunar Crescent");
        this.setCooldown(10);
        this.setMaxCooldown(10);
        this.setPassive(false);
        this.setActivationType(ActivationType.RIGHT_CLICK_EMPTY);
        this.setZanpakutoState(ModValues.STATE.SHIKAI);
    }

    @Override
    public void onRightClick(PlayerEntity user)
    {
        IEntityStats entityStats = EntityStatsCapability.get(user);
        LunarCrescentProjectile projectile = new LunarCrescentProjectile(user.level, user);
        projectile.alterDamage((float) (entityStats.getReiatsuPoints()/2));
        user.level.addFreshEntity(projectile);
        projectile.shootFromRotation(user, user.xRot, user.yRot, 0, 0.5f, 1);

    }
}
