package com.yuanno.soulsawakening.abilities.elements.lunar;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
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
        this.dependency = AbilityDependencies::shikaiDependance;
    }

    @Override
    public AbilityProjectileEntity getProjectile(PlayerEntity player) {
        return new LunarCrescentProjectile(player.level, player);
    }


    @Override
    public float addedVariable(PlayerEntity player) {
        return (float) EntityStatsCapability.get(player).getReiatsuPoints()/2;
    }
}
