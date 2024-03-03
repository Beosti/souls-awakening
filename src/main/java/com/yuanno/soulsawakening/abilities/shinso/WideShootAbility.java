package com.yuanno.soulsawakening.abilities.shinso;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IShootAbility;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.entities.projectiles.lunar.LunarCrescentProjectile;
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
        this.setActivationType(ActivationType.SHIFT_RIGHT_CLICK);
        this.setCategory(Category.ZANPAKUTO);
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
