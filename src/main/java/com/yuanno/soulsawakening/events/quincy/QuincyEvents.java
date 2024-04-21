package com.yuanno.soulsawakening.events.quincy;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.entity.quincy.QuincyStats;
import com.yuanno.soulsawakening.events.api.CustomInteractionEvent;
import com.yuanno.soulsawakening.init.ModAdvancements;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.items.DangleItem;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import com.yuanno.soulsawakening.networking.server.SSyncTeleportPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class QuincyEvents {

    @SubscribeEvent
    public static void onRightClick(CustomInteractionEvent event)
    {
        PlayerEntity player = event.getPlayer();
        if (player.level.isClientSide)
            return;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        boolean hasDangle = false;
        for (int i = 0; i < player.inventory.items.size(); i++)
        {
            if (!(player.inventory.getItem(i).getItem() instanceof DangleItem))
                continue;
            if (player.inventory.contains(new ItemStack(ModItems.KOJAKU.get()))) {
                return;
            }
            if (entityStats.getRace().equals(ModValues.SPIRIT) || entityStats.getRace().equals(ModValues.HUMAN))
            {
                entityStats.setRace(ModValues.QUINCY);
                ModAdvancements.RACE_CHANGE.trigger((ServerPlayerEntity) player);
                ModAdvancements.QUINCY.trigger((ServerPlayerEntity) player);
                QuincyStats quincyStats = new QuincyStats();
                entityStats.setQuincyStats(quincyStats);
                PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
            }
            if (!entityStats.getRace().equals(ModValues.QUINCY))
                return;
            hasDangle = true;
        }
        if (hasDangle && player.getMainHandItem().isEmpty())
        {
            player.setItemInHand(Hand.MAIN_HAND, new ItemStack(ModItems.KOJAKU.get()));
        }
    }
    @SubscribeEvent
    public static void onDropBow(ItemTossEvent event)
    {
        if (event.getEntityItem().getItem().getItem().equals(ModItems.KOJAKU.get()))
            event.getEntityItem().remove();
    }
}
