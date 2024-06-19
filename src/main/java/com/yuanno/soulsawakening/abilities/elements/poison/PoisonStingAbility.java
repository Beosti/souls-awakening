package com.yuanno.soulsawakening.abilities.elements.poison;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.IAttackAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IContinuousAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.init.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.UUID;

public class PoisonStingAbility extends Ability implements IContinuousAbility, IAttackAbility, IRightClickAbility {
    public static final PoisonStingAbility INSTANCE = new PoisonStingAbility();

    AttributeModifier rangeModifier = new AttributeModifier(UUID.fromString("4d8f8314-2dc7-11ef-9454-0242ac120002"), "Poison sting", 4, AttributeModifier.Operation.ADDITION);


    public PoisonStingAbility()
    {
        this.setName("Poison Sting");
        this.setDescription("Gives you range for your next hit, heavily poisoning the target hit");
        this.setMaxCooldown(20);
        this.dependency = AbilityDependencies::shikaiDependance;
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public boolean startContinuity(PlayerEntity player) {
        if (!player.getAttribute(ModAttributes.ATTACK_RANGE.get()).hasModifier(rangeModifier))
            player.getAttribute(ModAttributes.ATTACK_RANGE.get()).addTransientModifier(rangeModifier);
        return true;
    }

    @Override
    public boolean endContinuity(PlayerEntity player) {
        if (player.getAttribute(ModAttributes.ATTACK_RANGE.get()).hasModifier(rangeModifier))
            player.getAttribute(ModAttributes.ATTACK_RANGE.get()).removeModifier(rangeModifier);
        return true;
    }

    @Override
    public void activate(PlayerEntity player, LivingEntity target) {
        target.addEffect(new EffectInstance(Effects.POISON, 80, 2));
    }

    @Override
    public boolean getControl() {
        return true;
    }
}
