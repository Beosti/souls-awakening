package com.yuanno.soulsawakening.abilities.elements.thunder;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IAttackAbility;
import com.yuanno.soulsawakening.init.ModEffects;
import com.yuanno.soulsawakening.init.ModResources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class ThunderAttackAbility extends Ability implements IAttackAbility {
    public static final ThunderAttackAbility INSTANCE = new ThunderAttackAbility();


    public ThunderAttackAbility() {
        this.setName("Thunder Attack");
        this.setPassive(true);
        this.setActivationType(ActivationType.SHIFT_RIGHT_CLICK);
        this.setZanpakutoState(ModResources.STATE.SHIKAI);
    }

    @Override
    public void activate(LivingEntity livingEntityTarget, PlayerEntity player)
    {
        if (!livingEntityTarget.hasEffect(ModEffects.ELECTROCUTED.get()))
            livingEntityTarget.addEffect(new EffectInstance(ModEffects.ELECTROCUTED.get(), 40, 0));
    }
}
