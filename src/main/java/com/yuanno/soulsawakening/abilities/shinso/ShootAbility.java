package com.yuanno.soulsawakening.abilities.shinso;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
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
        this.setSubCategory(SubCategory.SHIKAI);
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
