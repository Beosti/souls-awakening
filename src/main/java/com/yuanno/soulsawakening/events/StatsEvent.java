package com.yuanno.soulsawakening.events;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.abilities.hollow.HollowRegenerationAbility;
import com.yuanno.soulsawakening.abilities.hollow.BiteAbility;
import com.yuanno.soulsawakening.abilities.hollow.SlashAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.challenges.ChallengesDataCapability;
import com.yuanno.soulsawakening.data.challenges.IChallengesData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.misc.IMiscData;
import com.yuanno.soulsawakening.data.misc.MiscDataCapability;
import com.yuanno.soulsawakening.init.ModAdvancements;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.init.ModChallenges;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import com.yuanno.soulsawakening.networking.server.SSyncChallengeDataPacket;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import com.yuanno.soulsawakening.networking.server.SSyncMiscDataPacket;
import com.yuanno.soulsawakening.world.ChallengesChunkGenerator;
import com.yuanno.soulsawakening.world.DynamicDimensionManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Dimension;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;


@Mod.EventBusSubscriber(modid = Main.MODID)
public class StatsEvent {
    private static ArrayList<ItemStack> soulboundItems = new ArrayList<>();

    @SubscribeEvent
    public static void joinWorldEvent(PlayerEvent.PlayerLoggedInEvent event)
    {
        PlayerEntity player = event.getPlayer();
        if (player.level.isClientSide)
            return;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        IAbilityData abilityData = AbilityDataCapability.get(player);
        IChallengesData challengesData = ChallengesDataCapability.get(player);
        IMiscData miscData = MiscDataCapability.get(player);
        if (!entityStats.hasRace())
            statsHandling(player);

        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        PacketHandler.sendTo(new SSyncChallengeDataPacket(player.getId(), challengesData), player);
        PacketHandler.sendTo(new SSyncMiscDataPacket(player.getId(), miscData), player);
    }
    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event)
    {
        if (!(event.getEntityLiving() instanceof PlayerEntity))
            return;
        PlayerEntity player = (PlayerEntity) event.getEntityLiving();
        if (!soulboundItems.isEmpty())
            soulboundItems.clear();
        for (int i = 0; i < player.inventory.getContainerSize(); i++)
        {
            if (!player.inventory.getItem(i).hasTag())
                continue;
            if (player.inventory.getItem(i).getTag().getBoolean("soulbound")) {
                soulboundItems.add(player.inventory.getItem(i).copy());
                player.inventory.getItem(i).shrink(1);
            }
        }
        IEntityStats entityStats = EntityStatsCapability.get(player);
        IAbilityData abilityData = AbilityDataCapability.get(player);
        IMiscData miscData = MiscDataCapability.get(player);

        if (entityStats.getRace().equals(ModValues.HUMAN)) {
            entityStats.setRace(ModValues.SPIRIT);
            ModAdvancements.RACE_CHANGE.trigger((ServerPlayerEntity) player);
            ModAdvancements.SPIRIT.trigger((ServerPlayerEntity) player);

            miscData.setCanRenderOverlay(true);
        }
        else if (entityStats.getRace().equals(ModValues.SPIRIT)) {
            entityStats.setRace(ModValues.HOLLOW);
            entityStats.setRank(ModValues.BASE);
            ModAdvancements.RACE_CHANGE.trigger((ServerPlayerEntity) player);
            ModAdvancements.HOLLOW.trigger((ServerPlayerEntity) player);
            miscData.setCanRenderOverlay(true);
            abilityData.addUnlockedAbility(SlashAbility.INSTANCE);
            abilityData.addUnlockedAbility(BiteAbility.INSTANCE);
            //abilityData.addUnlockedAbility(AcidicTouchAbility.INSTANCE);
            abilityData.addUnlockedAbility(HollowRegenerationAbility.INSTANCE);
            entityStats.setHollowPoints(0);
            entityStats.addAvailableStats(0);

        }
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        PacketHandler.sendTo(new SSyncMiscDataPacket(player.getId(), miscData), player);

    }

    public static void statsHandling(PlayerEntity player)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        IAbilityData abilityData = AbilityDataCapability.get(player);
        IMiscData miscData = MiscDataCapability.get(player);
        IChallengesData challengesData = ChallengesDataCapability.get((ServerPlayerEntity) player);
        if (entityStats.hasRace())
            return;
        entityStats.setRace(ModValues.HUMAN);
        miscData.setCanRenderOverlay(true);
        miscData.setKan(0);
        challengesData.addChallenge(ModChallenges.BASIC_SHINIGAMI.get());
        //challengesData.addChallenge(ModChallenges.SEATED20_SHINIGAMI.get());

        PacketHandler.sendTo(new SSyncMiscDataPacket(player.getId(), miscData), player);
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        PacketHandler.sendTo(new SSyncChallengeDataPacket(player.getId(), challengesData), player);
    }

    @SubscribeEvent
    public static void onRelogging(PlayerEvent.PlayerRespawnEvent event) {
        PlayerEntity player = event.getPlayer();
        IEntityStats statsProps = EntityStatsCapability.get(player);
        IAbilityData abilityData = AbilityDataCapability.get(player);
        IMiscData miscData = MiscDataCapability.get(player);
        if (!statsProps.hasRace())
            statsHandling(player);
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), statsProps), player);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        PacketHandler.sendTo(new SSyncMiscDataPacket(player.getId(), miscData), player);
    }

    @SubscribeEvent
    public static void onClonePlayer(PlayerEvent.Clone event) {
        if (!soulboundItems.isEmpty())
        {
            for (ItemStack soulboundItem : soulboundItems) {
                event.getPlayer().inventory.add(soulboundItem);
            }
        }
        if (event.isWasDeath()) {
            StatsEvent.restoreFullData(event.getOriginal(), event.getPlayer());
            IAbilityData newAbilityData = AbilityDataCapability.get(event.getPlayer());
            PacketHandler.sendTo(new SSyncAbilityDataPacket(event.getPlayer().getId(), newAbilityData), event.getPlayer());
            IEntityStats newEntityStats = EntityStatsCapability.get(event.getPlayer());
            PacketHandler.sendTo(new SSyncEntityStatsPacket(event.getPlayer().getId(), newEntityStats), event.getPlayer());
            IMiscData miscData = MiscDataCapability.get(event.getPlayer());
            PacketHandler.sendTo(new SSyncMiscDataPacket(event.getPlayer().getId(), miscData), event.getPlayer());

        } else
            StatsEvent.restoreFullData(event.getOriginal(), event.getPlayer());
    }
    private static void restoreFullData(PlayerEntity original, PlayerEntity player) {
        INBT nbt = new CompoundNBT();

        // Keep the entity stats
        IEntityStats oldEntityStats = EntityStatsCapability.get(original);
        nbt = EntityStatsCapability.INSTANCE.writeNBT(oldEntityStats, null);
        IEntityStats newEntityStats = EntityStatsCapability.get(player);
        EntityStatsCapability.INSTANCE.readNBT(newEntityStats, null, nbt);


        // Keep the ability stats
        IAbilityData oldAbilityData = AbilityDataCapability.get(original);
        nbt = AbilityDataCapability.INSTANCE.writeNBT(oldAbilityData, null);
        IAbilityData newAbilityData = AbilityDataCapability.get(player);
        AbilityDataCapability.INSTANCE.readNBT(newAbilityData, null, nbt);

        // Keep the miscData
        IMiscData oldMiscData = MiscDataCapability.get(original);
        nbt = MiscDataCapability.INSTANCE.writeNBT(oldMiscData, null);
        IMiscData newMiscData = MiscDataCapability.get(player);
        MiscDataCapability.INSTANCE.readNBT(newMiscData, null, nbt);


        /*
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), newAbilityData), player);
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), newEntityStats), player);

         */


    }

    @SubscribeEvent
    public static void onPlayerChangeDimensions(PlayerEvent.PlayerChangedDimensionEvent event)
    {
        PlayerEntity player = event.getPlayer();
        IEntityStats stats = EntityStatsCapability.get(player);
        IAbilityData abilityData = AbilityDataCapability.get(player);
        IMiscData miscData = MiscDataCapability.get(player);
        IChallengesData challengesData = ChallengesDataCapability.get(player);
        PacketHandler.sendToAllTrackingAndSelf(new SSyncEntityStatsPacket(player.getId(), stats), player);
        PacketHandler.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        PacketHandler.sendToAllTrackingAndSelf(new SSyncMiscDataPacket(player.getId(), miscData), player);
        PacketHandler.sendToAllTrackingAndSelf(new SSyncChallengeDataPacket(player.getId(), challengesData), player);
        //MinecraftForge.EVENT_BUS.post(new EntityEvent.Size(player, player.getPose(), player.getDimensions(player.getPose()), player.getBbHeight()));
    }

    @SubscribeEvent
    public static void onPlayerStartsTracking(PlayerEvent.StartTracking event)
    {
        if (event.getTarget() instanceof  PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) event.getTarget();
            IEntityStats stats = EntityStatsCapability.get(player);
            IAbilityData abilityData = AbilityDataCapability.get(player);
            PacketHandler.sendToAllTrackingAndSelf(new SSyncEntityStatsPacket(player.getId(), stats), player);
            PacketHandler.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(player.getId(), abilityData), player);

        }
    }

}
