package com.yuanno.soulsawakening.abilities.quincy.rod;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IContinuousAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;

public class WeakeningBobberAbility extends Ability implements IContinuousAbility, IRightClickAbility {
    public static final WeakeningBobberAbility INSTANCE = new WeakeningBobberAbility();

    public WeakeningBobberAbility()
    {
        this.setName("Weakening Bobber");
        this.setDescription("The next bobber that you ");
        this.setMaxCooldown(16);
        this.setSubCategory(SubCategory.REISHI);
    }

    @Override
    public boolean getShift() {
        return true;
    }
}
