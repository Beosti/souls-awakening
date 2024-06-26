package com.yuanno.soulsawakening.abilities.elements.wind;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.api.Beapi;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3d;

public class WindAttackAbility extends Ability implements IAttackAbility {
    public static final WindAttackAbility INSTANCE = new WindAttackAbility();


    public WindAttackAbility() {
        this.setName("Wind Attack");
        this.setSubCategory(SubCategory.SHIKAI);
        this.dependency = AbilityDependencies::shikaiDependance;
    }

    @Override
    public void activate(PlayerEntity player, LivingEntity livingEntityTarget)
    {
        Vector3d speed = Beapi.propulsion(livingEntityTarget, 0.2, 0.2);
        livingEntityTarget.setDeltaMovement(speed.x, 4, speed.z);
    }
}
