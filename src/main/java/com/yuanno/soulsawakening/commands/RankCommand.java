package com.yuanno.soulsawakening.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.entity.hollow.HollowStats;
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

public class RankCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("rank").requires((commandSource) -> commandSource.hasPermission(2))
                .then(Commands.argument("target", EntityArgument.player())
                        .then(Commands.argument("rank", StringArgumentType.string()).suggests(SUGGEST_SET)
                                .executes((command) ->
                                {
                                    String race = StringArgumentType.getString(command, "rank");

                                    return setRace(command.getSource(), EntityArgument.getPlayer(command, "target"), race);
                                }))));
    }

    private static final SuggestionProvider<CommandSource> SUGGEST_SET = (source, builder) -> {
        List<String> suggestions = new ArrayList<>();

        for (int i = 0; i < ModValues.rankHollow.size(); i++)
        {
            suggestions.add(ModValues.rankHollow.get(i).replace(" ", ""));
        }

        for (int i = 0; i < ModValues.rankShinigami.size(); i++)
        {
            suggestions.add(ModValues.rankShinigami.get(i).replace(" ", ""));
        }

        return ISuggestionProvider.suggest(suggestions.stream(), builder);
    };

    private static int setRace(CommandSource commandSource, PlayerEntity player, String rank)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (containsIgnoringSpaces(ModValues.rankShinigami, rank) && entityStats.getRace().equals(ModValues.SHINIGAMI))
        {
            entityStats.setRank(findOriginalStringIgnoringSpaces(ModValues.rankShinigami, rank));
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);

            commandSource.sendSuccess(new TranslationTextComponent("command.rank.success"), true);
            return 1;
        }
        else if (!entityStats.getRace().equals(ModValues.SHINIGAMI) && !containsIgnoringSpaces(ModValues.rankHollow, rank))
        {
            commandSource.sendSuccess(new TranslationTextComponent("command.rank.not_shinigami"), true);
            return 0;
        }
        if (containsIgnoringSpaces(ModValues.rankHollow, rank) && entityStats.getRace().equals(ModValues.HOLLOW))
        {
            entityStats.setRank(findOriginalStringIgnoringSpaces(ModValues.rankHollow, rank));
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);

            commandSource.sendSuccess(new TranslationTextComponent("command.rank.success"), true);
            return 1;
        }
        else if (!entityStats.getRace().equals(ModValues.HOLLOW) && !containsIgnoringSpaces(ModValues.rankShinigami, rank))
        {
            commandSource.sendSuccess(new TranslationTextComponent("command.rank.not_hollow"), true);
            return 0;

        }


        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);

        commandSource.sendSuccess(new TranslationTextComponent("command.rank.success"), true);
        return 1;
    }

    public static boolean containsIgnoringSpaces(List<String> list, String target) {
        // Remove spaces from the target string
        String targetNoSpaces = target.replace(" ", "");

        for (String item : list) {
            // Remove spaces from the current list item
            String itemNoSpaces = item.replace(" ", "");

            // Compare the strings ignoring spaces
            if (itemNoSpaces.equals(targetNoSpaces)) {
                return true;
            }
        }
        return false;
    }

    public static String findOriginalStringIgnoringSpaces(List<String> list, String target) {
        String targetNoSpaces = target.replace(" ", "");
        for (String item : list) {
            String itemNoSpaces = item.replace(" ", "");
            if (itemNoSpaces.equals(targetNoSpaces)) {
                return item; // Return the original string with spaces
            }
        }
        return null; // Return null if no match is found
    }

}
