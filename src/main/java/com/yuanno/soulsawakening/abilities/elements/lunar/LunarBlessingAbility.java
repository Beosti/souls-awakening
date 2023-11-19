package com.yuanno.soulsawakening.abilities.elements.lunar;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IRightClickEntityAbility;
import com.yuanno.soulsawakening.entities.projectiles.fire.FireBallProjectile;
import com.yuanno.soulsawakening.init.ModResources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class LunarBlessingAbility extends Ability implements IRightClickEntityAbility {
    public static final LunarBlessingAbility INSTANCE = new LunarBlessingAbility();

    public LunarBlessingAbility()
    {
        this.setName("Lunar Curse");
        this.setCooldown(20);
        this.setMaxCooldown(20);
        this.setPassive(false);
        this.setActivationType(ActivationType.RIGHT_CLICK_ENTITY);
        this.setZanpakutoState(ModResources.STATE.SHIKAI);
    }

    @Override
    public void onRightClickEntity(LivingEntity target, PlayerEntity user)
    {
        user.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 200, 1));
        user.addEffect(new EffectInstance(Effects.DIG_SPEED, 200, 1));

        target.addEffect(new EffectInstance(Effects.WEAKNESS, 200, 1));
        target.addEffect(new EffectInstance(Effects.DIG_SLOWDOWN, 200, 1));

    }
}
