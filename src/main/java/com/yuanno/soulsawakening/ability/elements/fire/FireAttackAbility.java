package com.yuanno.soulsawakening.ability.elements.fire;

import com.yuanno.soulsawakening.ability.api.Ability;
import net.minecraft.entity.LivingEntity;

public class FireAttackAbility extends Ability {
    public static final FireAttackAbility INSTANCE = new FireAttackAbility();

    public FireAttackAbility() {
        this.setName("Fire Attack");
        this.setCooldown(0);
        this.setActivationType(ActivationType.ATTACK);
    }

    @Override
    public void activate(LivingEntity livingEntityTarget)
    {
        livingEntityTarget.setSecondsOnFire(5);
    }
}
