package com.yuanno.soulsawakening.events;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.SoulboundItemHelper;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ZanpakutoCraftEvent {

    @SubscribeEvent
    public static void onZanpakutoCraftin(PlayerEvent.ItemCraftedEvent event)
    {
        if (event.getCrafting().getItem() == ModItems.ZANPAKUTO.get())
        {
            SoulboundItemHelper.setOwner(event.getCrafting().getStack(), event.getPlayer());
            ZanpakutoItem zanpakutoItem = (ZanpakutoItem) event.getCrafting().getItem();
            zanpakutoItem.setOwner(event.getPlayer(), event.getCrafting());
            IEntityStats entityStats = EntityStatsCapability.get(event.getPlayer());
            if (entityStats.getRace().equals(ModValues.SPIRIT))
                entityStats.setRace(ModValues.SHINIGAMI);
        }
    }
}
