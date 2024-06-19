package com.yuanno.soulsawakening.abilities.elements.wind;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.api.SourceElement;
import com.yuanno.soulsawakening.api.SourceType;
import com.yuanno.soulsawakening.init.ModDamageSource;
import net.minecraft.util.DamageSource;


public class WhirldWindDanceAbility extends Ability implements IRightClickAbility, IWaveAbility {
    public static final WhirldWindDanceAbility INSTANCE = new WhirldWindDanceAbility();
    private final static DamageSource WIND_DAMAGE = new ModDamageSource("wind_wave").setSourceTypes(SourceType.SHOCKWAVE).setSourceElement(SourceElement.WIND);

    public WhirldWindDanceAbility()
    {
        this.setName("Whirld Wind Dance");
        this.setMaxCooldown(8);
        this.setSubCategory(SubCategory.SHIKAI);
        this.dependency = AbilityDependencies::shikaiDependance;
    }

    @Override
    public int getRadius()
    {
        return 10;
    }

    @Override
    public DamageSource getDamageSource()
    {
        return WIND_DAMAGE;
    }
    @Override
    public float getBaseDamage()
    {
        return 5;
    }

    @Override
    public boolean getControl()
    {
        return true;
    }
}
