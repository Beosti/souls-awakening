package com.yuanno.soulsawakening.abilities.elements.water;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IReiatsuAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IShootAbility;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.entities.projectiles.water.WaterSlashProjectile;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

public class AquaSlashAbility extends Ability implements IRightClickAbility, IShootAbility, IReiatsuAbility {
    public static final AquaSlashAbility INSTANCE = new AquaSlashAbility();

    public AquaSlashAbility()
    {
        this.setName("Aqua Slash");
        this.setMaxCooldown(10);
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public AbilityProjectileEntity getProjectile(PlayerEntity player) {
        return new WaterSlashProjectile(player.level, player);
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
