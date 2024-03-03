package com.yuanno.soulsawakening.abilities.kido.hado;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IKidoAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IShootAbility;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.entities.projectiles.kido.ByakuraiProjectile;
import net.minecraft.entity.player.PlayerEntity;

// blasts the entity a few meters away
public class ByakuraiAbility extends Ability implements IShootAbility, IKidoAbility {
    public static final ByakuraiAbility INSTANCE = new ByakuraiAbility();

    public ByakuraiAbility()
    {
        this.setName("Byakurai");
        this.setDescription("Shoots a focuses reishi energy beam");
        this.setCooldown(10);
        this.setMaxCooldown(10);
        this.setPassive(false);
        this.setActivationType(ActivationType.SCROLL);
        this.setSubCategory(SubCategory.HADO);
    }

    @Override
    public void onUse(PlayerEntity player)
    {
        ByakuraiProjectile byakuraiProjectile = new ByakuraiProjectile(player.level, player);
        IEntityStats entityStats = EntityStatsCapability.get(player);
        byakuraiProjectile.alterDamage((float) (entityStats.getReiatsuPoints()/4));
        player.level.addFreshEntity(byakuraiProjectile);
        byakuraiProjectile.shootFromRotation(player, player.xRot, player.yRot, 0, 1f ,1);
    }
}
