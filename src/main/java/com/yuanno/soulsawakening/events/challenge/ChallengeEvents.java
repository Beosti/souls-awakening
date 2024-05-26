package com.yuanno.soulsawakening.events.challenge;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.data.ChallengesWorldData;
import com.yuanno.soulsawakening.events.EffectsEvent;
import com.yuanno.soulsawakening.events.util.CustomArrowLooseEvent;
import com.yuanno.soulsawakening.init.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ChallengeEvents {

    @SubscribeEvent
    public static void onDeathDamage(LivingDamageEvent event)
    {
        if (!(event.getEntityLiving() instanceof PlayerEntity))
            return;
        PlayerEntity player = (PlayerEntity) event.getEntityLiving();
        if (player.level.isClientSide)
            return;
        if (!player.level.getBiome(player.blockPosition()).getRegistryName().toString().equals("minecraft:the_void"))
            return;
        if (player.getHealth() - event.getAmount() <= 0)
        {
            event.setCanceled(true);
            player.addEffect(new EffectInstance(ModEffects.PASSIF.get(), 20, 0));
        }
    }

    @SubscribeEvent
    public static void onDestroyBlock(BlockEvent.BreakEvent event)
    {
        PlayerEntity player = event.getPlayer();
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
