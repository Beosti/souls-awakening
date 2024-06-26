package com.yuanno.soulsawakening.abilities.quincy.rod;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.init.ModItems;

public class ExplodingBobberAbility extends Ability implements IContinuousAbility, IRightClickAbility {
    public static final ExplodingBobberAbility INSTANCE = new ExplodingBobberAbility();

    public ExplodingBobberAbility()
    {
        this.setName("Exploding Bobber");
        this.setDescription("The next bobber that you land on an entity or block explodes");
        this.setMaxCooldown(16);
        this.setSubCategory(SubCategory.SPIRIT_WEAPON);
        this.dependency = player -> AbilityDependencies.itemDependence(player, ModItems.FISHING_ROD_REISHI.get());
    }

    @Override
    public boolean getAlt() {
        return true;
    }
}
