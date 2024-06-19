package com.yuanno.soulsawakening.abilities.elements.water;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IShootAbility;
import com.yuanno.soulsawakening.entities.projectiles.water.WaterPressureProjectile;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

public class WaterPressureAbility extends Ability implements IShootAbility, IRightClickAbility {
    public static final WaterPressureAbility INSTANCE = new WaterPressureAbility();

    public WaterPressureAbility()
    {
        this.setName("Water Pressure");
        this.setDescription("Shoots a pressurized beam of water");
        this.setCooldown(30);
        this.dependency = AbilityDependencies::shikaiDependance;
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public AbilityProjectileEntity getProjectile(PlayerEntity player) {
        return new WaterPressureProjectile(player.level, player);
    }

    @Override
    public float getVelocity() {
        return 3f;
    }

    @Override
    public boolean getShift() {
        return true;
    }
}
