package com.yuanno.soulsawakening.events.quincy;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.entity.quincy.QuincyStats;
import com.yuanno.soulsawakening.events.UpdateStatEvent;
import com.yuanno.soulsawakening.events.api.CustomInteractionEvent;
import com.yuanno.soulsawakening.init.ModAdvancements;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.items.DangleItem;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SUpdateHealthPacket;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

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
    @SubscribeEvent
    public static void onKillHollow(LivingDeathEvent event)
    {
        if (!EntityStatsCapability.get(event.getEntityLiving()).getRace().equals(ModValues.HOLLOW))
            return;
        LivingEntity hollowEntity = event.getEntityLiving();
        if (hollowEntity.level.isClientSide)
            return;
        if (event.getSource().getDirectEntity() != null && event.getSource().getDirectEntity() instanceof LivingEntity)
        {
            Entity killerEntity = event.getSource().getDirectEntity();
            if (!(killerEntity instanceof LivingEntity))
                return;
            LivingEntity killerLivingEntity = (LivingEntity) killerEntity;
            if (!EntityStatsCapability.get(killerLivingEntity).getRace().equals(ModValues.QUINCY) || !EntityStatsCapability.get(killerLivingEntity).hasQuincyStats())
                return;
            IEntityStats killerStats = EntityStatsCapability.get(killerLivingEntity);
            Random random = new Random();
            int randomNumber = random.nextInt();
            if (randomNumber < 25)
                killerStats.getQuincyStats().alterClassPoints(1);
            if (killerLivingEntity instanceof PlayerEntity)
                PacketHandler.sendTo(new SSyncEntityStatsPacket(killerLivingEntity.getId(), killerStats), (PlayerEntity) killerLivingEntity);
        }
    }
    @SubscribeEvent
    public static void onUpdateHollowStat(UpdateStatEvent event)
    {
        if (!(event.getEntityLiving() instanceof PlayerEntity))
            return;
        PlayerEntity player = (PlayerEntity) event.getEntityLiving();
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (!entityStats.getRace().equals(ModValues.QUINCY))
            return;
        handleConstitution(player, entityStats);
        handleHierro(player, entityStats);
    }

    static void handleHierro(PlayerEntity player, IEntityStats entityStats)
    {
        ModifiableAttributeInstance damageReductionAttribute = player.getAttribute(ModAttributes.DAMAGE_REDUCTION.get());
        damageReductionAttribute.setBaseValue(entityStats.getHollowStats().getHierro() * 0.05);
    }

    // handles the constitution stat
    static void handleConstitution(PlayerEntity player, IEntityStats entityStats)
    {
        ModifiableAttributeInstance maxHpAttribute = player.getAttribute(Attributes.MAX_HEALTH);
        maxHpAttribute.setBaseValue(20 + (float) (entityStats.getQuincyStats().getConstitution()));
        ((ServerPlayerEntity) player).connection.send(new SUpdateHealthPacket(player.getHealth(), player.getFoodData().getFoodLevel(), player.getFoodData().getSaturationLevel()));
    }
}
