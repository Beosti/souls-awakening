package com.yuanno.soulsawakening.commands.ability;

import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AbilityCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("ability").requires((commandSource) -> commandSource.hasPermission(2))
                .then(Commands.argument("target", EntityArgument.player())
                        .then(Commands.argument("set", StringArgumentType.string()).suggests(SUGGEST_SET)
                                .then(Commands.argument("ability", AbilityArgument.ability())
                                        .then(Commands.argument("in bar", BoolArgumentType.bool()).suggests(SUGGEST_BAR)
                                .executes(context ->
                                {
                                    String set = StringArgumentType.getString(context, "set");
                                    Boolean inBar = BoolArgumentType.getBool(context, "in bar");
                                    return setAbility(context.getSource(), EntityArgument.getPlayer(context, "target"), set, AbilityArgument.getAbility(context, "ability"), inBar);
                                }))))));
    }

    private static final SuggestionProvider<CommandSource> SUGGEST_SET = (source, builder) -> {
        List<String> suggestions = new ArrayList<>();

        suggestions.add("add");
        suggestions.add("remove");

        return ISuggestionProvider.suggest(suggestions.stream(), builder);
    };

    private static final SuggestionProvider<CommandSource> SUGGEST_BAR = (source, builder) -> {
        List<String> suggestions = new ArrayList<>();

        suggestions.add("true");
        suggestions.add("false");

        return ISuggestionProvider.suggest(suggestions.stream(), builder);
    };

    private static int setAbility(CommandSource commandSource, PlayerEntity player, String set, Ability ability, Boolean inBar)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        if (set.equals("add"))
            abilityData.addUnlockedAbility(ability);
        else if (set.equals("remove"))
            abilityData.removeUnlockedAbility(ability);
        if (inBar)
            abilityData.addAbilityToBar(ability);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        commandSource.sendSuccess(new TranslationTextComponent(set + " the ability " + ability.getName() + " from player: " + player.getDisplayName().getString()), true);
        return 1;
    }
    private static Collection<ServerPlayerEntity> getDefaultCollection(CommandContext<CommandSource> context) throws CommandSyntaxException
    {
        return Lists.newArrayList(context.getSource().getPlayerOrException());
    }


    private static int addAbility(CommandContext<CommandSource> context, Ability ability, Collection<ServerPlayerEntity> targets)
    {
        for (ServerPlayerEntity player : targets)
        {
            IAbilityData props = AbilityDataCapability.get(player);

            props.addUnlockedAbility(ability);

            //if(BeoDebug.isDebug())
              //  player.sendMessage(new StringTextComponent(TextFormatting.GREEN + "" + TextFormatting.ITALIC + "[DEBUG] " + ability.getName() + " unlocked for " + player.getName().getString()), Util.NIL_UUID);

            PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), props), player);
        }

        return 1;
    }
}
