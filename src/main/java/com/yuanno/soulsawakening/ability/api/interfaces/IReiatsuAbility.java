package com.yuanno.soulsawakening.ability.api.interfaces;

import net.minecraft.entity.player.PlayerEntity;

public interface IReiatsuAbility {

    default float addedVariable(PlayerEntity player)
    {
        return 0;
    }
    default float addedVariable()
    {
        return 0;
    }

    default int addedLife(PlayerEntity player)
    {
        return 0;
    }
    default int addedLife()
    {
        return 0;
    }

}
