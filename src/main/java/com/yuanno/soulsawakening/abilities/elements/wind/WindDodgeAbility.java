package com.yuanno.soulsawakening.abilities.elements.wind;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.IContinuousAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IGetHitAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IShootAbility;
import com.yuanno.soulsawakening.entities.projectiles.wind.WindSlashProjectile;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Random;

public class WindDodgeAbility extends Ability implements IGetHitAbility, IRightClickAbility, IContinuousAbility {
    public static final WindDodgeAbility INSTANCE = new WindDodgeAbility();

    public WindDodgeAbility()
    {
        this.setName("Wind Dodge");
        this.setDescription("For a period of time the wind blesses you, you won't be able to get hit");
        this.setMaxCooldown(30);
        this.dependency = AbilityDependencies::shikaiDependance;
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public void getHit(PlayerEntity player, LivingEntity attacker) {
        Random random = new Random();
        player.moveTo(player.getX() + random.nextInt(2), player.getY() + random.nextInt(1), player.getZ() + random.nextInt(2));
    }

    @Override
    public boolean getCancelEvent() {
        return true;
    }

    @Override
    public int getMaxTimer() {
        return 200;
    }

    @Override
    public boolean getControl() {
        return IRightClickAbility.super.getControl();
    }
}
