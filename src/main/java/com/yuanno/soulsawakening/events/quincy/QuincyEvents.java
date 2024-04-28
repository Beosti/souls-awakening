package com.yuanno.soulsawakening.events.quincy;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.abilities.quincy.BlutStrengthAbility;
import com.yuanno.soulsawakening.abilities.quincy.PiercingArrowAbility;
import com.yuanno.soulsawakening.abilities.quincy.StrongArrowAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.entity.quincy.QuincyStats;
import com.yuanno.soulsawakening.events.UpdateStatEvent;
import com.yuanno.soulsawakening.events.api.CustomInteractionEvent;
import com.yuanno.soulsawakening.events.util.CustomArrowLooseEvent;
import com.yuanno.soulsawakening.init.ModAdvancements;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.items.DangleItem;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.monster.MonsterEntity;
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
                quincyStats.setMaxClassExperience(100);
                entityStats.setQuincyStats(quincyStats);
                quincyStats.setSpiritWeapon(ModItems.FISHING_ROD_REISHI.get());
                PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
            }
            if (!entityStats.getRace().equals(ModValues.QUINCY))
                return;
            hasDangle = true;
        }
        if (hasDangle && player.getMainHandItem().isEmpty())
        {
            player.setItemInHand(Hand.MAIN_HAND, new ItemStack(entityStats.getQuincyStats().getSpiritWeapon()));
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
        int amountToChange;
        if (EntityStatsCapability.get(event.getEntityLiving()).getRace().equals(ModValues.HOLLOW))
            amountToChange = 150;
        else if (event.getEntityLiving() instanceof MonsterEntity)
            amountToChange = 25;
        else
            return;
        LivingEntity deadEntity = event.getEntityLiving();
        if (deadEntity.level.isClientSide)
            return;
        if (event.getSource().getDirectEntity() != null)
        {
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
            if (!EntityStatsCapability.get(killerLivingEntity).getRace().equals(ModValues.QUINCY) || !EntityStatsCapability.get(killerLivingEntity).hasQuincyStats())
                return;

            IEntityStats killerStats = EntityStatsCapability.get(killerLivingEntity);
            killerStats.getQuincyStats().alterClassExperience(amountToChange);
            if (killerStats.getQuincyStats().getClassExperience() >= killerStats.getQuincyStats().getMaxClassExperience())
            {
                int amount = killerStats.getQuincyStats().getMaxClassExperience() - killerStats.getQuincyStats().getClassExperience();
                killerStats.getQuincyStats().setExperiencePoints(0);
                killerStats.getQuincyStats().alterClassExperience(amount);
                killerStats.getQuincyStats().alterMaxClassExperience(50);
                killerStats.getQuincyStats().alterClassPoints(1);
            }
            if (killerLivingEntity instanceof PlayerEntity)
                PacketHandler.sendTo(new SSyncEntityStatsPacket(killerLivingEntity.getId(), killerStats), (PlayerEntity) killerLivingEntity);
        }
    }
    @SubscribeEvent
    public static void onKojakuLoose(CustomArrowLooseEvent event)
    {
        if (event.getPower() != 1)
            return;
        IEntityStats looseEntity = EntityStatsCapability.get(event.getEntityLiving());
        if (!looseEntity.getRace().equals(ModValues.QUINCY) || !looseEntity.hasQuincyStats())
            return;
        looseEntity.getQuincyStats().alterClassExperience(10);
        if (looseEntity.getQuincyStats().getClassExperience() >= looseEntity.getQuincyStats().getMaxClassExperience())
        {
            int amount = looseEntity.getQuincyStats().getMaxClassExperience() - looseEntity.getQuincyStats().getClassExperience();
            looseEntity.getQuincyStats().setExperiencePoints(0);
            looseEntity.getQuincyStats().alterClassExperience(amount);
            looseEntity.getQuincyStats().alterMaxClassExperience(50);
        }
        if (event.getEntityLiving() instanceof PlayerEntity)
            PacketHandler.sendTo(new SSyncEntityStatsPacket(event.getEntityLiving().getId(), looseEntity), (PlayerEntity) event.getEntityLiving());

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
        handleBlut(player, entityStats);
    }

    // handles the blut stat -> extra damage reduction + extra damage + abilities
    static void handleBlut(PlayerEntity player, IEntityStats entityStats)
    {
        ModifiableAttributeInstance damageReductionAttribute = player.getAttribute(ModAttributes.DAMAGE_REDUCTION.get());
        damageReductionAttribute.setBaseValue(entityStats.getQuincyStats().getBlut() * 0.02);
        ModifiableAttributeInstance attackAddedAttribute = player.getAttribute(Attributes.ATTACK_DAMAGE);
        attackAddedAttribute.setBaseValue(entityStats.getQuincyStats().getBlut() * 0.02);
        handleAbilities(player, entityStats);
    }
    public static void handleAbilities(PlayerEntity player, IEntityStats entityStats)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        if (!abilityData.getUnlockedAbilities().contains(StrongArrowAbility.INSTANCE) && entityStats.getQuincyStats().getBlut() == 5)
            abilityData.addUnlockedAbility(StrongArrowAbility.INSTANCE);
        if (!abilityData.getUnlockedAbilities().contains(BlutStrengthAbility.INSTANCE) && entityStats.getQuincyStats().getBlut() == 10)
            abilityData.addUnlockedAbility(BlutStrengthAbility.INSTANCE);
        if (!abilityData.getUnlockedAbilities().contains(PiercingArrowAbility.INSTANCE) && entityStats.getQuincyStats().getBlut() == 15)
            abilityData.addUnlockedAbility(PiercingArrowAbility.INSTANCE);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
    }

    // handles the constitution stat -> extra max health
    static void handleConstitution(PlayerEntity player, IEntityStats entityStats)
    {
        ModifiableAttributeInstance maxHpAttribute = player.getAttribute(Attributes.MAX_HEALTH);
        maxHpAttribute.setBaseValue(20 + (float) (entityStats.getQuincyStats().getConstitution()));
        ((ServerPlayerEntity) player).connection.send(new SUpdateHealthPacket(player.getHealth(), player.getFoodData().getFoodLevel(), player.getFoodData().getSaturationLevel()));
    }
}
