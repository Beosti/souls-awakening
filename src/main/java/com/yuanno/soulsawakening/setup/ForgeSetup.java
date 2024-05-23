package com.yuanno.soulsawakening.setup;

import com.mojang.brigadier.CommandDispatcher;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.commands.*;
import com.yuanno.soulsawakening.commands.ability.AbilityCommand;
import com.yuanno.soulsawakening.commands.challenge.ChallengeCommand;
import com.yuanno.soulsawakening.commands.quest.QuestCommand;
import com.yuanno.soulsawakening.init.ModFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

import java.util.Map;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeSetup {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void biomeModification(BiomeLoadingEvent event)
    {
        if(event.getCategory() == Biome.Category.NETHER || event.getCategory() == Biome.Category.THEEND || event.getName() == Biomes.THE_VOID.getRegistryName()) {
            return;
        }


        ModFeatures.setupFeatures(event);

    }

    @SubscribeEvent
    public static void serverStarting(FMLServerStartingEvent event)
    {
        CommandDispatcher dispatcher = event.getServer().getCommands().getDispatcher();
        ElementPointsCommand.register(dispatcher);
        RaceCommand.register(dispatcher);
        AbilityCommand.register(dispatcher);
        QuestCommand.register(dispatcher);
        StatsCommand.register(dispatcher);
        ShikaiCommand.register(dispatcher);
        ChallengeCommand.register(dispatcher);
        RankCommand.register(dispatcher);
        StructureCommand.register(dispatcher);
    }
}
