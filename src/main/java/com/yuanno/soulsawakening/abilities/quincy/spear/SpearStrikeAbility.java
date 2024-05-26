package com.yuanno.soulsawakening.abilities.quincy.spear;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IContinuousAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.init.ModItems;
import net.minecraft.entity.player.PlayerEntity;

public class SpearStrikeAbility extends Ability implements IContinuousAbility, IRightClickAbility {
    public static final SpearStrikeAbility INSTANCE = new SpearStrikeAbility();
    public SpearStrikeAbility()
    {
        this.setName("Spear Strike");
        this.setDescription("The next attack will cause your opponent to lose movement speed and armor for a while");
        this.setMaxCooldown(16);
        this.dependency = this::dependence;
        this.setSubCategory(SubCategory.REISHI);
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
