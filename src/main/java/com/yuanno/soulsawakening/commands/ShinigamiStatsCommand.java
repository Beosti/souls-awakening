package com.yuanno.soulsawakening.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;

//TODO fix this command
public class ShinigamiStatsCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("shinigami")
                .requires((commandSource) -> commandSource.hasPermission(4))
                .then(Commands.argument("target", EntityArgument.player())
                        .then(Commands.argument("stats", StringArgumentType.string()).suggests(SUGGEST_STAT))
                            .then(Commands.argument("add|set", StringArgumentType.string()).suggests(SUGGEST_SET)
                                    .then(Commands.argument("amount", IntegerArgumentType.integer()))
                                .executes((command ->
                                {
                                    String stats = StringArgumentType.getString(command, "stats");
                                    String change = StringArgumentType.getString(command, "add|set");
                                    int amount = IntegerArgumentType.getInteger(command, "amount");

                                    return setStat(command.getSource(), EntityArgument.getPlayer(command, "target"), stats, change, amount);
                                })))));
    }

    private static final SuggestionProvider<CommandSource> SUGGEST_SET = (source, builder) -> {
        List<String> suggestions = new ArrayList<>();

        suggestions.add("add");
        suggestions.add("set");

        return ISuggestionProvider.suggest(suggestions.stream(), builder);
    };

    private static final SuggestionProvider<CommandSource> SUGGEST_STAT = (source, builder) -> {
        List<String> suggestions = new ArrayList<>();

        suggestions.add("zanjutsu");
        suggestions.add("hoho");
        suggestions.add("hakuda");

        return ISuggestionProvider.suggest(suggestions.stream(), builder);
    };

    private static int setStat(CommandSource commandSource, PlayerEntity player, String stats, String change, int amount)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (!entityStats.getRace().equals(ModValues.SHINIGAMI) && !entityStats.getRace().equals(ModValues.FULLBRINGER))
        {
            commandSource.sendSuccess(new TranslationTextComponent("Can only set the stats of fullbringers and shinigamis with this command!"), true);
            return 0;
        }
        if (change.equals("add"))
        {
            switch (stats)
            {
                case ("zanjutsu"):
                    entityStats.alterZanjutsuPoints(amount);
                    PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
                    break;
                case ("hoho"):
                    entityStats.alterHohoPoints(amount);
                    PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
                    break;
                case ("hakuda"):
                    entityStats.alterHakudaPoints(amount);
                    PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
                    break;
            }
        }
        else if (change.equals("set"))
        {
            switch (stats)
            {
                case ("zanjutsu"):
                    entityStats.setZanjutsuPoints(amount);
                    PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
                    break;
                case ("hoho"):
                    entityStats.setHohoPoints(amount);
                    PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
                    break;
                case ("hakuda"):
                    entityStats.setHakudaPoints(amount);
                    PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
                    break;
            }
        }
        if (change.equals("set"))
            commandSource.sendSuccess(new TranslationTextComponent("set " + stats + " of " + player.getDisplayName().getString() + " to " + amount), true);
        if (change.equals("add"))
            commandSource.sendSuccess(new TranslationTextComponent("added " + amount + " " + stats + " points" + " to " + player.getDisplayName().getString()), true);

        /*
        IEntityStats entityStats = EntityStatsCapability.get(player);
        entityStats.setRace(race);
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);

        commandSource.sendSuccess(new TranslationTextComponent("set race of " + player.getDisplayName().getString() + " to " + race), true);

         */
        return 1;
    }
}
