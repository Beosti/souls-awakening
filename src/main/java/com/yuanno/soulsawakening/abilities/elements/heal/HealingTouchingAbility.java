package com.yuanno.soulsawakening.abilities.elements.heal;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IEntityRayTrace;
import com.yuanno.soulsawakening.ability.api.interfaces.IReiatsuAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class HealingTouchingAbility extends Ability implements IEntityRayTrace, IRightClickAbility, IReiatsuAbility {
    public static final HealingTouchingAbility INSTANCE = new HealingTouchingAbility();

    public HealingTouchingAbility()
    {
        this.setName("Healing Touch");
        this.setMaxCooldown(20);
        this.setSubCategory(SubCategory.SHIKAI);
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
}
