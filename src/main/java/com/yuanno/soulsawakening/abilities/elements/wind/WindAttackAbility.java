package com.yuanno.soulsawakening.abilities.elements.wind;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IAttackAbility;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3d;

public class WindAttackAbility extends Ability implements IAttackAbility {
    public static final WindAttackAbility INSTANCE = new WindAttackAbility();


    public WindAttackAbility() {
        this.setName("Wind Attack");
        this.setActivationType(ActivationType.ATTACK);
        this.setZanpakutoState(ModValues.STATE.SHIKAI);
    }

    @Override
    public void activate(LivingEntity livingEntityTarget, PlayerEntity player)
    {
        Vector3d speed = Beapi.propulsion(livingEntityTarget, 0.2, 0.2);
        livingEntityTarget.setDeltaMovement(speed.x, 4, speed.z);
    }
}
