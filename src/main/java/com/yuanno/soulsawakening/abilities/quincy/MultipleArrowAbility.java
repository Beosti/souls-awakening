package com.yuanno.soulsawakening.abilities.quincy;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IContinuousAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IReleaseArrow;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.entities.projectiles.quincy.ReishiArrow;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

public class MultipleArrowAbility extends Ability implements IContinuousAbility, IReleaseArrow, IRightClickAbility {
    public static final MultipleArrowAbility INSTANCE = new MultipleArrowAbility();

    public MultipleArrowAbility()
    {
        this.setName("Multiple Arrow");
        this.setDescription("Shoots multiple reishi arrows with low accuracy");
        this.setMaxCooldown(12);
        this.setSubCategory(SubCategory.REISHI);
    }

    // TODO make an IRepeater interface for handling of doing 1 thing multiple times through a continuous abiltity
    @Override
    public void onLooseArrow(PlayerEntity player, AbilityProjectileEntity abilityProjectileEntity, float power) {
        if (power != 1)
            return;
        abilityProjectileEntity.remove();
        for (int i = 0; i < 10; i++)
        {
            ReishiArrow newReishiArrow = new ReishiArrow(player.level, player);
            newReishiArrow.alterDamage(abilityProjectileEntity.getAddedDamage());
            newReishiArrow.shootFromRotation(player, player.xRot, player.yRot, 0, 3, 5);
            player.level.addFreshEntity(newReishiArrow);
        }
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
