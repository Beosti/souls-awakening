package com.yuanno.soulsawakening.abilities.elements.poison;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class PoisonAttackAbility extends Ability implements IAttackAbility {
    public static final PoisonAttackAbility INSTANCE = new PoisonAttackAbility();
    public PoisonAttackAbility()
    {
        this.setName("Poison Attack");
        this.setSubCategory(SubCategory.SHIKAI);
        this.dependency = AbilityDependencies::shikaiDependance;
    }

    @Override
    public EffectInstance addedEffect()
    {
        return new EffectInstance(Effects.POISON, 40, 0);
    }
}
