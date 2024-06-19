package com.yuanno.soulsawakening.abilities.elements.thunder;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.IContinuousAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.init.ModAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public class ThunderBoostAbility extends Ability implements IContinuousAbility, IRightClickAbility {
    public static final ThunderBoostAbility INSTANCE = new ThunderBoostAbility();

    AttributeModifier speedModifier = new AttributeModifier(UUID.fromString("35d8259a-2e22-11ef-9454-0242ac120002"), "Thunder boost speed", 0.2, AttributeModifier.Operation.ADDITION);
    AttributeModifier attackSpeedModifier = new AttributeModifier(UUID.fromString("45588046-2e22-11ef-9454-0242ac120002"), "Thunder boost attack speed", 1, AttributeModifier.Operation.ADDITION);
    AttributeModifier heightStepModifier = new AttributeModifier(UUID.fromString("51e5b1c6-2e22-11ef-9454-0242ac120002"), "Thunder boost height step", 1, AttributeModifier.Operation.ADDITION);


    public ThunderBoostAbility()
    {
        this.setName("Thunder Boost");
        this.setDescription("Boost yourself with lightning, making you gaster");
        this.setMaxCooldown(30);
        this.dependency = AbilityDependencies::shikaiDependance;
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public boolean startContinuity(PlayerEntity player) {
        player.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(speedModifier);
        player.getAttribute(Attributes.ATTACK_SPEED).addTransientModifier(attackSpeedModifier);
        player.getAttribute(ModAttributes.STEP_HEIGHT.get()).addTransientModifier(heightStepModifier);
        return true;
    }

    @Override
    public boolean endContinuity(PlayerEntity player) {
        player.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(speedModifier);
        player.getAttribute(Attributes.ATTACK_SPEED).removeModifier(attackSpeedModifier);
        player.getAttribute(ModAttributes.STEP_HEIGHT.get()).removeModifier(heightStepModifier);
        return true;
    }

    @Override
    public int getMaxTimer() {
        return 200;
    }
}
