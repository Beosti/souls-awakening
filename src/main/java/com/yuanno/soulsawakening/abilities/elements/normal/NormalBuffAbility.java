package com.yuanno.soulsawakening.abilities.elements.normal;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.ISelfEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class NormalBuffAbility extends Ability implements IRightClickAbility, ISelfEffect {
    public static final NormalBuffAbility INSTANCE = new NormalBuffAbility();

    public NormalBuffAbility()
    {
        this.setName("Normal Buff");
        this.setMaxCooldown(20);
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public void otherEffects(PlayerEntity player)
    {
        player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 400, 3));
        player.addEffect(new EffectInstance(Effects.DIG_SPEED, 400, 3));
    }
}
