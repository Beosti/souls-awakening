package com.yuanno.soulsawakening.abilities.quincy.bow;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IContinuousAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IReleaseArrow;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.entities.projectiles.quincy.PiercingReishiArrow;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

public class PiercingArrowAbility extends Ability implements IContinuousAbility, IReleaseArrow, IRightClickAbility {
    public static final PiercingArrowAbility INSTANCE = new PiercingArrowAbility();


    public PiercingArrowAbility()
    {
        this.setName("Piercing Arrow");
        this.setDescription("Shoots a lightning fast reishi arrow that goes through anything");
        this.setMaxCooldown(32);
        this.setSubCategory(SubCategory.REISHI);
        this.dependency = player -> AbilityDependencies.itemDependence(player, ModItems.GINREI_KOJAKU.get());
    }

    @Override
    public void onLooseArrow(PlayerEntity player, AbilityProjectileEntity abilityProjectileEntity) {
        float extraDamage = abilityProjectileEntity.getAddedDamage();
        PiercingReishiArrow piercingReishiArrow = new PiercingReishiArrow(player.level, player);
        piercingReishiArrow.alterAddedDamage(extraDamage);
        abilityProjectileEntity.remove();
        piercingReishiArrow.shootFromRotation(player, player.xRot, player.yRot, 0, 3, 1);
        player.level.addFreshEntity(piercingReishiArrow);
    }

    @Override
    public boolean getEndAfterUse() {
        return true;
    }

    @Override
    public boolean getShift() {
        return true;
    }
}
