package com.yuanno.soulsawakening.abilities.quincy;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public class BlutStrengthAbility extends Ability implements IRightClickAbility, IContinuousAbility {
    public static final BlutStrengthAbility INSTANCE = new BlutStrengthAbility();

    AttributeModifier damageModifier = new AttributeModifier(UUID.fromString("009a2b14-16e2-11ef-9262-0242ac120002"), "Blut damage", 5, AttributeModifier.Operation.ADDITION);
    AttributeModifier attackSpeedModifier = new AttributeModifier(UUID.fromString("fe7e8186-16e1-11ef-9262-0242ac120002"), "Blut attack speed", 1, AttributeModifier.Operation.ADDITION);
    AttributeModifier movementSpeedModifier = new AttributeModifier(UUID.fromString("fbb32fc4-16e1-11ef-9262-0242ac120002"), "Blut movement", 0.2, AttributeModifier.Operation.ADDITION);

    public BlutStrengthAbility()
    {
        this.setName("Blut Strength");
        this.setDescription("Get a big buff due to your blut for 20 seconds");
        this.setMaxCooldown(60);
        this.setSubCategory(SubCategory.BLUT);
    }

    @Override
    public boolean getControl() {
        return true;
    }

    @Override
    public int getMaxTimer() {
        return 400;
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
