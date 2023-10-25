package com.yuanno.soulsawakening.events;

import com.yuanno.soulsawakening.init.ModResources;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class ZanpakutoChangeEvent extends Event {
    private ZanpakutoItem zanpakutoItem;
    private PlayerEntity player;

    public ZanpakutoChangeEvent(PlayerEntity player, ZanpakutoItem zanpakutoItem)
    {
        this.player = player;
        this.zanpakutoItem = zanpakutoItem;
    }

    public ZanpakutoItem getZanpakutoItem()
    {
        return this.zanpakutoItem;
    }

    public PlayerEntity getPlayer()
    {
        return this.player;
    }

    public ModResources.STATE getZanpakutoState()
    {
        return zanpakutoItem.getZanpakutoState();
    }

    public ZanpakutoItem.ELEMENT getZanpakutoElement()
    {
        return zanpakutoItem.getZanpakutoElement();
    }
}
