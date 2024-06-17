package com.yuanno.soulsawakening.abilities.util;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AbilityDependencies {

    public static boolean itemDependence(PlayerEntity player, Item item)
    {
        return player.getMainHandItem().getItem().equals(item);
    }
    public static boolean shikaiDependance(PlayerEntity player)
    {
        ItemStack zanpakutoItem = player.getMainHandItem();
        if (!zanpakutoItem.getItem().equals(ModItems.ZANPAKUTO.get().getItem()))
            return false;
        String state = zanpakutoItem.getTag().getString("zanpakutoState");
        if (!state.equals(ModValues.STATE.SHIKAI.name())) // if your item is in shikai state you can use it
            return false;
        return true;
    }
}
