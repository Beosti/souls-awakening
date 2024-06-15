package com.yuanno.soulsawakening.abilities.elements.fire;

import com.yuanno.soulsawakening.api.ModEffect;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.IContinuousAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IGetHitAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.init.ModAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.UUID;

public class FireCoatAbility extends Ability implements IRightClickAbility, IContinuousAbility, IGetHitAbility {
    public static final FireCoatAbility INSTANCE = new FireCoatAbility();
    AttributeModifier damageModifier = new AttributeModifier(UUID.fromString("d370b8c0-2b3f-11ef-9454-0242ac120002"), "Fire Coat", 0.2, AttributeModifier.Operation.ADDITION);

    public FireCoatAbility()
    {
        this.setName("Fire Coat");
        this.setDescription("Cloaks yourself with fire, reduce incoming damage and put on the fire the attacker");
        this.setMaxCooldown(30);
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public boolean startContinuity(PlayerEntity player) {
        player.getAttribute(ModAttributes.DAMAGE_REDUCTION.get()).addTransientModifier(damageModifier);
        return true;
    }

    @Override
    public void duringContinuity(PlayerEntity player) {
        player.setSecondsOnFire(1);
        player.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 30, 0));
    }

    @Override
    public boolean endContinuity(PlayerEntity player) {
        player.getAttribute(ModAttributes.DAMAGE_REDUCTION.get()).removeModifier(damageModifier);

        return true;
    }

    @Override
    public void getHit(PlayerEntity player, LivingEntity attacker) {
        attacker.setSecondsOnFire(5);
    }

    @Override
    public boolean getShift() {
        return true;
    }

    @Override
    public int getMaxTimer() {
        return 400;
    }
}
