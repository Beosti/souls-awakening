package com.yuanno.soulsawakening.abilities.elements.lunar;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public class LunarBlessingAbility extends Ability implements IRightClickAbility, IContinuousAbility {
    public static final LunarBlessingAbility INSTANCE = new LunarBlessingAbility();
    AttributeModifier damageModifier = new AttributeModifier(UUID.fromString("0dc1e980-1c6e-11ef-9262-0242ac120002"), "Lunar blessing", 4, AttributeModifier.Operation.ADDITION);
    AttributeModifier attackSpeedModifier = new AttributeModifier(UUID.fromString("10a1f942-1c6e-11ef-9262-0242ac120002"), "Lunar attack speed", 1.5, AttributeModifier.Operation.ADDITION);
    AttributeModifier movementSpeedModifier = new AttributeModifier(UUID.fromString("15b02684-1c6e-11ef-9262-0242ac120002"), "Lunar movement", 0.15, AttributeModifier.Operation.ADDITION);
    public LunarBlessingAbility()
    {
        this.setName("Lunar Blessing");
        this.setDescription("Grants you buffs");
        this.setMaxCooldown(30);
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

    @Override
    public boolean getShift() {
        return true;
    }

    @Override
    public int getMaxTimer() {
        return 400;
    }
}
