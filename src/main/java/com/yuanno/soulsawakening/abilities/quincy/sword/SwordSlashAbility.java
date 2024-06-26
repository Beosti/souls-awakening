package com.yuanno.soulsawakening.abilities.quincy.sword;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.entities.projectiles.quincy.ReishiSlashProjectile;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;

public class SwordSlashAbility extends Ability implements IShootAbility, IRightClickAbility {
    public static final SwordSlashAbility INSTANCE = new SwordSlashAbility();

    public SwordSlashAbility()
    {
        this.setName("Sword Slash");
        this.setDescription("Shoots a short range air slash");
        this.setMaxCooldown(16);
        this.dependency = player -> AbilityDependencies.itemDependence(player, ModItems.SWORD_REISHI.get());
        this.setSubCategory(SubCategory.SPIRIT_WEAPON);
    }


    @Override
    public AbilityProjectileEntity getProjectile(PlayerEntity player) {
        return new ReishiSlashProjectile(player.level, player);
    }

    @Override
    public boolean getAlt() {
        return true;
    }
}
