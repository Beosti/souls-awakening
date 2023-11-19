package com.yuanno.soulsawakening.abilities.elements.normal;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.init.ModResources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class NormalBuffAbility extends Ability {
    public static final NormalBuffAbility INSTANCE = new NormalBuffAbility();

    public NormalBuffAbility()
    {
        this.setName("Normal Buff");
        this.setCooldown(20);
        this.setMaxCooldown(20);
        this.setPassive(false);
        this.setActivationType(ActivationType.RIGHT_CLICK_EMPTY);
        this.setZanpakutoState(ModResources.STATE.SHIKAI);
    }

    @Override
    public void onRightClick(PlayerEntity user)
    {
        user.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 400, 3));
        user.addEffect(new EffectInstance(Effects.DIG_SPEED, 400, 3));


    }
}
