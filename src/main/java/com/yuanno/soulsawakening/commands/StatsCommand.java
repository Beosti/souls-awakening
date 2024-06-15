package com.yuanno.soulsawakening.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.entity.quincy.QuincyStats;
import com.yuanno.soulsawakening.data.entity.shinigami.ShinigamiStats;
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

/**
 * Stat command to change the entity stats, reliant on race
 */
public class StatsCommand {

    static ArrayList<String> shinigami = new ArrayList<>();
    static ArrayList<String> hollow = new ArrayList<>();
    static ArrayList<String> quincy = new ArrayList<>();

    public static void register(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("stats").requires((commandSource) -> commandSource.hasPermission(4))
                .then(Commands.argument("target", EntityArgument.player())
                        .then(Commands.argument("stats", StringArgumentType.string()).suggests(SUGGEST_STAT)
                            .then(Commands.argument("add|set", StringArgumentType.string()).suggests(SUGGEST_SET)
                                    .then((Commands.argument("amount", IntegerArgumentType.integer()))
                                .executes((command) ->
                                {
                                    String stats = StringArgumentType.getString(command, "stats");
                                    String change = StringArgumentType.getString(command, "add|set");
                                    int amount = IntegerArgumentType.getInteger(command, "amount");

                                    return setStat(command.getSource(), EntityArgument.getPlayer(command, "target"), stats, change, amount);
                                }))))));
    }

    private static final SuggestionProvider<CommandSource> SUGGEST_SET = (source, builder) -> {
        List<String> suggestions = new ArrayList<>();

        suggestions.add("add");
        suggestions.add("set");

        return ISuggestionProvider.suggest(suggestions.stream(), builder);
    };

    private static final SuggestionProvider<CommandSource> SUGGEST_STAT = (source, builder) -> {
        List<String> suggestions = new ArrayList<>();

        suggestions.add("reiatsu");

        suggestions.add("class");
        shinigami.add("class");
        quincy.add("class");

        suggestions.add("zanjutsu");
        shinigami.add("zanjutsu");
        suggestions.add("hoho");
        shinigami.add("hoho");
        suggestions.add("hakuda");
        shinigami.add("hakuda");

        suggestions.add("blut");
        quincy.add("blut");
        suggestions.add("hirenkyaku");
        quincy.add("hirenkyaku");
        suggestions.add("constitution");
        quincy.add("constitution");

        suggestions.add("hollow");
        hollow.add("hollow");
        suggestions.add("mutation");
        hollow.add("mutation");
        suggestions.add("constitution");
        hollow.add("constitution");
        suggestions.add("hierro");
        hollow.add("hierro");
        suggestions.add("agility");
        hollow.add("agility");

        return ISuggestionProvider.suggest(suggestions.stream(), builder);
    };

    private static int setStat(CommandSource commandSource, PlayerEntity player, String stats, String change, int amount)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        String race = entityStats.getRace();
        if (stats.equals("reiatsu") && !race.equals(ModValues.SPIRIT) && !race.equals(ModValues.HUMAN))
        {
            if (change.equals("set"))
                entityStats.setReiatsuPoints(amount);
            else if (change.equals("add"))
                entityStats.alterReiatsuPoints(amount);
            commandSource.sendSuccess(new TranslationTextComponent("command.stats.changed"), true);
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
            return 1;
        }

        if (shinigami.contains(stats) && !hollow.contains(stats) && !quincy.contains(stats) && !race.equals(ModValues.SHINIGAMI))
        {
            commandSource.sendSuccess(new TranslationTextComponent("command.rank.not_shinigami"), true);
            return 0;
        }
        else if (hollow.contains(stats) && !shinigami.contains(stats) && !quincy.contains(stats) && !race.equals(ModValues.HOLLOW))
        {
            commandSource.sendSuccess(new TranslationTextComponent("command.rank.not_hollow"), true);
            return 0;
        }
        else if (quincy.contains(stats) && !shinigami.contains(stats) && !hollow.contains(stats) && !race.equals(ModValues.QUINCY))
        {
            commandSource.sendSuccess(new TranslationTextComponent("command.rank.not_quincy"), true);
            return 0;
        }

        if (change.equals("add"))
        {
            switch (stats)
            {
                case ("zanjutsu"):
                    entityStats.getShinigamiStats().alterZanjutsuPoints(amount);
                    break;
                case ("hoho"):
                    entityStats.getShinigamiStats().alterHohoPoints(amount);
                    break;
                case ("hakuda"):
                    entityStats.getShinigamiStats().alterHakudaPoints(amount);
                    break;
                case ("class"):
                if (entityStats.getRace().equals(ModValues.SHINIGAMI))
                {
                    if (!entityStats.hasShinigamiStats()) {
                        ShinigamiStats shinigamiStats = new ShinigamiStats();
                        shinigamiStats.alterClassPoints(amount);
                        entityStats.setShinigamiStats(shinigamiStats);
                    } else
                        entityStats.getShinigamiStats().alterClassPoints(amount);
                }
                else if (entityStats.getRace().equals(ModValues.QUINCY))
                {
                    if (!entityStats.hasQuincyStats())
                    {
                        QuincyStats quincyStats = new QuincyStats();
                        quincyStats.alterClassPoints(amount);
                        entityStats.setQuincyStats(quincyStats);
                    }
                    else
                        entityStats.getQuincyStats().alterClassPoints(amount);
                }
                    break;
                case ("hollow"):
                    entityStats.getHollowStats().alterHollowPoints(amount);
                    break;
                case ("mutation"):
                    entityStats.getHollowStats().alterMutationPoints(amount);
                    break;
                case ("hierro"):
                    entityStats.getHollowStats().alterHierro(amount);
                    break;
                case ("constitution"):
                    entityStats.getHollowStats().alterConstitution(amount);
                    break;
                case ("agility"):
                    entityStats.getHollowStats().alterAgility(amount);
                    break;
                default:
                    commandSource.sendFailure(new TranslationTextComponent("command.general.input_false"));
                    return 0;
            }
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
            commandSource.sendSuccess(new TranslationTextComponent("command.stats.changed"), true);
            return 1;
        }
        else if (change.equals("set"))
        {
            switch (stats)
            {
                case ("zanjutsu"):
                    entityStats.getShinigamiStats().setZanjutsuPoints(amount);
                    break;
                case ("hoho"):
                    entityStats.getShinigamiStats().setHohoPoints(amount);
                    break;
                case ("hakuda"):
                    entityStats.getShinigamiStats().setHakudaPoints(amount);
                    break;
                case ("class"):
                    entityStats.getShinigamiStats().setClassPoints(amount);
                    break;
                case ("hollow"):
                    entityStats.getHollowStats().setHollowPoints(amount);
                    break;
                case ("mutation"):
                    entityStats.getHollowStats().setMutationPoints(amount);
                    break;
                case ("hierro"):
                    entityStats.getHollowStats().setHierro(amount);
                    break;
                case ("constitution"):
                    entityStats.getHollowStats().setConstitution(amount);
                    break;
                case ("agility"):
                    entityStats.getHollowStats().setAgility(amount);
                    break;
                default:
                    commandSource.sendFailure(new TranslationTextComponent("command.general.input_false"));
                    return 0;
            }
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
            commandSource.sendSuccess(new TranslationTextComponent("command.stats.changed"), true);
            return 1;
        }
        else
        {
            commandSource.sendFailure(new TranslationTextComponent("command.general.input_false"));
            return 0;
        }
    }
}
