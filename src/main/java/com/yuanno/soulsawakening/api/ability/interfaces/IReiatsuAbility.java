package com.yuanno.soulsawakening.api.ability.interfaces;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;

/**
 * Used for abilities that use reiatsu and thus can be scalable, it doesn't handle any logics but gives variables
 * It is used in other interfaces to change variables around {@link IBlockRayTrace}, {@link IEntityRayTrace}, {@link ISelfEffect}, {@link IShootAbility}, {@link IWaveAbility}
 * Also displays the modified variables
 * @see com.yuanno.soulsawakening.screens.AbilityListScreen#render(MatrixStack, int, int, float)
 */
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

    default int reducedCooldown(PlayerEntity player)
    {
        return 0;
    }
    default int reducedCooldown()
    {
        return 0;
    }

}
