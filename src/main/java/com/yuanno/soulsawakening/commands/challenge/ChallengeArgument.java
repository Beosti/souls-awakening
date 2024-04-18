package com.yuanno.soulsawakening.commands.challenge;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.yuanno.soulsawakening.api.challenges.ChallengeCore;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.concurrent.CompletableFuture;

public class ChallengeArgument implements ArgumentType<ChallengeCore>
{
	@Override
	public ChallengeCore parse(StringReader reader) throws CommandSyntaxException
	{
		ResourceLocation resourcelocation = ResourceLocation.read(reader);
		ChallengeCore challenge = (ChallengeCore) GameRegistry.findRegistry(ChallengeCore.class).getValue(resourcelocation);
		return challenge;
	}

	public static ChallengeArgument challenge()
	{
		return new ChallengeArgument();
	}
	
	public static <S> ChallengeCore getChallenge(CommandContext<S> context, String name)
	{
		return context.getArgument(name, ChallengeCore.class);
	}

	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder)
	{
		StringReader stringreader = new StringReader(builder.getInput());
		stringreader.setCursor(builder.getStart());

		return this.suggestAbility(builder);
	}

	private CompletableFuture<Suggestions> suggestAbility(SuggestionsBuilder builder)
	{
		return ISuggestionProvider.suggestResource(GameRegistry.findRegistry(ChallengeCore.class).getKeys(), builder);
	}
}
