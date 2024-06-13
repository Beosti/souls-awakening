package com.yuanno.soulsawakening.abilities.elements.shadow;

import com.yuanno.soulsawakening.api.SourceElement;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.IReiatsuAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IShootAbility;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.entities.projectiles.dark.DarkBallProjectile;
import com.yuanno.soulsawakening.entities.projectiles.fire.FireBallProjectile;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

public class DarkBallAbility extends Ability implements IRightClickAbility, IShootAbility, IReiatsuAbility {
    public static final DarkBallAbility INSTANCE = new DarkBallAbility();

    public DarkBallAbility()
    {
        this.setName("Darkball");
        this.setDescription("Shoots a darkball, on hit gives weakness to the entity");
        this.setMaxCooldown(10);
        this.setSourceElement(SourceElement.SHADOW);
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public AbilityProjectileEntity getProjectile(PlayerEntity player)
    {
        return new DarkBallProjectile(player.level, player);
    }
    @Override
    public boolean getShift()
    {
        return false;
    }
    @Override
    public float addedVariable(PlayerEntity player)
    {
        return (float) EntityStatsCapability.get(player).getReiatsuPoints()/2;
    }
}
