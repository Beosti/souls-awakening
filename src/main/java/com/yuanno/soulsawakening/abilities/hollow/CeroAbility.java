package com.yuanno.soulsawakening.abilities.hollow;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IRightClickEmptyAbility;
import com.yuanno.soulsawakening.entities.projectiles.hollow.CeroProjectile;
import net.minecraft.entity.player.PlayerEntity;

public class CeroAbility extends Ability implements IRightClickEmptyAbility {
    public static final CeroAbility INSTANCE = new CeroAbility();

    public CeroAbility()
    {
        this.setName("Cero");
        this.setCooldown(20);
        this.setMaxCooldown(20);
        this.setPassive(false);
        this.setActivationType(ActivationType.RIGHT_CLICK_EMPTY);
    }

    @Override
    public void onRightClick(PlayerEntity user)
    {
        CeroProjectile ceroProjectile = new CeroProjectile(user.level, user);
        user.level.addFreshEntity(ceroProjectile);
        ceroProjectile.shootFromRotation(user, user.xRot, user.yRot, 0, 2f, 1);
    }
}
