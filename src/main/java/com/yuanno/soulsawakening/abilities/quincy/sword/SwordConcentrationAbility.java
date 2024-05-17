package com.yuanno.soulsawakening.abilities.quincy.sword;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IContinuousAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IShootAbility;
import com.yuanno.soulsawakening.entities.projectiles.quincy.ReishiSlashProjectile;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

public class SwordConcentrationAbility extends Ability implements IRightClickAbility, IContinuousAbility {
    public static final SwordConcentrationAbility INSTANCE = new SwordConcentrationAbility();

    public SwordConcentrationAbility()
    {
        this.setName("Sword Concentration");
        this.setDescription("Concentrates more reishi into your sword, making it deal more damage");
        this.setMaxCooldown(16);
        this.dependency = player -> AbilityDependencies.itemDependence(player, ModItems.SWORD_REISHI.get());
        this.setSubCategory(SubCategory.REISHI);
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
    public boolean endContinuity(PlayerEntity player) {
        return IContinuousAbility.super.endContinuity(player);
    }

    @Override
    public boolean startContinuity(PlayerEntity player) {
        return IContinuousAbility.super.startContinuity(player);
    }
}
