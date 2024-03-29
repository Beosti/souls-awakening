package com.yuanno.soulsawakening.abilities.elements.shadow;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.ISelfEffect;
import com.yuanno.soulsawakening.init.ModEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;

public class UmbralCloakAbility extends Ability implements IRightClickAbility, ISelfEffect {
    public static final UmbralCloakAbility INSTANCE = new UmbralCloakAbility();

    public UmbralCloakAbility()
    {
        this.setName("Umbral Cloak");
        this.setMaxCooldown(16);
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public EffectInstance getEffectInstance()
    {
        return new EffectInstance(ModEffects.VANISH_INVISIBILITY.get(), 200, 2, false, false);
    }
    @Override
    public boolean getShift()
    {
        return true;
    }
}
