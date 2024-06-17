package com.yuanno.soulsawakening.abilities.elements.heal;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.IContinuousAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IOnDeath;
import net.minecraft.entity.player.PlayerEntity;

public class SecondChanceAbility extends Ability implements IContinuousAbility, IOnDeath {
    public static final SecondChanceAbility INSTANCE = new SecondChanceAbility();

    public SecondChanceAbility()
    {
        this.setName("Second Chance");
        this.setDescription("Resuscitates the user upon taking lethal damage");
        this.setMaxCooldown(100);
        this.dependency = AbilityDependencies::shikaiDependance;
        this.setSubCategory(SubCategory.REISHI);
    }

    @Override
    public boolean getCancelDeath() {
        return true;
    }

    @Override
    public void onDeath(PlayerEntity player) {
        player.heal(4);
    }

    @Override
    public boolean getPassive() {
        return true;
    }

    @Override
    public boolean getEndAfterUse() {
        return true;
    }
}
