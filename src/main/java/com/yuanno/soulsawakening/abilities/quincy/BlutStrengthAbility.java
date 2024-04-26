package com.yuanno.soulsawakening.abilities.quincy;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.ISelfEffect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlutStrengthAbility extends Ability implements IRightClickAbility, ISelfEffect {
    public static final BlutStrengthAbility INSTANCE = new BlutStrengthAbility();
    public static final EffectInstance DAMAGE_BOOST = new EffectInstance(Effects.DAMAGE_BOOST, 400, 1);
    public static final EffectInstance ATTACK_SPEED = new EffectInstance(Effects.DIG_SPEED, 400, 1);
    public static final EffectInstance MOVEMENT_SPEED = new EffectInstance(Effects.MOVEMENT_SPEED, 400, 1);

    List<EffectInstance> effectInstances = new ArrayList<>(
            Arrays.asList(DAMAGE_BOOST, ATTACK_SPEED, MOVEMENT_SPEED)
    );
    public BlutStrengthAbility()
    {
        this.setName("Blut Strength");
        this.setDescription("Get a big buff due to your blut for 20 seconds");
        this.setMaxCooldown(60);
        this.setSubCategory(SubCategory.REISHI);
    }

    @Override
    public ArrayList<EffectInstance> getEffectInstances() {
        return (ArrayList<EffectInstance>) effectInstances;
    }
}
