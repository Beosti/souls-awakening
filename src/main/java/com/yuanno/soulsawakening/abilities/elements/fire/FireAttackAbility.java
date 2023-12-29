package com.yuanno.soulsawakening.abilities.elements.fire;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IAttackAbility;
import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class FireAttackAbility extends Ability implements IAttackAbility {
    public static final FireAttackAbility INSTANCE = new FireAttackAbility();


    public FireAttackAbility() {
        this.setName("Fire Attack");
        this.setActivationType(ActivationType.ATTACK);
        this.setZanpakutoState(ModValues.STATE.SHIKAI);
    }

    @Override
    public void activate(LivingEntity livingEntityTarget, PlayerEntity player)
    {
        livingEntityTarget.setSecondsOnFire(5);
    }
}
