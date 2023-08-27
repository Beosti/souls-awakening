package com.yuanno.soulsawakening.events;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.SoulboundItemHelper;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ZanpakutoEvent {

    @SubscribeEvent
    public static void onZanpakutoCraftin(PlayerEvent.ItemCraftedEvent event)
    {
        if (event.getCrafting().getItem() == ModItems.ZANPAKUTO.get())
        {
            SoulboundItemHelper.setOwner(event.getCrafting().getStack(), event.getPlayer());
            ZanpakutoItem zanpakutoItem = (ZanpakutoItem) event.getCrafting().getItem();
            zanpakutoItem.setOwner(event.getPlayer(), event.getCrafting());
        }
    }
}
