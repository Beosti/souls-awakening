package com.yuanno.soulsawakening.abilities.quincy;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IContinuousAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;

public class ExplodingBobberAbility extends Ability implements IContinuousAbility, IRightClickAbility {
    public static final ExplodingBobberAbility INSTANCE = new ExplodingBobberAbility();

    public ExplodingBobberAbility()
    {
        this.setName("Exploding Bobber");
        this.setDescription("The next bobber that you ");
        this.setMaxCooldown(16);
        this.setSubCategory(SubCategory.REISHI);
    }

    @Override
    public boolean getAlt() {
        return true;
    }
}