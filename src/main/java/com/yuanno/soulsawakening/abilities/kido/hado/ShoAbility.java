package com.yuanno.soulsawakening.abilities.kido.hado;

import com.yuanno.soulsawakening.ability.api.KidoAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IReiatsuAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IShootAbility;
import com.yuanno.soulsawakening.entities.projectiles.kido.ShoProjectile;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

// blasts the entity a few meters away
public class ShoAbility extends KidoAbility implements IShootAbility, IReiatsuAbility {
    public static final ShoAbility INSTANCE = new ShoAbility();

    public ShoAbility() {
        this.setName("Sho");
        this.setDescription("Shoots a projectile pushing away entities");
        this.setMaxCooldown(10);
        this.setIncantation("Push back, repel the vile knave! Hadou number 1 Sho");
        this.setSubCategory(SubCategory.HADO);
    }

    @Override
    public AbilityProjectileEntity getProjectile(PlayerEntity player) {
        return new ShoProjectile(player.level, player);
    }
}
