package com.yuanno.soulsawakening.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.entity.hollow.HollowStats;
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

public class RaceCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("race").requires((commandSource) -> commandSource.hasPermission(2))
                .then(Commands.argument("target", EntityArgument.player())
                        .then(Commands.argument("race", StringArgumentType.string()).suggests(SUGGEST_SET)
                                .executes((command) ->
                                {
                                    String race = StringArgumentType.getString(command, "race");

                                    return setRace(command.getSource(), EntityArgument.getPlayer(command, "target"), race);
                                }))));
    }

    private static final SuggestionProvider<CommandSource> SUGGEST_SET = (source, builder) -> {
        List<String> suggestions = new ArrayList<>();

        suggestions.add(ModValues.SHINIGAMI);
        suggestions.add(ModValues.HOLLOW);
        suggestions.add(ModValues.FULLBRINGER);
        suggestions.add(ModValues.SPIRIT);
        suggestions.add(ModValues.HUMAN);
        suggestions.add(ModValues.QUINCY);

        return ISuggestionProvider.suggest(suggestions.stream(), builder);
    };

    private static int setRace(CommandSource commandSource, PlayerEntity player, String race)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        entityStats.setRace(race);
        if (race.equals(ModValues.SHINIGAMI) && !entityStats.hasShinigamiStats())
            entityStats.setShinigamiStats(new ShinigamiStats());
        if (race.equals(ModValues.HOLLOW) && !entityStats.hasHollowStats()) {
            entityStats.setHollowStats(new HollowStats());
            entityStats.setRank(ModValues.BASE);
        }
        if (race.equals(ModValues.QUINCY) && !entityStats.hasQuincyStats())
            entityStats.setQuincyStats(new QuincyStats());

        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);

        commandSource.sendSuccess(new TranslationTextComponent("set race of " + player.getDisplayName().getString() + " to " + race), true);
        return 1;
    }
}
