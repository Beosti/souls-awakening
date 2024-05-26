package com.yuanno.soulsawakening.events.shinigami;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.events.ExperienceEvent;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.network.play.server.STitlePacket;
import net.minecraft.util.text.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Class that handles everything related to shinigami
 */
@Mod.EventBusSubscriber(modid = Main.MODID)
public class ShinigamiEvents {

    @SubscribeEvent
    public static void onKillHollow(LivingDeathEvent event)
    {
        // TODO if doing a challenge you don't get points
        int amountToChange;
        LivingEntity killedEntity = event.getEntityLiving();
        IEntityStats killedEntityStats = EntityStatsCapability.get(killedEntity);
        if (killedEntityStats.getRace().equals(ModValues.HOLLOW))
            amountToChange = 150;
        else if (killedEntity instanceof MonsterEntity)
            amountToChange = 25;
        else
            return;
        if (killedEntity.level.isClientSide)
            return;
        if (event.getSource().getDirectEntity() != null)
        {
            if (event.getSource().getDirectEntity().level.getBiome(event.getSource().getDirectEntity().blockPosition()).getRegistryName().toString().equals("minecraft:the_void"))
                event.setCanceled(true);
            Entity killerEntity;
            if (event.getSource().getDirectEntity() instanceof AbilityProjectileEntity)
            {
                AbilityProjectileEntity abilityProjectileEntity = (AbilityProjectileEntity) event.getSource().getDirectEntity();
                if (abilityProjectileEntity.getOwner() != null)
                    killerEntity = abilityProjectileEntity.getOwner();
                else
                    return;
            }
            else
                killerEntity = event.getSource().getDirectEntity();

            if (!(killerEntity instanceof LivingEntity))
                return;
            LivingEntity killerLivingEntity = (LivingEntity) killerEntity;
            if (!EntityStatsCapability.get(killerLivingEntity).getRace().equals(ModValues.SHINIGAMI) || !EntityStatsCapability.get(killerLivingEntity).hasShinigamiStats())
                return;
            ExperienceEvent experienceEvent = new ExperienceEvent(killerLivingEntity, amountToChange);
            MinecraftForge.EVENT_BUS.post(experienceEvent);
        }
    }

    @SubscribeEvent
    public static void onExperienceChange(ExperienceEvent experienceEvent)
    {
        LivingEntity killerLivingEntity = experienceEvent.getEntityLiving();
        IEntityStats killerStats = EntityStatsCapability.get(killerLivingEntity);
        if (!killerStats.hasShinigamiStats())
            return;
        int amountToChange = experienceEvent.getAmount();
        killerStats.getShinigamiStats().alterClassExperience(amountToChange);
        if (killerStats.getShinigamiStats().getClassExperience() >= killerStats.getShinigamiStats().getMaxClassExperience())
        {
            int amount = killerStats.getShinigamiStats().getMaxClassExperience() - killerStats.getShinigamiStats().getClassExperience();
            killerStats.getShinigamiStats().setClassExperience(0);
            killerStats.getShinigamiStats().alterClassExperience(amount);
            killerStats.getShinigamiStats().alterMaxClassExperience(50);
            killerStats.getShinigamiStats().alterClassPoints(1);
            if (killerLivingEntity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) killerLivingEntity;
                try
                {
                    ((ServerPlayerEntity) player).connection.send(new STitlePacket(3, 10, 3));
                    ITextComponent titleComponent = TextComponentUtils.updateForEntity(player.createCommandSourceStack(), new TranslationTextComponent("shinigami.shinigami_point.text", "§0Gained a class point"), player, 0);
                    ((ServerPlayerEntity) player).connection.send(new STitlePacket(STitlePacket.Type.ACTIONBAR, titleComponent));
                }
                catch (Exception e)
                {
                    e.printStackTrace();;
                }


                PacketHandler.sendTo(new SSyncEntityStatsPacket(killerLivingEntity.getId(), killerStats), (PlayerEntity) killerLivingEntity);
            }
        }
    }

    /**
     * Handles everything that happens with zanjutsu when attacking
     * I use this event and not {@link net.minecraftforge.event.entity.living.LivingAttackEvent} because you can't change the damage in the other event
     * @param event
     */
    @SubscribeEvent
    public static void onAttackWithSword(LivingDamageEvent event)
    {
        if (event.getEntityLiving() != null && !event.getEntityLiving().level.isClientSide)
        {
            if (event.getSource().getDirectEntity() != null)
            {
                if (!(event.getSource().getDirectEntity() instanceof LivingEntity))
                    return;

                LivingEntity livingEntity = (LivingEntity) event.getSource().getDirectEntity();
                IEntityStats entityStats = EntityStatsCapability.get(livingEntity);
                if (!entityStats.getRace().equals(ModValues.SHINIGAMI))
                    return;

                if (!(livingEntity.getMainHandItem().getItem().asItem() instanceof SwordItem))
                    return;

                if (!EntityStatsCapability.get(livingEntity).getRace().equals(ModValues.SHINIGAMI))
                    return;
                if (livingEntity instanceof PlayerEntity)
                    experienceHandler((PlayerEntity) livingEntity, entityStats);
                if (!entityStats.hasShinigamiStats())
                    return;
                if (entityStats.getShinigamiStats().getZanjutsuPoints() <= 0)
                    return;
                event.setAmount((float) (event.getAmount() + entityStats.getShinigamiStats().getZanjutsuPoints() / 2));
            }
        }
    }

    // handles the experience gain of the player for zanjutsu
    public static void experienceHandler(PlayerEntity player, IEntityStats entityStats)
    {
        double currentZanjutsu = entityStats.getShinigamiStats().getZanjutsuPoints();
        int amount = 5; // upping this value will make it slower, the lower this value the faster it'll be
        double amountToAdd = (0.5 * Math.pow(0.5, (currentZanjutsu - 2) / amount));
        entityStats.getShinigamiStats().alterZanjutsuPoints(amountToAdd);
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
    }

    /**
     * Adds zanjutsu tooltip to all the swords
     * @param event
     */
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onToolTipRender(ItemTooltipEvent event)
    {
        PlayerEntity player = event.getPlayer();

        if (player == null) {
            return;
        }
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (event.getItemStack().getItem() instanceof SwordItem && (entityStats.getRace().equals(ModValues.SHINIGAMI) || entityStats.getRace().equals(ModValues.FULLBRINGER)) && entityStats.getShinigamiStats().getZanjutsuPoints() > 0)
        {
            StringTextComponent damageBonus = new StringTextComponent(TextFormatting.WHITE + "" + new TranslationTextComponent("Zanjutsu Bonus Damage: " + Math.floor(entityStats.getShinigamiStats().getZanjutsuPoints() / 2)).getString());
            if (!event.getToolTip().contains(damageBonus)) {
                event.getToolTip().add(new StringTextComponent(""));
                event.getToolTip().add(damageBonus);
            }
        }
    }
}
