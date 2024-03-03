package com.yuanno.soulsawakening.abilities.shinso;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IShootAbility;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.entities.projectiles.shinso.BladeProjectile;
import com.yuanno.soulsawakening.entities.projectiles.water.TidalWaveProjectile;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

public class ShootAbility extends Ability implements IRightClickAbility, IShootAbility {
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
    public AbilityProjectileEntity getProjectile(PlayerEntity player) {
        return new BladeProjectile(player.level, player);
    }


    @Override
    public boolean getShift()
    {
        return false;
    }
}
