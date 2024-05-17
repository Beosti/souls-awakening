package com.yuanno.soulsawakening.abilities.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;

public class AbilityDependencies {

    public static boolean itemDependence(PlayerEntity player, Item item)
    {
        return player.getMainHandItem().getItem().equals(item);
    }
}
