package com.yuanno.soulsawakening.abilities.elements.fire;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.api.SourceElement;

public class FireAttackAbility extends Ability implements IAttackAbility {
    public static final FireAttackAbility INSTANCE = new FireAttackAbility();

    public FireAttackAbility() {
        this.setName("Fire Attack");
        this.setDescription("Hitting an enemy puts them on fire");
        this.setSourceElement(SourceElement.FIRE);
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public int secondsOnFire()
    {
        return 3;
    }
}
