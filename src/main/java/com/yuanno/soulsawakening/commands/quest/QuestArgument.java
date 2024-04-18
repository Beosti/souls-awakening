package com.yuanno.soulsawakening.commands.quest;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.quests.Quest;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.concurrent.CompletableFuture;

public class QuestArgument implements ArgumentType<Quest> {

    @Override
    public Quest parse(StringReader reader) throws CommandSyntaxException
    {
        ResourceLocation resourceLocation = ResourceLocation.read(reader);
        Quest quest = GameRegistry.findRegistry(Quest.class).getValue(resourceLocation);
        return quest;
    }

    public static QuestArgument quest()
    {
        return new QuestArgument();
    }

    public static <S> Quest getQuest(CommandContext<S> context, String name)
    {
        return context.getArgument(name, Quest.class);
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder)
    {
        StringReader stringReader = new StringReader(builder.getInput());
        stringReader.setCursor(builder.getStart());

        return this.suggestQuest(builder);
    }

    private CompletableFuture<Suggestions> suggestQuest(SuggestionsBuilder builder)
    {
        return ISuggestionProvider.suggestResource(GameRegistry.findRegistry(Quest.class).getKeys(), builder);
    }
}
