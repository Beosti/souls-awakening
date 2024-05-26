package com.yuanno.soulsawakening.commands.quest;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.yuanno.soulsawakening.data.quest.IQuestData;
import com.yuanno.soulsawakening.data.quest.QuestDataCapability;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncQuestDataPacket;
import com.yuanno.soulsawakening.quests.Quest;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Command to:
 * - add quests
 * - finish quests
 * - remove quests
 */
public class QuestCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("quest").requires((commandSource) -> commandSource.hasPermission(2))
                .then(Commands.argument("target", EntityArgument.player())
                        .then(Commands.argument("set", StringArgumentType.string()).suggests(SUGGEST_SET)
                                .then(Commands.argument("quest", QuestArgument.quest())
                                        .executes(context -> {
                                            String set = StringArgumentType.getString(context, "set");
                                            return setQuest(context.getSource(), EntityArgument.getPlayer(context, "target"), set, QuestArgument.getQuest(context, "quest"));
                                        })))));
    }

    private static final SuggestionProvider<CommandSource> SUGGEST_SET = (source, builder) -> {
        List<String> suggestions = new ArrayList<>();

        suggestions.add("add");
        suggestions.add("remove");
        suggestions.add("finish");
        return ISuggestionProvider.suggest(suggestions.stream(), builder);
    };

    private static int setQuest(CommandSource commandSource, PlayerEntity player, String set, Quest quest)
    {
        IQuestData questData = QuestDataCapability.get(player);
        switch (set) {
            case "add":
                if (questData.hasInProgressQuest(quest))
                    commandSource.sendFailure(new TranslationTextComponent("command.quest.progress"));
                else if (questData.hasFinishedQuest(quest))
                    commandSource.sendFailure(new TranslationTextComponent("command.quest.finished"));
                else {
                    questData.addInProgressQuest(quest);
                    PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), questData), player);
                    commandSource.sendSuccess(new TranslationTextComponent("command.quest.add_quest"), true);
                    return 1;
                }
                return 0;
            case "remove":
                if (!questData.hasInProgressQuest(quest) && !questData.hasFinishedQuest(quest))
                    commandSource.sendFailure(new TranslationTextComponent("command.quest.inexistent"));
                else {
                    questData.removeQuest(quest);
                    PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), questData), player);
                    commandSource.sendSuccess(new TranslationTextComponent("command.quest.remove_true"), true);
                    return 1;
                }
                return 0;
            case "finish":
                if (!questData.hasInProgressQuest(quest))
                    commandSource.sendFailure(new TranslationTextComponent("command.quest.inexistent"));
                else if (questData.hasFinishedQuest(quest))
                    commandSource.sendFailure(new TranslationTextComponent("command.quest.finished"));
                else {
                    questData.removeInProgressQuest(quest);
                    PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), questData), player);
                    commandSource.sendSuccess(new TranslationTextComponent("command.quest.finish"), true);
                    return 1;
                }
                return 0;
        }
        return 0;
    }
}
