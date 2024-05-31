package com.yuanno.soulsawakening.abilities.shinso;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.entities.projectiles.shinso.WideBladeProjectile;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

public class WideShootAbility extends Ability implements IRightClickAbility, IShootAbility {
    public static final WideShootAbility INSTANCE = new WideShootAbility();

    public WideShootAbility()
    {
        this.setName("Wide Shoot");
        this.setCooldown(20);
        this.setMaxCooldown(20);
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public AbilityProjectileEntity getProjectile(PlayerEntity player) {
        return new WideBladeProjectile(player.level, player);
    }


    @Override
    public boolean getShift()
    {
        return false;
    }
}
