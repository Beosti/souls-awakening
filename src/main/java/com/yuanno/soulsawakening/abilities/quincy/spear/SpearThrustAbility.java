package com.yuanno.soulsawakening.abilities.quincy.spear;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.init.ModEffects;
import com.yuanno.soulsawakening.init.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;

public class SpearThrustAbility extends Ability implements IContinuousAbility, IRightClickAbility {
    public static final SpearThrustAbility INSTANCE = new SpearThrustAbility();
    public SpearThrustAbility()
    {
        this.setName("Spear Thrust");
        this.setDescription("The next attack you will do with your spear will have way more range");
        this.setMaxCooldown(16);
        this.dependency = player -> AbilityDependencies.itemDependence(player, ModItems.SPEAR_REISHI.get());
        this.setSubCategory(SubCategory.SPIRIT_WEAPON);
    }

    @Override
    public boolean getShift() {
        return true;
    }

    @Override
    public boolean startContinuity(PlayerEntity player) {
        player.addEffect(new EffectInstance(ModEffects.SPEAR_THRUST.get(), 100000, 0));
        return true;
    }

    @Override
    public boolean endContinuity(PlayerEntity player) {
        player.removeEffect(ModEffects.SPEAR_THRUST.get());
        return true;
    }
}
