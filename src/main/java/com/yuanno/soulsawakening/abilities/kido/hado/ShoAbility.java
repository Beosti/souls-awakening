package com.yuanno.soulsawakening.abilities.kido.hado;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IKidoAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IShootAbility;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.entities.projectiles.kido.ShoProjectile;
import net.minecraft.entity.player.PlayerEntity;

// blasts the entity a few meters away
public class ShoAbility extends Ability implements IShootAbility, IKidoAbility {
    public static final ShoAbility INSTANCE = new ShoAbility();

    public ShoAbility()
    {
        this.setName("Sho");
        this.setDescription("Shoots a projectile pushing away entities");
        this.setCooldown(10);
        this.setMaxCooldown(10);
        this.setPassive(false);
        this.setActivationType(ActivationType.SCROLL);
        this.setSubCategory(SubCategory.HADO);
    }

    @Override
    public void onUse(PlayerEntity player)
    {
        ShoProjectile shoProjectile = new ShoProjectile(player.level, player);
        IEntityStats entityStats = EntityStatsCapability.get(player);
        shoProjectile.setMaxLife(32 + (int) entityStats.getReiatsuPoints());
        shoProjectile.setKnockbackStrength(3 + (int) entityStats.getReiatsuPoints()/4);
        player.level.addFreshEntity(shoProjectile);
        shoProjectile.shootFromRotation(player, player.xRot, player.yRot, 0, 1f ,1);
    }
}
