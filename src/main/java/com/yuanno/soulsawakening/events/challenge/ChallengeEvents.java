package com.yuanno.soulsawakening.events.challenge;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.events.util.CustomArrowLooseEvent;
import com.yuanno.soulsawakening.init.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDestroyBlockEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ChallengeEvents {

    @SubscribeEvent
    public static void onDestroyBlock(BlockEvent.BreakEvent event)
    {
        if (event.getPlayer().level.getBiome(event.getPlayer().blockPosition()).getRegistryName().toString().equals("minecraft:the_void"))
            event.setCanceled(true);
    }

    @SubscribeEvent
    public static void onPlaceBlock(BlockEvent.EntityPlaceEvent event)
    {
        if (!(event.getEntity() instanceof LivingEntity))
            return;
        LivingEntity livingEntity = (LivingEntity) event.getEntity();
        if (livingEntity.level.getBiome(livingEntity.blockPosition()).getRegistryName().toString().equals("minecraft:the_void"))
            event.setCanceled(true);
    }

    @SubscribeEvent
    public static void onArrowLoose(ArrowLooseEvent event)
    {
        if (!(event.getEntity() instanceof LivingEntity))
            return;
        LivingEntity livingEntity = (LivingEntity) event.getEntity();
        if (livingEntity.hasEffect(ModEffects.IN_EVENT.get()))
            event.setCanceled(true);
    }
    @SubscribeEvent
    public static void onCustomArrowLoose(CustomArrowLooseEvent event)
    {
        if (!(event.getEntity() instanceof LivingEntity))
            return;
        LivingEntity livingEntity = (LivingEntity) event.getEntity();
        if (livingEntity.hasEffect(ModEffects.IN_EVENT.get()))
            event.setCanceled(true);
    }
}
