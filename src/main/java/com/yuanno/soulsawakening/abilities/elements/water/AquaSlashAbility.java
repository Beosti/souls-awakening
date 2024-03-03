package com.yuanno.soulsawakening.abilities.elements.water;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IShootAbility;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.entities.projectiles.fire.FireBallProjectile;
import com.yuanno.soulsawakening.entities.projectiles.water.WaterSlashProjectile;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

public class AquaSlashAbility extends Ability implements IRightClickAbility, IShootAbility {
    public static final AquaSlashAbility INSTANCE = new AquaSlashAbility();

    public AquaSlashAbility()
    {
        this.setName("Aqua Slash");
        this.setCooldown(10);
        this.setMaxCooldown(10);
        this.setPassive(false);
        this.setActivationType(ActivationType.RIGHT_CLICK_EMPTY);
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
}
