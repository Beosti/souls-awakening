package com.yuanno.soulsawakening.abilities.elements.fire;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IAttackAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class FireAttackAbility extends Ability implements IAttackAbility {
    public static final FireAttackAbility INSTANCE = new FireAttackAbility();


    public FireAttackAbility() {
        this.setName("Fire Attack");
        this.setDescription("Hitting an enemy puts them on fire");
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public int secondsOnFire()
    {
        return 5;
    }
}
