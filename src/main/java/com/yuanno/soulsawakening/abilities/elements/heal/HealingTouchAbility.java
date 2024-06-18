package com.yuanno.soulsawakening.abilities.elements.heal;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class HealingTouchAbility extends Ability implements IEntityRayTrace, IRightClickAbility, IReiatsuAbility {
    public static final HealingTouchAbility INSTANCE = new HealingTouchAbility();

    public HealingTouchAbility()
    {
        this.setName("Healing Touch");
        this.setMaxCooldown(20);
        this.setSubCategory(SubCategory.SHIKAI);
        this.dependency = AbilityDependencies::shikaiDependance;
    }
    @Override
    public int getDistance()
    {
        return 3;
    }
    @Override
    public float healAmount() {
        return 6;
    }
    @Override
    public float addedVariable(PlayerEntity player) {
        return (float) EntityStatsCapability.get(player).getReiatsuPoints()/4;
    }
    @Override
    public EffectInstance addEffect()
    {
        return new EffectInstance(Effects.ABSORPTION, 120, 0);
    }

    @Override
    public boolean getControl() {
        return true;
    }
}
