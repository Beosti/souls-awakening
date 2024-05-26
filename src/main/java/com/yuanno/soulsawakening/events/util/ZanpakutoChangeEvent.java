package com.yuanno.soulsawakening.events.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.Event;

public class ZanpakutoChangeEvent extends Event {
    private ItemStack zanpakutoItem;
    private PlayerEntity player;

    public ZanpakutoChangeEvent(PlayerEntity player)
    {
        this.player = player;
        this.zanpakutoItem =  player.getMainHandItem();
    }


    public PlayerEntity getPlayer()
    {
        return this.player;
    }

    public ItemStack getZanpakutoItem()
    {
        return zanpakutoItem;
    }

}
