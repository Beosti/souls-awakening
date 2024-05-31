package com.yuanno.soulsawakening.abilities.elements.water;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.entities.projectiles.water.TidalWaveProjectile;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

public class TidalWaveAbility extends Ability implements IRightClickAbility, IShootAbility, IReiatsuAbility {
    public static final TidalWaveAbility INSTANCE = new TidalWaveAbility();

    public TidalWaveAbility()
    {
        this.setName("Tidal Wave");
        this.setMaxCooldown(10);
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public AbilityProjectileEntity getProjectile(PlayerEntity player) {
        return new TidalWaveProjectile(player.level, player);
    }
    @Override
    public boolean getShift()
    {
        return true;
    }
    @Override
    public float addedVariable(PlayerEntity player) {
        return (float) EntityStatsCapability.get(player).getReiatsuPoints()/2;
    }
}
