package com.yuanno.soulsawakening.events;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.data.ChallengesWorldData;
import com.yuanno.soulsawakening.data.challenges.ChallengesDataCapability;
import com.yuanno.soulsawakening.init.ModChallenges;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class TestEvent {

    @SubscribeEvent
    public static void chatEvent(ServerChatEvent event)
    {
        if (event.getMessage().equals("test"))
        {
            ChallengesDataCapability.get((ServerPlayerEntity) event.getPlayer()).addChallenge(ModChallenges.BANDIT.get());
            ChallengesWorldData.get().startChallenge((ServerPlayerEntity) event.getPlayer(), new ArrayList<>(), ModChallenges.BANDIT.get(), false);

        }
    }
}
