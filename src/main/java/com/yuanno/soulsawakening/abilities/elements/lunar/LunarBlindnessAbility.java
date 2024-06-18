package com.yuanno.soulsawakening.abilities.elements.lunar;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.IEntityRayTrace;
import com.yuanno.soulsawakening.api.ability.interfaces.IRightClickAbility;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class LunarBlindnessAbility extends Ability implements IRightClickAbility, IEntityRayTrace {
    public static final LunarBlindnessAbility INSTANCE = new LunarBlindnessAbility();

    public LunarBlindnessAbility()
    {
        this.setName("Lunar Blindness");
        this.setDescription("Targets an entity, blinding it for a long period of time");
        this.setMaxCooldown(30);
        this.dependency = AbilityDependencies::shikaiDependance;
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public int getDistance() {
        return 12;
    }

    @Override
    public EffectInstance addEffect() {
        return new EffectInstance(Effects.BLINDNESS, 360, 0);
    }

    @Override
    public boolean getControl() {
        return true;
    }
}
