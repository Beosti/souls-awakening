package com.yuanno.soulsawakening.abilities.elements.thunder;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IShootAbility;
import com.yuanno.soulsawakening.entities.projectiles.lightning.ThunderBallProjectile;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

public class ThunderballAbility extends Ability implements IShootAbility, IRightClickAbility {
    public static final ThunderballAbility INSTANCE = new ThunderballAbility();

    public ThunderballAbility()
    {
        this.setName("Thunderball");
        this.setDescription("Shoots a thunderball, hitting en entity electrocutes it");
        this.setMaxCooldown(16);
        this.dependency = AbilityDependencies::shikaiDependance;
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public AbilityProjectileEntity getProjectile(PlayerEntity player) {
        return new ThunderBallProjectile(player.level, player);
    }
}
