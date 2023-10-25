package com.yuanno.soulsawakening.events;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.data.entity.EntityStatsBase;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class StatPointsEvent {

    /**
     * if fullbringer/shinigami something happens
     * if hitting fist you get hakuda
     * if hitting killing with zanpakuto you get zanjutsu
     * @param event
     */
    @SubscribeEvent
    public static void onAttackEvent(AttackEntityEvent event)
    {
        PlayerEntity player = event.getPlayer();
        Entity target = event.getTarget();
        IEntityStats entityStats  = EntityStatsCapability.get(player);
        if (player.level.isClientSide)
            return;
        if (!(target instanceof LivingEntity))
            return;
        if (!entityStats.getRace().equals(ModValues.SHINIGAMI) || !entityStats.getRace().equals(ModValues.FULLBRINGER))
            return;
        LivingEntity livingEntityTarget = (LivingEntity) target;

        if (player.getMainHandItem().isEmpty())
        {
            entityStats.alterHakudaPoints(1);
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
        }
        else if (player.getMainHandItem().getItem().equals(ModItems.ZANPAKUTO.get()) && livingEntityTarget.isDeadOrDying())
        {
            entityStats.alterZanjutsuPoints(1);
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
        }
    }

    @SubscribeEvent
    public static void onHurtEvent(LivingHurtEvent event)
    {
        LivingEntity hurtEntity = event.getEntityLiving();
        if (hurtEntity.level.isClientSide)
            return;
        if (hurtEntity instanceof PlayerEntity)
        {
            PlayerEntity playerHurtEntity = (PlayerEntity) hurtEntity;
            IEntityStats entityStats = EntityStatsCapability.get(playerHurtEntity);
            if (!entityStats.getRace().equals(ModValues.SHINIGAMI) || !entityStats.getRace().equals(ModValues.FULLBRINGER))
                return;
            if (!event.getSource().equals(DamageSource.CACTUS))
            {
                entityStats.alterHakudaPoints(0.05);
                if (entityStats.getHakudaPoints() >= 1)
                {
                    double newMaxHealth = Math.round(entityStats.getHakudaPoints());
                    AttributeModifier maxHealthModifier = new AttributeModifier("HealthBoost", newMaxHealth, AttributeModifier.Operation.ADDITION);

                    // Apply the new maximum health value to the player
                    playerHurtEntity.getAttribute(Attributes.MAX_HEALTH).removeModifier(maxHealthModifier);
                    playerHurtEntity.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(maxHealthModifier);
                }
                PacketHandler.sendTo(new SSyncEntityStatsPacket(playerHurtEntity.getId(), entityStats), playerHurtEntity);
            }
        }
    }
}
