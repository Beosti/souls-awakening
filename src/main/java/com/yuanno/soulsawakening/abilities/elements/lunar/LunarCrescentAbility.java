package com.yuanno.soulsawakening.abilities.elements.lunar;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IReiatsuAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IShootAbility;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.entities.projectiles.lunar.LunarCrescentProjectile;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

public class LunarCrescentAbility extends Ability implements IRightClickAbility, IShootAbility, IReiatsuAbility {
    public static final LunarCrescentAbility INSTANCE = new LunarCrescentAbility();

    public LunarCrescentAbility()
    {
        this.setName("Lunar Crescent");
        this.setMaxCooldown(10);
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public AbilityProjectileEntity getProjectile(PlayerEntity player) {
        return new LunarCrescentProjectile(player.level, player);
    }


    @Override
    public boolean getShift()
    {
        return false;
    }

    @Override
    public float addedVariable(PlayerEntity player) {
        return (float) EntityStatsCapability.get(player).getReiatsuPoints()/2;
    }
}
