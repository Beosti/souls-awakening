package com.yuanno.soulsawakening.ability.api.interfaces;


/**
 * Used for abilities that right click (with item or without) but not on an entity
 * Something then happens with the player
 */
public interface IRightClickAbility {


    default boolean getShift() {
        return false;
    }
}
