package com.yuanno.soulsawakening.events;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.abilities.hollow.BiteAbility;
import com.yuanno.soulsawakening.abilities.hollow.HollowRegenerationAbility;
import com.yuanno.soulsawakening.abilities.hollow.SlashAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.entity.hollow.HollowStats;
import com.yuanno.soulsawakening.data.misc.IMiscData;
import com.yuanno.soulsawakening.data.misc.MiscDataCapability;
import com.yuanno.soulsawakening.data.quest.IQuestData;
import com.yuanno.soulsawakening.data.quest.QuestDataCapability;
import com.yuanno.soulsawakening.init.ModAdvancements;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.init.ModQuests;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.init.world.ModDimensions;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import com.yuanno.soulsawakening.networking.server.SSyncMiscDataPacket;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.WaveParticleEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class SpiritEvents {
    public static final ParticleEffect PARTICLES_WAVE = new WaveParticleEffect(1.4);

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

    @SubscribeEvent
    public static void onChainSoul(TickEvent.PlayerTickEvent event)
    {
        PlayerEntity player = event.player;
        if (player.level.isClientSide)
            return;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (!entityStats.getRace().equals(ModValues.SPIRIT))
            return;
        IQuestData questData = QuestDataCapability.get(player);
        if (questData.hasInProgressQuest(ModQuests.KILLHOLLOW))
            return;
        IMiscData miscData = MiscDataCapability.get(player);
        IAbilityData abilityData = AbilityDataCapability.get(player);
        MinecraftServer minecraftServer = player.level.getServer();
        ServerWorld soulSociety = minecraftServer.getLevel(ModDimensions.SOUL_SOCIETY);
        if ((miscData.getSpiritChain() > 0 && player.tickCount % 20 == 0) && player.level != soulSociety)
            miscData.alterSpiritChain(-1);
        else if (miscData.getSpiritChain() <= 0)
        {
            PARTICLES_WAVE.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0, ModParticleTypes.HOLLOW.get());
            entityStats.setRace(ModValues.HOLLOW);
            entityStats.setRank(ModValues.BASE);
            ModAdvancements.RACE_CHANGE.trigger((ServerPlayerEntity) player);
            ModAdvancements.HOLLOW.trigger((ServerPlayerEntity) player);
            miscData.setCanRenderOverlay(true);
            abilityData.addUnlockedAbility(SlashAbility.INSTANCE);
            abilityData.addUnlockedAbility(BiteAbility.INSTANCE);
            abilityData.addUnlockedAbility(HollowRegenerationAbility.INSTANCE);
            HollowStats hollowStats = new HollowStats();
            entityStats.setHollowStats(hollowStats);
            //entityStats.getHollowStats().altermut
            miscData.setCanRenderOverlay(true);
        }
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        PacketHandler.sendTo(new SSyncMiscDataPacket(player.getId(), miscData), player);
    }
}
