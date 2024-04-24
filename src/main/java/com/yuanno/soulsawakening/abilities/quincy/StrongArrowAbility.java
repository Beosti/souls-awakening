package com.yuanno.soulsawakening.abilities.quincy;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IContinuousAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IReleaseArrow;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.entities.projectiles.quincy.BigReishiArrow;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

public class StrongArrowAbility extends Ability implements IContinuousAbility, IReleaseArrow, IRightClickAbility {
    public static final StrongArrowAbility INSTANCE = new StrongArrowAbility();


    public StrongArrowAbility()
    {
        this.setName("Strong Arrow");
        this.setDescription("Shoots a bigger reishi arrow when this ability is active");
        this.setMaxCooldown(12);
        this.setSubCategory(SubCategory.REISHI);
    }

    @Override
    public void onLooseArrow(PlayerEntity player, AbilityProjectileEntity abilityProjectileEntity, float power) {
        if (power != 1)
            return;
        float extraDamage = abilityProjectileEntity.getAddedDamage();
        BigReishiArrow bigReishiArrow = new BigReishiArrow(player.level, player);
        bigReishiArrow.alterAddedDamage(extraDamage);
        abilityProjectileEntity.remove();
        bigReishiArrow.shootFromRotation(player, player.xRot, player.yRot, 0, 3, 1);
        player.level.addFreshEntity(bigReishiArrow);
    }

    @Override
    public boolean getEndAfterUse() {
        return true;
    }

    @Override
    public boolean getAlt() {
        return true;
    }
}
