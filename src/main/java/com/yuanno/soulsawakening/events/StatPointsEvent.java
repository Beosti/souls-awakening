package com.yuanno.soulsawakening.events;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.abilities.hollow.BiteAbility;
import com.yuanno.soulsawakening.api.AbilityDamageSource;
import com.yuanno.soulsawakening.api.SourceElement;
import com.yuanno.soulsawakening.api.SourceType;
import com.yuanno.soulsawakening.data.entity.EntityStatsBase;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.entity.hollow.HollowEntity;
import com.yuanno.soulsawakening.entity.hollow.JetEntity;
import com.yuanno.soulsawakening.events.stats.HakudaGainEvent;
import com.yuanno.soulsawakening.events.stats.HohoGainEvent;
import com.yuanno.soulsawakening.events.stats.HollowGainEvent;
import com.yuanno.soulsawakening.events.stats.ZanjutsuGainEvent;
import com.yuanno.soulsawakening.init.ModAbilities;
import com.yuanno.soulsawakening.init.ModDamageSource;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.items.ShinaiItem;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = Main.MODID)
public class StatPointsEvent {
    /**
     * if doing something as shinigami/fullbringer/spirit
     * you pull event and in there something happens
     */
    private final static DamageSource HAKUDA_DAMAGE = new ModDamageSource("hakuda").setSourceTypes(SourceType.FIST).setSourceElement(SourceElement.NONE);


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
        if (!entityStats.getRace().equals(ModValues.SHINIGAMI) && !entityStats.getRace().equals(ModValues.FULLBRINGER) && !entityStats.getRace().equals(ModValues.SPIRIT))
            return;
        int hakudaPoints = (int) entityStats.getHakudaPoints();
        if (player.getMainHandItem().isEmpty())
        {
            target.hurt(HAKUDA_DAMAGE, hakudaPoints);
            HakudaGainEvent hakudaGainEvent = new HakudaGainEvent(player);
            MinecraftForge.EVENT_BUS.post(hakudaGainEvent);
        }
        else if (player.getMainHandItem().getItem().asItem() instanceof ZanpakutoItem)
        {
            ZanjutsuGainEvent zanjutsuGainEvent = new ZanjutsuGainEvent(player, 0.1);
            MinecraftForge.EVENT_BUS.post(zanjutsuGainEvent);
        }
        else if (player.getMainHandItem().getItem().asItem() instanceof ShinaiItem)
        {
            ZanjutsuGainEvent zanjutsuGainEvent = new ZanjutsuGainEvent(player, 0.2);
            MinecraftForge.EVENT_BUS.post(zanjutsuGainEvent);
        }
    }

    @SubscribeEvent
    public static void onRunning(LivingEvent.LivingUpdateEvent event)
    {
        if (event.getEntityLiving().level.isClientSide)
            return;
        if (!(event.getEntityLiving() instanceof PlayerEntity))
            return;
        PlayerEntity player = (PlayerEntity) event.getEntityLiving();
        if (!player.isSprinting())
            return;
        if (player.tickCount % 20 != 0)
            return;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (!entityStats.getRace().equals(ModValues.SHINIGAMI) && !entityStats.getRace().equals(ModValues.FULLBRINGER) && !entityStats.getRace().equals(ModValues.SPIRIT))
            return;
        HohoGainEvent hohoGainEvent = new HohoGainEvent(player);
        MinecraftForge.EVENT_BUS.post(hohoGainEvent);

    }

    @SubscribeEvent
    public static void onHurtEvent(LivingHurtEvent event)
    {
        LivingEntity hurtEntity = event.getEntityLiving();
        if (hurtEntity.level.isClientSide)
            return;
        if (hurtEntity instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) hurtEntity;
            IEntityStats entityStats = EntityStatsCapability.get(hurtEntity);
            if ((entityStats.getRace().equals(ModValues.SHINIGAMI) || entityStats.getRace().equals(ModValues.FULLBRINGER)) && !event.getSource().equals(DamageSource.CACTUS))
            {
                HakudaGainEvent hakudaGainEvent = new HakudaGainEvent(player);
                MinecraftForge.EVENT_BUS.post(hakudaGainEvent);
            }
        }
    }

    @SubscribeEvent
    public static void onDeathEntity(LivingDeathEvent event)
    {
        if (!(event.getSource().getDirectEntity() instanceof PlayerEntity))
            return;
        PlayerEntity player = (PlayerEntity) event.getSource().getDirectEntity();
        if (player.level.isClientSide)
            return;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if ((entityStats.getRace().equals(ModValues.FULLBRINGER) || entityStats.getRace().equals(ModValues.SHINIGAMI))
                && (player.getMainHandItem().getItem().asItem() instanceof ZanpakutoItem || player.getMainHandItem().getItem().asItem() instanceof ShinaiItem)
                && (event.getEntityLiving() instanceof HollowEntity || event.getEntityLiving() instanceof JetEntity))
        {
            entityStats.alterClassExperience(1);
            if (entityStats.getClassExperience() >= entityStats.getClassLevel() * 3)
            {
                entityStats.alterClassPoints(1);
                entityStats.alterClassLevel(1);
                entityStats.setClassExperience(0);
                PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
            }
        }
        else if (entityStats.getRace().equals(ModValues.HOLLOW)
                && event.getSource() instanceof AbilityDamageSource
                && ((AbilityDamageSource) event.getSource()).getAbilitySource().equals(BiteAbility.INSTANCE))
        {
            HollowGainEvent hollowGainEvent = new HollowGainEvent(player);
            MinecraftForge.EVENT_BUS.post(hollowGainEvent);
        }
    }
}
