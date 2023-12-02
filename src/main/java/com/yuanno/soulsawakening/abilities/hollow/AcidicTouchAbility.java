package com.yuanno.soulsawakening.abilities.hollow;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IRightClickEntityAbility;
import com.yuanno.soulsawakening.api.ModEffect;
import com.yuanno.soulsawakening.init.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;

public class AcidicTouchAbility extends Ability implements IRightClickEntityAbility {
    public static final AcidicTouchAbility INSTANCE = new AcidicTouchAbility();

    public AcidicTouchAbility()
    {
        this.setName("Acidic Touch");
        //this.setCooldown(8);
        //this.setMaxCooldown(8);
        this.setPassive(false);
        this.setActivationType(ActivationType.RIGHT_CLICK_ENTITY);
    }

    @Override
    public void onRightClickEntity(LivingEntity target, PlayerEntity user)
    {
        if (!target.hasEffect(ModEffects.HOLLOW_ACID.get()))
            target.addEffect(new EffectInstance(ModEffects.HOLLOW_ACID.get(), 120, 0));
    }
}
