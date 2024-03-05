package com.yuanno.soulsawakening.ability.api.interfaces;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.events.ability.CustomInteractionEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

/**
 * Used for abilities that target a block, or an entity, if it's an entity it does something at the block position of that entity. 
 * Handled in {@link #onBlockRayTrace(PlayerEntity, Ability)}, triggered here:
 * @see com.yuanno.soulsawakening.events.ability.AbilityEvents#customRightClickLogic(CustomInteractionEvent) 
 * {@link #getDistance()} max distance block targeted
 * {@link #somethingAtDistance(PlayerEntity, BlockPos)} to handle quite everything, there's 101 things you can do so let the player handle it
 */
public interface IBlockRayTrace {

    default void onBlockRayTrace(PlayerEntity player, Ability ability) {
        RayTraceResult rayTraceResult = null;
        if (ability instanceof IReiatsuAbility)
            rayTraceResult = Beapi.rayTraceBlocksAndEntities(player, getDistance() + ((IReiatsuAbility) ability).addedVariable(player));
        else
            rayTraceResult = Beapi.rayTraceBlocksAndEntities(player, getDistance());
        BlockPos blockPos = new BlockPos(rayTraceResult.getLocation());
        somethingAtDistance(player, blockPos);
    }
    default int getDistance()
    {
        return 0;
    }


    default void somethingAtDistance(PlayerEntity player, BlockPos blockPos)
    {

    }
}
