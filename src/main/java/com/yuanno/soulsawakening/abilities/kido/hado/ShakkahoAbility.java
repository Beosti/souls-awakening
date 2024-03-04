package com.yuanno.soulsawakening.abilities.kido.hado;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IKidoAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IShootAbility;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.entities.projectiles.kido.ShakkahoProjectile;
import net.minecraft.entity.player.PlayerEntity;

// blasts the entity a few meters away
public class ShakkahoAbility extends Ability implements IShootAbility, IKidoAbility {
    public static final ShakkahoAbility INSTANCE = new ShakkahoAbility();

    public ShakkahoAbility()
    {
        this.setName("Shakkaho");
        this.setDescription("Generates and fires an orb of crimson energy");
        this.setMaxCooldown(10);
        this.setSubCategory(SubCategory.HADO);
    }

    @Override
    public void onUse(PlayerEntity player)
    {
        ShakkahoProjectile shakkahoProjectile = new ShakkahoProjectile(player.level, player);
        IEntityStats entityStats = EntityStatsCapability.get(player);
        shakkahoProjectile.alterDamage((float) (entityStats.getReiatsuPoints()/2));
        shakkahoProjectile.setMaxLife(64 + (int) entityStats.getReiatsuPoints()/2);
        player.level.addFreshEntity(shakkahoProjectile);
        shakkahoProjectile.shootFromRotation(player, player.xRot, player.yRot, 0, 1f ,1);
    }
}
