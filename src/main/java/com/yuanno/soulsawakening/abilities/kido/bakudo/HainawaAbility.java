package com.yuanno.soulsawakening.abilities.kido.bakudo;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.KidoAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.entities.projectiles.kido.bakuro.HainawaProjectile;
import com.yuanno.soulsawakening.entities.projectiles.kido.hado.ByakuraiProjectile;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

// blasts the entity a few meters away
public class HainawaAbility extends KidoAbility implements IShootAbility, IReiatsuAbility {
    public static final HainawaAbility INSTANCE = new HainawaAbility();

    public HainawaAbility()
    {
        this.setName("Hainawa");
        this.setDescription("Shoots a lasso of concentrated reishi, if hit the entity cannot move");
        this.setMaxCooldown(10);
        this.setIncantation("May thy crawling rope catch thy enemy Bakudo number 4 hainawa");
        this.setSubCategory(SubCategory.BAKUDO);
    }

    @Override
    public float addedVariable(PlayerEntity player) {
        return (float) EntityStatsCapability.get(player).getReiatsuPoints();
    }

    @Override
    public AbilityProjectileEntity getProjectile(PlayerEntity player) {
        return new HainawaProjectile(player.level, player);
    }
}
