package com.yuanno.soulsawakening.abilities.hollow;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.entities.projectiles.hollow.CeroProjectile;
import com.yuanno.soulsawakening.entities.projectiles.water.TidalWaveProjectile;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

public class CeroAbility extends Ability implements IRightClickAbility, IShootAbility, IReiatsuAbility {
    public static final CeroAbility INSTANCE = new CeroAbility();

    public CeroAbility()
    {
        this.setName("Cero");
        this.setDescription("Unleashes an energy blast dealing damage");
        this.setMaxCooldown(45);
        this.setSubCategory(SubCategory.GILLIAN);
    }

    @Override
    public AbilityProjectileEntity getProjectile(PlayerEntity player) {
        return new CeroProjectile(player.level, player);
    }

    @Override
    public float addedVariable(PlayerEntity player) {
        return (int) EntityStatsCapability.get(player).getReiatsuPoints();
    }

    @Override
    public int reducedCooldown(PlayerEntity player) {
        return (int) EntityStatsCapability.get(player).getReiatsuPoints() * 10;
    }

    @Override
    public boolean getShift()
    {
        return false;
    }
}
