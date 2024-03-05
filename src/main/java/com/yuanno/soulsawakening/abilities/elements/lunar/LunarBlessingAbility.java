package com.yuanno.soulsawakening.abilities.elements.lunar;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IEntityRayTrace;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.ISelfEffect;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LunarBlessingAbility extends Ability implements IRightClickAbility, ISelfEffect {
    public static final LunarBlessingAbility INSTANCE = new LunarBlessingAbility();
    public static final EffectInstance DAMAGE_BOOST = new EffectInstance(Effects.DAMAGE_BOOST, 200, 1);
    public static final EffectInstance ATTACK_SPEED = new EffectInstance(Effects.DIG_SPEED, 200, 1);
    List<EffectInstance> effectInstances = new ArrayList<>(
            Arrays.asList(DAMAGE_BOOST, ATTACK_SPEED)
    );
    public LunarBlessingAbility()
    {
        this.setName("Lunar Curse");
        this.setDescription("Sucks life energy from the target, granting you buffs and the target debuffs");
        this.setMaxCooldown(20);
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public ArrayList<EffectInstance> getEffectInstances() {
        return (ArrayList<EffectInstance>) effectInstances;
    }
}
