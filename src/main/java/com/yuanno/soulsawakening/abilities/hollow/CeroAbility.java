package com.yuanno.soulsawakening.abilities.hollow;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IShootAbility;
import com.yuanno.soulsawakening.entities.projectiles.hollow.CeroProjectile;
import com.yuanno.soulsawakening.entities.projectiles.water.TidalWaveProjectile;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

public class CeroAbility extends Ability implements IRightClickAbility, IShootAbility {
    public static final CeroAbility INSTANCE = new CeroAbility();

    public CeroAbility()
    {
        this.setName("Cero");
        this.setDescription("Unleashes an energy blast dealing damage");
        this.setCooldown(45);
        this.setMaxCooldown(45);
        this.setPassive(false);
        this.setActivationType(ActivationType.RIGHT_CLICK_EMPTY);
        this.setSubCategory(SubCategory.GILLIAN);
    }

    @Override
    public AbilityProjectileEntity getProjectile(PlayerEntity player) {
        return new CeroProjectile(player.level, player);
    }


    @Override
    public boolean getShift()
    {
        return false;
    }
}
