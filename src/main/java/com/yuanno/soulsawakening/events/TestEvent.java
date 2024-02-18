package com.yuanno.soulsawakening.events;

import com.yuanno.soulsawakening.Main;
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
import com.yuanno.soulsawakening.entities.projectiles.kido.ShakkahoIncantationProjectile;
import com.yuanno.soulsawakening.entities.projectiles.kido.ShakkahoProjectile;
import com.yuanno.soulsawakening.init.ModChallenges;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.COpenAbilityListScreenPacket;
import com.yuanno.soulsawakening.networking.client.COpenPlayerScreenPacket;
import com.yuanno.soulsawakening.networking.client.COpenTradingScreenPacket;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
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

            abilityData.setSelectedAbility(0);
            PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
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
