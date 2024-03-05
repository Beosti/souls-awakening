package com.yuanno.soulsawakening.abilities.kido.hado;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IReiatsuAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IShootAbility;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.entities.projectiles.kido.ShakkahoProjectile;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

// blasts the entity a few meters away
public class ShakkahoAbility extends Ability implements IShootAbility, IReiatsuAbility {
    public static final ShakkahoAbility INSTANCE = new ShakkahoAbility();

    public ShakkahoAbility()
    {
        this.setName("Shakkaho");
        this.setDescription("Generates and fires an orb of crimson energy");
        this.setMaxCooldown(10);
        this.setSubCategory(SubCategory.HADO);
    }
    @Override
    public float addedVariable(PlayerEntity player) {
        return (float) EntityStatsCapability.get(player).getReiatsuPoints();
    }
    @Override
    public AbilityProjectileEntity getProjectile(PlayerEntity player) {
        return new ShakkahoProjectile(player.level, player);
    }
}
