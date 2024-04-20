package com.yuanno.soulsawakening.events.quincy;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.events.api.CustomInteractionEvent;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.items.DangleItem;
import com.yuanno.soulsawakening.items.ISpiritWeapon;
import com.yuanno.soulsawakening.items.KojakuItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class QuincyEvents {

    @SubscribeEvent
    public static void onRightClick(CustomInteractionEvent event)
    {
        PlayerEntity player = event.getPlayer();
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (!entityStats.getRace().equals(ModValues.QUINCY))
            return;
        if (player.level.isClientSide)
            return;
        boolean hasDangle = false;
        for (int i = 0; i < player.inventory.items.size(); i++)
        {
            if (!(player.inventory.getItem(i).getItem() instanceof DangleItem))
                continue;
            if (player.inventory.contains(new ItemStack(ModItems.KOJAKU.get()))) {
                System.out.println("CALLED");
                return;
            }
            hasDangle = true;
        }
        if (hasDangle && player.getMainHandItem().isEmpty())
        {
            player.setItemInHand(Hand.MAIN_HAND, new ItemStack(ModItems.KOJAKU.get()));
        }
    }
}
