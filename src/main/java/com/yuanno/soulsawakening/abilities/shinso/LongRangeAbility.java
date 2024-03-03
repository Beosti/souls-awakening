package com.yuanno.soulsawakening.abilities.shinso;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IPunchAbility;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public class LongRangeAbility extends Ability implements IPunchAbility {
    public static final LongRangeAbility INSTANCE = new LongRangeAbility();
    private static final AttributeModifier SHINSO_RANGE = new AttributeModifier(UUID.fromString("25cc6f40-a5a5-11ee-a506-0242ac120002"),
            "Shinso range", 20f, AttributeModifier.Operation.ADDITION);
    public LongRangeAbility()
    {
        this.setName("Long Range");
        this.setCooldown(10);
        this.setMaxCooldown(10);
        this.setPassive(false);
        this.setActivationType(ActivationType.ATTACK);
        this.setCategory(Category.ZANPAKUTO);
    }

    public void startContinuity(PlayerEntity player) {
        if (!player.getAttribute(ModAttributes.ATTACK_RANGE.get()).hasModifier(SHINSO_RANGE)) {
            player.getAttribute(ModAttributes.ATTACK_RANGE.get()).addTransientModifier(SHINSO_RANGE);
            System.out.println("GOT RANGE");
        }
    }
    public void onHitEntity(LivingEntity target, PlayerEntity user) {
        System.out.println("HIT AN ENTITY");
    }
    public void startCooldown(PlayerEntity player)
    {
        if (player.getAttribute(ModAttributes.ATTACK_RANGE.get()).hasModifier(SHINSO_RANGE)) {
            player.getAttribute(ModAttributes.ATTACK_RANGE.get()).removeModifier(SHINSO_RANGE);
            System.out.println("REMOVED RANGE");
        }

    }
}
