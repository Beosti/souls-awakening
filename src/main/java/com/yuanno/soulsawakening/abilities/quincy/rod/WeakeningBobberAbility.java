package com.yuanno.soulsawakening.abilities.quincy.rod;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.init.ModItems;

public class WeakeningBobberAbility extends Ability implements IContinuousAbility, IRightClickAbility {
    public static final WeakeningBobberAbility INSTANCE = new WeakeningBobberAbility();

    public WeakeningBobberAbility()
    {
        this.setName("Weakening Bobber");
        this.setDescription("The next bobber that you ");
        this.setMaxCooldown(16);
        this.setSubCategory(SubCategory.SPIRIT_WEAPON);
        this.dependency = player -> AbilityDependencies.itemDependence(player, ModItems.FISHING_ROD_REISHI.get());
    }

    @Override
    public boolean getShift() {
        return true;
    }
}
