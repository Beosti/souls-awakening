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

        suggestions.addAll(ModValues.rankHollow);

        suggestions.addAll(ModValues.rankShinigami);

        return ISuggestionProvider.suggest(suggestions.stream(), builder);
    };

    private static int setRace(CommandSource commandSource, PlayerEntity player, String rank)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (ModValues.rankShinigami.contains(rank) && !entityStats.getRace().equals(ModValues.SHINIGAMI))
        {
            commandSource.sendSuccess(new TranslationTextComponent("command.rank.not_shinigami"), true);
            return 0;
        }
        else if (ModValues.rankHollow.contains(rank) && !entityStats.getRank().equals(ModValues.HOLLOW))
        {
            commandSource.sendSuccess(new TranslationTextComponent("command.rank.not_hollow"), true);
            return 0;
        }
        entityStats.setRank(rank);


        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);

        commandSource.sendSuccess(new TranslationTextComponent("command.rank.success"), true);
        return 1;
    }
}
