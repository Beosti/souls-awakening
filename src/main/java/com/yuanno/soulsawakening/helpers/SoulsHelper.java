package com.yuanno.soulsawakening.helpers;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import net.minecraft.entity.player.PlayerEntity;

public class SoulsHelper {

    public static boolean hasCategoryAbility(PlayerEntity player, Ability.Category category)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        for (int i = 0; i < abilityData.getUnlockedAbilities().size(); i++)
        {
            if (abilityData.getUnlockedAbilities().get(i).getCategory().equals(category))
                return true;
        }
        return false;
    }
}
