package com.yuanno.soulsawakening.abilities.elements.normal;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IContinuousAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.ISelfEffect;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class NormalBuffAbility extends Ability implements IRightClickAbility, IContinuousAbility {
    public static final NormalBuffAbility INSTANCE = new NormalBuffAbility();

    AttributeModifier damageModifier = new AttributeModifier(UUID.fromString("b1be259a-1c6d-11ef-9262-0242ac120002"), "Normal damage", 20, AttributeModifier.Operation.ADDITION);
    AttributeModifier attackSpeedModifier = new AttributeModifier(UUID.fromString("b77d7cd8-1c6d-11ef-9262-0242ac120002"), "Normal attack speed", 2, AttributeModifier.Operation.ADDITION);
    AttributeModifier movementSpeedModifier = new AttributeModifier(UUID.fromString("bb4dcac0-1c6d-11ef-9262-0242ac120002"), "Normal movement", 0.4, AttributeModifier.Operation.ADDITION);


    public NormalBuffAbility()
    {
        this.setName("Normal Buff");
        this.setDescription("Buffs your damage, attack speed and movement speed a lot for a few seconds");
        this.setMaxCooldown(60);
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public boolean startContinuity(PlayerEntity player) {
        player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(damageModifier);
        player.getAttribute(Attributes.ATTACK_SPEED).addTransientModifier(attackSpeedModifier);
        player.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(movementSpeedModifier);
        return true;
    }

    @Override
    public boolean endContinuity(PlayerEntity player) {
        player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(damageModifier);
        player.getAttribute(Attributes.ATTACK_SPEED).removeModifier(attackSpeedModifier);
        player.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(movementSpeedModifier);

        return true;
    }
}
