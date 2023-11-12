package com.yuanno.soulsawakening.ability.elements.fire;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.init.ModResources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class FireAttackAbility extends Ability {
    public static final FireAttackAbility INSTANCE = new FireAttackAbility();


    public FireAttackAbility() {
        this.setName("Fire Attack");
        this.setPassive(true);
        this.setActivationType(ActivationType.ATTACK);
        this.setZanpakutoState(ModResources.STATE.SHIKAI);
    }

    @Override
    public void activate(LivingEntity livingEntityTarget, PlayerEntity player)
    {
        livingEntityTarget.setSecondsOnFire(5);
    }
}
