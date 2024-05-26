package com.yuanno.soulsawakening.commands.challenge;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.yuanno.soulsawakening.api.challenges.Challenge;
import com.yuanno.soulsawakening.api.challenges.ChallengeCore;
import com.yuanno.soulsawakening.api.challenges.ChallengeDifficulty;
import com.yuanno.soulsawakening.data.challenges.ChallengesDataCapability;
import com.yuanno.soulsawakening.data.challenges.IChallengesData;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncChallengeDataPacket;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.server.command.EnumArgument;

public class ChallengeCommand
{
	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("challenge").requires(source -> source.hasPermission(3));

		builder
			.then(Commands.literal("finish")
				.then(Commands.argument("challenge", ChallengeArgument.challenge())
				.then(Commands.argument("difficulty", EnumArgument.enumArgument(ChallengeDifficulty.class))
				.then(Commands.argument("target", EntityArgument.player())
					.executes(context -> finishChallenge(context, ChallengeArgument.getChallenge(context, "challenge"), context.getArgument("difficulty", ChallengeDifficulty.class), EntityArgument.getPlayer(context, "target")))))))
			.then(Commands.literal("give")
				.then(Commands.argument("challenge", ChallengeArgument.challenge())
				.then(Commands.argument("target", EntityArgument.player())
					.executes(context -> giveChallenge(context, ChallengeArgument.getChallenge(context, "challenge"), EntityArgument.getPlayer(context, "target"))))))
			.then(Commands.literal("unfinish")
				.then(Commands.argument("challenge", ChallengeArgument.challenge())
				.then(Commands.argument("difficulty", EnumArgument.enumArgument(ChallengeDifficulty.class))
				.then(Commands.argument("target", EntityArgument.player())
					.executes(context -> unfinishChallenge(context, ChallengeArgument.getChallenge(context, "challenge"), context.getArgument("difficulty", ChallengeDifficulty.class), EntityArgument.getPlayer(context, "target")))))))
			.then(Commands.literal("remove")
				.then(Commands.argument("challenge", ChallengeArgument.challenge())
				.then(Commands.argument("target", EntityArgument.player())
					.executes(context -> removeChallenge(context, ChallengeArgument.getChallenge(context, "challenge"), EntityArgument.getPlayer(context, "target"))))));
		
		dispatcher.register(builder);
	}
	
	private static int unfinishChallenge(CommandContext<CommandSource> context, ChallengeCore core, ChallengeDifficulty difficulty, ServerPlayerEntity player)
	{
		IChallengesData props = ChallengesDataCapability.get(player);
		
		Challenge challenge = props.getChallenge(core);
		if(challenge != null)
		{
			challenge.setComplete(false);
			PacketHandler.sendTo(new SSyncChallengeDataPacket(player.getId(), props), player);
		}
		else
			player.sendMessage(new StringTextComponent("You haven't finished this challenge!"), Util.NIL_UUID);
		
		return 1;
	}
	
	private static int finishChallenge(CommandContext<CommandSource> context, ChallengeCore core, ChallengeDifficulty difficulty, ServerPlayerEntity player)
	{
		IChallengesData props = ChallengesDataCapability.get(player);
		
		Challenge challenge = props.getChallenge(core);
		if(challenge != null)
		{
			challenge.setComplete(true);
			PacketHandler.sendTo(new SSyncChallengeDataPacket(player.getId(), props), player);
		}
		else
			player.sendMessage(new StringTextComponent("You don't have this challenge!"), Util.NIL_UUID);
		
		return 1;
	}
	
	private static int giveChallenge(CommandContext<CommandSource> context, ChallengeCore core, ServerPlayerEntity player) {
		IChallengesData props = ChallengesDataCapability.get(player);

		if (!props.hasChallenge(core)) {
			props.addChallenge(core);
			PacketHandler.sendTo(new SSyncChallengeDataPacket(player.getId(), props), player);
		}
		else {
			player.sendMessage(new StringTextComponent("You aleady have this challenge!"), Util.NIL_UUID);
		}

		return 1;
	}
	
	private static int removeChallenge(CommandContext<CommandSource> context, ChallengeCore core, ServerPlayerEntity player)
	{
		IChallengesData props = ChallengesDataCapability.get(player);
		
		if(props.hasChallenge(core))
		{
			props.removeChallenge(core);
			PacketHandler.sendTo(new SSyncChallengeDataPacket(player.getId(), props), player);
		}
		else
			player.sendMessage(new StringTextComponent("You don't have this challenge!"), Util.NIL_UUID);
		
		return 1;
	}
}
