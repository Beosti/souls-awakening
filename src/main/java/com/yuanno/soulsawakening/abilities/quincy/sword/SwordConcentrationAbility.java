package com.yuanno.soulsawakening.abilities.quincy.sword;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public class SwordConcentrationAbility extends Ability implements IRightClickAbility, IContinuousAbility {
    public static final SwordConcentrationAbility INSTANCE = new SwordConcentrationAbility();
    AttributeModifier damageModifier = new AttributeModifier(UUID.fromString("7aeefd7e-14fb-11ef-9262-0242ac120002"), "Sword Concentration", 5, AttributeModifier.Operation.ADDITION);

    public SwordConcentrationAbility()
    {
        this.setName("Sword Concentration");
        this.setDescription("Concentrates more reishi into your sword, making it deal more damage");
        this.setMaxCooldown(16);
        this.dependency = player -> AbilityDependencies.itemDependence(player, ModItems.SWORD_REISHI.get());
        this.setSubCategory(SubCategory.SPIRIT_WEAPON);
    }



    @Override
    public boolean getShift() {
        return true;
    }

    @Override
    public int getMaxTimer() {
        return 120;
    }


    @Override
    public boolean startContinuity(PlayerEntity player) {
        player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(damageModifier);
        return true;
    }

    @Override
    public boolean endContinuity(PlayerEntity player) {
        player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(damageModifier);
        return true;
    }
}
