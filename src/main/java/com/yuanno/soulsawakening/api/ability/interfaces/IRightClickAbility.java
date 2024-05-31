package com.yuanno.soulsawakening.api.ability.interfaces;


import com.yuanno.soulsawakening.events.api.CustomInteractionEvent;
import com.yuanno.soulsawakening.events.ability.RightClickAbilityEvents;

/**
 * Used for abilities that right click (with item or without) but not on an entity
 * Has not much going except {@link #getShift()} to check if it's a right click ability where you need to hold shift
 * Only used to check if the ability needs to be right clicked or not
 * @see RightClickAbilityEvents#customRightClickLogic(CustomInteractionEvent)
 */
public interface IRightClickAbility {


    default boolean getShift() {
        return false;
    }
    default boolean getAlt()
    {
        return false;
    }
    default boolean getControl()
    {
        return false;
    }
}
