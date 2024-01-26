package com.yuanno.soulsawakening.abilities.elements.normal;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IRightClickEmptyAbility;
import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class NormalBuffAbility extends Ability implements IRightClickEmptyAbility {
    public static final NormalBuffAbility INSTANCE = new NormalBuffAbility();

    public NormalBuffAbility()
    {
        this.setName("Normal Buff");
        this.setCooldown(20);
        this.setMaxCooldown(20);
        this.setPassive(false);
        this.setActivationType(ActivationType.RIGHT_CLICK_EMPTY);
        this.setZanpakutoState(ModValues.STATE.SHIKAI);
        this.setCategory(Category.ZANPAKUTO);
    }

    @Override
    public void onRightClick(PlayerEntity user)
    {
        user.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 400, 3));
        user.addEffect(new EffectInstance(Effects.DIG_SPEED, 400, 3));


    }
}
