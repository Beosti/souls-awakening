package com.yuanno.soulsawakening.setup;

import com.mojang.brigadier.CommandDispatcher;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.commands.RaceCommand;
import com.yuanno.soulsawakening.commands.ShinigamiStatsCommand;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeSetup {

    @SubscribeEvent
    public static void serverStarting(FMLServerStartingEvent event)
    {
        CommandDispatcher dispatcher = event.getServer().getCommands().getDispatcher();
        RaceCommand.register(dispatcher);
        ShinigamiStatsCommand.register(dispatcher);
    }
}
