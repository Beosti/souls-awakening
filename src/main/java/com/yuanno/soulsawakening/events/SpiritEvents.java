package com.yuanno.soulsawakening.events;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class SpiritEvents {

    @SubscribeEvent
    public static void onHunger(TickEvent.PlayerTickEvent event)
    {
        if (event.player.level.isClientSide)
            return;
        String race = EntityStatsCapability.get(event.player).getRace();
        if (!race.equals(ModValues.SPIRIT))
            return;
        PlayerEntity player = event.player;
        player.getFoodData().setFoodLevel(20);
    }
}
