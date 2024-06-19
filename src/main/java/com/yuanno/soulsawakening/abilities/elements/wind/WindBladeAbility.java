package com.yuanno.soulsawakening.abilities.elements.wind;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IShootAbility;
import com.yuanno.soulsawakening.entities.projectiles.wind.WindSlashProjectile;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

public class WindBladeAbility extends Ability implements IShootAbility, IRightClickAbility {
    public static final WindBladeAbility INSTANCE = new WindBladeAbility();

    public WindBladeAbility()
    {
        this.setName("Wind Blade");
        this.setDescription("Shoots a wind blade");
        this.setMaxCooldown(10);
        this.dependency = AbilityDependencies::shikaiDependance;
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public AbilityProjectileEntity getProjectile(PlayerEntity player) {
        return new WindSlashProjectile(player.level, player);
    }

    @Override
    public float getVelocity() {
        return 2;
    }
}
