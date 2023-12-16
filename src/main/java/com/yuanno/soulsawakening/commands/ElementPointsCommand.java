package com.yuanno.soulsawakening.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.yuanno.soulsawakening.abilities.elements.fire.FireAttackAbility;
import com.yuanno.soulsawakening.abilities.elements.fire.FireBallAbility;
import com.yuanno.soulsawakening.abilities.elements.fire.FireWaveAbility;
import com.yuanno.soulsawakening.abilities.elements.heal.HealingTouchingAbility;
import com.yuanno.soulsawakening.abilities.elements.heal.RevitilazingAuraAbility;
import com.yuanno.soulsawakening.abilities.elements.heal.SelfHealingAbility;
import com.yuanno.soulsawakening.abilities.elements.lunar.LunarBlessingAbility;
import com.yuanno.soulsawakening.abilities.elements.lunar.LunarCrescentAbility;
import com.yuanno.soulsawakening.abilities.elements.lunar.LunarWaveAbility;
import com.yuanno.soulsawakening.abilities.elements.normal.NormalBuffAbility;
import com.yuanno.soulsawakening.abilities.elements.poison.AdrenalineCloudAbility;
import com.yuanno.soulsawakening.abilities.elements.poison.PoisonAttackAbility;
import com.yuanno.soulsawakening.abilities.elements.poison.VenomousCloudAbility;
import com.yuanno.soulsawakening.abilities.elements.shadow.DarkStepAbility;
import com.yuanno.soulsawakening.abilities.elements.shadow.ShadowAttackAbility;
import com.yuanno.soulsawakening.abilities.elements.shadow.UmbralCloakAbility;
import com.yuanno.soulsawakening.abilities.elements.thunder.LightningStepAbility;
import com.yuanno.soulsawakening.abilities.elements.thunder.ThunderAttackAbility;
import com.yuanno.soulsawakening.abilities.elements.thunder.ThunderStrikeAbility;
import com.yuanno.soulsawakening.abilities.elements.water.AquaSlashAbility;
import com.yuanno.soulsawakening.abilities.elements.water.TidalWaveAbility;
import com.yuanno.soulsawakening.abilities.elements.water.WaterPrisonAbility;
import com.yuanno.soulsawakening.abilities.elements.wind.GaleForceAbility;
import com.yuanno.soulsawakening.abilities.elements.wind.WhirldWindDanceAbility;
import com.yuanno.soulsawakening.abilities.elements.wind.WindAttackAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;

public class ElementPointsCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("zanpakuto").requires((commandSource) -> commandSource.hasPermission(3))
                .then(Commands.argument("target", EntityArgument.player())
                        .then(Commands.argument("points", IntegerArgumentType.integer())
                                .then(Commands.argument("element", StringArgumentType.string()).suggests(SUGGEST_SET)
                                .executes((command) ->
                                {
                                    String element = StringArgumentType.getString(command, "element");
                                    Integer points = IntegerArgumentType.getInteger(command, "points");
                                    return setZanpakutoElement(command.getSource(), EntityArgument.getPlayer(command, "target"), element, points);
                                })))));
    }

    private static final SuggestionProvider<CommandSource> SUGGEST_SET = (source, builder) -> {
        List<String> suggestions = new ArrayList<>();

        //suggestions.add(ZanpakutoItem.ELEMENT.DARK.name());
        //suggestions.add(ZanpakutoItem.ELEMENT.FIRE.name());
        //suggestions.add(ZanpakutoItem.ELEMENT.HEAL.name());
        suggestions.add("thunder");
        //suggestions.add(ZanpakutoItem.ELEMENT.LUNAR.name());
        suggestions.add("normal");
        suggestions.add("poison");
        suggestions.add("water");
        suggestions.add("wind");

        return ISuggestionProvider.suggest(suggestions.stream(), builder);
    };

    private static int setZanpakutoElement(CommandSource commandSource, PlayerEntity player, String element, Integer pointsAmount)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        IAbilityData abilityData = AbilityDataCapability.get(player);
        if (!(entityStats.getRace().equals(ModValues.SHINIGAMI) || entityStats.getRace().equals(ModValues.FULLBRINGER)))
        {
            commandSource.sendSuccess(new TranslationTextComponent("Need to be a shinigami or fullbringer!"), true);
            return 0;
        }
        if (!player.getMainHandItem().getItem().equals(ModItems.ZANPAKUTO.get()))
        {
            commandSource.sendSuccess(new TranslationTextComponent("Have to hold a zanpakuto in hand!"), true);
            return 0;
        }
        ItemStack zanpakutoItem = player.getMainHandItem();
        CompoundNBT tagCompound = zanpakutoItem.getTag();
        int elementalPoints = tagCompound.getInt("element");
        if (elementalPoints + pointsAmount > 5)
        {
            commandSource.sendSuccess(new TranslationTextComponent("The total amount of elemental points cannot exceed 5!"), true);
            return 0;
        }
        int specialPoints = tagCompound.getInt(element);
        tagCompound.putInt(element, specialPoints + pointsAmount);
        tagCompound.putInt("element", elementalPoints + pointsAmount);
        zanpakutoItem.setTag(tagCompound);




        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);

        /*
        IEntityStats entityStats = EntityStatsCapability.get(player);
        entityStats.setRace(race);
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);

        commandSource.sendSuccess(new TranslationTextComponent("set race of " + player.getDisplayName().getString() + " to " + race), true);

         */
        return 1;
    }
}
