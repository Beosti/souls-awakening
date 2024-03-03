package com.yuanno.soulsawakening.abilities.elements.lunar;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IShootAbility;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.entities.projectiles.fire.FireBallProjectile;
import com.yuanno.soulsawakening.entities.projectiles.lunar.LunarCrescentProjectile;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

public class LunarCrescentAbility extends Ability implements IRightClickAbility, IShootAbility {
    public static final LunarCrescentAbility INSTANCE = new LunarCrescentAbility();

    public LunarCrescentAbility()
    {
        this.setName("Lunar Crescent");
        this.setCooldown(10);
        this.setMaxCooldown(10);
        this.setPassive(false);
        this.setActivationType(ActivationType.RIGHT_CLICK_EMPTY);
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
}
