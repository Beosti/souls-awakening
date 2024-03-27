package com.yuanno.soulsawakening.events;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.abilities.kido.bakudo.SaiAbility;
import com.yuanno.soulsawakening.abilities.kido.hado.ByakuraiAbility;
import com.yuanno.soulsawakening.abilities.kido.hado.ShakkahoAbility;
import com.yuanno.soulsawakening.abilities.kido.hado.ShoAbility;
import com.yuanno.soulsawakening.data.ChallengesWorldData;
import com.yuanno.soulsawakening.data.ability.AbilityDataBase;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.challenges.ChallengesDataCapability;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.quest.IQuestData;
import com.yuanno.soulsawakening.data.quest.QuestDataCapability;
import com.yuanno.soulsawakening.data.teleports.ITeleports;
import com.yuanno.soulsawakening.data.teleports.TeleportCapability;
import com.yuanno.soulsawakening.entities.projectiles.kido.ShakkahoIncantationProjectile;
import com.yuanno.soulsawakening.entities.projectiles.kido.ShakkahoProjectile;
import com.yuanno.soulsawakening.init.ModChallenges;
import com.yuanno.soulsawakening.init.ModQuests;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.COpenAbilityListScreenPacket;
import com.yuanno.soulsawakening.networking.client.COpenPlayerScreenPacket;
import com.yuanno.soulsawakening.networking.client.COpenTradingScreenPacket;
import com.yuanno.soulsawakening.networking.server.SOpenChatPromptScreenPacket;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import com.yuanno.soulsawakening.networking.server.SSyncQuestDataPacket;
import com.yuanno.soulsawakening.networking.server.SSyncTeleportPacket;
import com.yuanno.soulsawakening.quests.KillHollowQuest;
import com.yuanno.soulsawakening.quests.Quest;
import com.yuanno.soulsawakening.teleport.TeleportPosition;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class TestEvent {

    @SubscribeEvent
    public static void chatEventServer(ServerChatEvent event)
    {
        if (event.getMessage().equals("sho"))
        {
            PlayerEntity player = event.getPlayer();
            IAbilityData abilityData = AbilityDataCapability.get(player);
            abilityData.addUnlockedAbility(ShoAbility.INSTANCE);
            abilityData.addAbilityToBar(ShoAbility.INSTANCE);
            abilityData.addUnlockedAbility(ByakuraiAbility.INSTANCE);
            abilityData.addAbilityToBar(ByakuraiAbility.INSTANCE);
            abilityData.addUnlockedAbility(ShakkahoAbility.INSTANCE);
            abilityData.addAbilityToBar(ShakkahoAbility.INSTANCE);
            abilityData.addUnlockedAbility(SaiAbility.INSTANCE);
            abilityData.addAbilityToBar(SaiAbility.INSTANCE);
            abilityData.setSelectedAbility(0);
            PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        }

        if (event.getMessage().equals("give"))
        {
            PlayerEntity player = event.getPlayer();
            IQuestData questData = QuestDataCapability.get(player);
            questData.addInProgressQuest(ModQuests.KILLHOLLOW);
            PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), questData), player);
        }

        if (event.getMessage().equals("check"))
        {
            PlayerEntity player = event.getPlayer();
            System.out.println(QuestDataCapability.get(player).getQuests());
        }
        if (event.getMessage().equals("finished"))
        {
            PlayerEntity player = event.getPlayer();
            IQuestData questData = QuestDataCapability.get(player);
            Quest quest = new KillHollowQuest();
            for (int i = 0; i < questData.getQuests().size(); i++)
            {
                if (questData.getQuests().get(i).getTitle().equals(quest.getTitle()) && questData.isQuestComplete(questData.getQuests().get(i))) {
                    questData.getQuests().get(i).getQuestReward().giveReward(player);
                    questData.getQuests().get(i).setInProgress(false);
                    PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), questData), player);
                }
            }
        }
        if (event.getMessage().equals("quest"))
        {
            PlayerEntity player = event.getPlayer();
            PacketHandler.sendTo(new SOpenChatPromptScreenPacket(), player);
        }
        if (event.getMessage().equals("teleport set"))
        {
            PlayerEntity player = event.getPlayer();
            ITeleports teleportData = TeleportCapability.get(player);
            TeleportPosition teleportPosition = new TeleportPosition();
            teleportPosition.setBlockPos(player.blockPosition());
            teleportPosition.setName("test");
            teleportPosition.setDimension(player.level.dimension().toString());
            teleportData.addTeleportsPosition(teleportPosition);
            System.out.println("teleport position set: " + teleportPosition.getName());
            PacketHandler.sendTo(new SSyncTeleportPacket(player.getId(), teleportData), player);
        }
        if (event.getMessage().equals("go teleport"))
        {
            PlayerEntity player = event.getPlayer();
            ITeleports teleports = TeleportCapability.get(player);
            if (!teleports.getTeleportPositions().get(0).getDimension().equals(player.level.dimension().toString()))
            {
                System.out.println("NOT IN RIGHT DIMENSION");
                return;
            }
            System.out.println("teleports are: " + teleports.getTeleportPositions());
            System.out.println("teleport to: " + teleports.getTeleportPositions().get(0));
            player.teleportTo(teleports.getTeleportPositions().get(0).getBlockPos().getX(), teleports.getTeleportPositions().get(0).getBlockPos().getY(), teleports.getTeleportPositions().get(0).getBlockPos().getZ());
        }
        if (event.getMessage().equals("testing tps"))
        {
            PlayerEntity player = event.getPlayer();
            ITeleports teleports = TeleportCapability.get(player);
            System.out.println(teleports.getTeleportPositions());
        }
        if (event.getMessage().equals("Ye lord! Mask of blood and flesh, all creation, flutter of wings, ye who bears the name of Man! Inferno and pandemonium, the sea barrier surges, march on to the south!"))
        {
            PlayerEntity player = event.getPlayer();
            ShakkahoIncantationProjectile shakkahoProjectile = new ShakkahoIncantationProjectile(player.level, player);
            IEntityStats entityStats = EntityStatsCapability.get(player);
            shakkahoProjectile.alterDamage((float) (entityStats.getReiatsuPoints()/2));
            shakkahoProjectile.setMaxLife(64 + (int) entityStats.getReiatsuPoints()/2);
            player.level.addFreshEntity(shakkahoProjectile);
            shakkahoProjectile.shootFromRotation(player, player.xRot, player.yRot, 0, 1f ,1);
        }
    }


}
