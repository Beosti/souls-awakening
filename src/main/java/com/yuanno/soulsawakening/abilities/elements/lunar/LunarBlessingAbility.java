package com.yuanno.soulsawakening.abilities.elements.lunar;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IContinuousAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IEntityRayTrace;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.ISelfEffect;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        this.setMaxCooldown(20);
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
