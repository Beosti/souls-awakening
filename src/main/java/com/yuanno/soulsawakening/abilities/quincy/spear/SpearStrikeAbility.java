package com.yuanno.soulsawakening.abilities.quincy.spear;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.init.ModItems;
import net.minecraft.entity.player.PlayerEntity;

public class SpearStrikeAbility extends Ability implements IContinuousAbility, IRightClickAbility {
    public static final SpearStrikeAbility INSTANCE = new SpearStrikeAbility();
    public SpearStrikeAbility()
    {
        this.setName("Spear Strike");
        this.setDescription("The next attack will cause your opponent to lose movement speed and armor for a while");
        this.setMaxCooldown(16);
        this.dependency = player -> AbilityDependencies.itemDependence(player, ModItems.SPEAR_REISHI.get());
        this.setSubCategory(SubCategory.SPIRIT_WEAPON);
    }

    public boolean dependence(PlayerEntity player)
    {
        if (player.getMainHandItem().getItem().asItem().equals(ModItems.SPEAR_REISHI.get()))
            return true;
        else
            return false;
    }

    @Override
    public boolean getAlt() {
        return true;
    }
}
