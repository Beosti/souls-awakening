package com.yuanno.soulsawakening.abilities.elements.normal;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.ISelfEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NormalBuffAbility extends Ability implements IRightClickAbility, ISelfEffect {
    public static final NormalBuffAbility INSTANCE = new NormalBuffAbility();
    public static final EffectInstance DAMAGE = new EffectInstance(Effects.DAMAGE_BOOST, 400, 3);
    public static final EffectInstance ATTACK_SPEED = new EffectInstance(Effects.DIG_SPEED, 400, 3);
    public static final EffectInstance SPEED = new EffectInstance(Effects.MOVEMENT_SPEED, 400, 3);

    List<EffectInstance> effectInstances = new ArrayList<>(
            Arrays.asList(DAMAGE, ATTACK_SPEED, SPEED)
    );
    public NormalBuffAbility()
    {
        this.setName("Normal Buff");
        this.setDescription("Buggs your damage, attack speed and movement speed a lot for a few seconds");
        this.setMaxCooldown(60);
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public ArrayList<EffectInstance> getEffectInstances() {
        return (ArrayList<EffectInstance>) effectInstances;
    }
}
