package com.yuanno.soulsawakening.abilities.kido.hado;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.KidoAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.entities.projectiles.kido.hado.ByakuraiProjectile;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

// blasts the entity a few meters away
public class ByakuraiAbility extends KidoAbility implements IShootAbility, IReiatsuAbility {
    public static final ByakuraiAbility INSTANCE = new ByakuraiAbility();

    public ByakuraiAbility()
    {
        this.setName("Byakurai");
        this.setDescription("Shoots a focuses reishi energy beam");
        this.setMaxCooldown(10);
        this.setIncantation("Oh ye, pale lightning may you smitten thy enemy as thy Hadou number 4 Byakurai");
        this.setSubCategory(SubCategory.HADO);
    }

    @Override
    public float addedVariable(PlayerEntity player) {
        return (float) EntityStatsCapability.get(player).getReiatsuPoints();
    }

    @Override
    public AbilityProjectileEntity getProjectile(PlayerEntity player) {
        return new ByakuraiProjectile(player.level, player);
    }
}
