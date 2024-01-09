package com.yuanno.soulsawakening.world.gen;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.data.ChallengesWorldData;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class WorldEvents {

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event)
    {
        OreGeneration.generateOres(event);
        EntityGeneration.onEntitySpawn(event);
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if (event.world.dimension().location().toString().contains("challenges_")) {
                event.world.getProfiler().push("challengesManager");

                ChallengesWorldData.get().tick((ServerWorld) event.world);

                event.world.getProfiler().pop();
            }
        }
    }
}
