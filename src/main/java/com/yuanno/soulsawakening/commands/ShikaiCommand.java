package com.yuanno.soulsawakening.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.yuanno.soulsawakening.abilities.elements.fire.FireAttackAbility;
import com.yuanno.soulsawakening.abilities.elements.fire.FireBallAbility;
import com.yuanno.soulsawakening.abilities.elements.fire.FireWaveAbility;
import com.yuanno.soulsawakening.abilities.elements.heal.HealingTouchAbility;
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
import com.yuanno.soulsawakening.abilities.elements.thunder.ThunderStepAbility;
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

public class ShikaiCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("element").requires((commandSource) -> commandSource.hasPermission(2))
                .then(Commands.argument("target", EntityArgument.player())
                        .then(Commands.argument("element", StringArgumentType.string()).suggests(SUGGEST_SET)
                                .executes((command) ->
                                {
                                    String element = StringArgumentType.getString(command, "element");

                                    return setZanpakutoElement(command.getSource(), EntityArgument.getPlayer(command, "target"), element);
                                }))));
    }

    private static final SuggestionProvider<CommandSource> SUGGEST_SET = (source, builder) -> {
        List<String> suggestions = new ArrayList<>();

        suggestions.add(ModValues.ELEMENT.DARK.name());
        suggestions.add(ModValues.ELEMENT.FIRE.name());
        suggestions.add(ModValues.ELEMENT.HEAL.name());
        suggestions.add(ModValues.ELEMENT.LIGHTNING.name());
        suggestions.add(ModValues.ELEMENT.LUNAR.name());
        suggestions.add(ModValues.ELEMENT.NORMAL.name());
        suggestions.add(ModValues.ELEMENT.POISON.name());
        suggestions.add(ModValues.ELEMENT.WATER.name());
        suggestions.add(ModValues.ELEMENT.WIND.name());
        //suggestions.add(ModValues.ELEMENT.SHINSO.name());

        return ISuggestionProvider.suggest(suggestions.stream(), builder);
    };

    private static int setZanpakutoElement(CommandSource commandSource, PlayerEntity player, String element)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        IAbilityData abilityData = AbilityDataCapability.get(player);
        if (!(entityStats.getRace().equals(ModValues.SHINIGAMI) || entityStats.getRace().equals(ModValues.FULLBRINGER)))
        {
            commandSource.sendSuccess(new TranslationTextComponent("Need to be a shinigami or fullbringer!"), true);
            return 0;
        }
        if (!(player.getMainHandItem().getItem().asItem() instanceof ZanpakutoItem))
        {
            commandSource.sendSuccess(new TranslationTextComponent("Have to hold a zanpakuto in hand!"), true);
            return 0;
        }
        ItemStack zanpakutoItem = player.getMainHandItem();
        CompoundNBT tagCompound = zanpakutoItem.getTag();
        tagCompound.putString("zanpakutoElement", element);
        zanpakutoItem.setTag(tagCompound);
        abilityData.clearUnlockedAbilities();

        switch (element)
        {
            case "DARK":
                abilityData.addUnlockedAbility(DarkStepAbility.INSTANCE);
                abilityData.addUnlockedAbility(ShadowAttackAbility.INSTANCE);
                abilityData.addUnlockedAbility(UmbralCloakAbility.INSTANCE);
                break;
            case "FIRE":
                abilityData.addUnlockedAbility(FireAttackAbility.INSTANCE);
                abilityData.addUnlockedAbility(FireWaveAbility.INSTANCE);
                abilityData.addUnlockedAbility(FireBallAbility.INSTANCE);
                break;
            case "HEAL":
                abilityData.addUnlockedAbility(HealingTouchAbility.INSTANCE);
                abilityData.addUnlockedAbility(RevitilazingAuraAbility.INSTANCE);
                abilityData.addUnlockedAbility(SelfHealingAbility.INSTANCE);
                break;
            case "LIGHTNING":
                abilityData.addUnlockedAbility(ThunderStepAbility.INSTANCE);
                abilityData.addUnlockedAbility(ThunderAttackAbility.INSTANCE);
                abilityData.addUnlockedAbility(ThunderStrikeAbility.INSTANCE);
                break;
            case "LUNAR":
                abilityData.addUnlockedAbility(LunarBlessingAbility.INSTANCE);
                abilityData.addUnlockedAbility(LunarCrescentAbility.INSTANCE);
                abilityData.addUnlockedAbility(LunarWaveAbility.INSTANCE);
                break;
            case "NORMAL":
                abilityData.addUnlockedAbility(NormalBuffAbility.INSTANCE);
                break;
            case "POISON":
                abilityData.addUnlockedAbility(PoisonAttackAbility.INSTANCE);
                abilityData.addUnlockedAbility(VenomousCloudAbility.INSTANCE);
                abilityData.addUnlockedAbility(AdrenalineCloudAbility.INSTANCE);
                break;
            case "WATER":
                abilityData.addUnlockedAbility(AquaSlashAbility.INSTANCE);
                abilityData.addUnlockedAbility(TidalWaveAbility.INSTANCE);
                abilityData.addUnlockedAbility(WaterPrisonAbility.INSTANCE);
                break;
            case "WIND":
                abilityData.addUnlockedAbility(GaleForceAbility.INSTANCE);
                abilityData.addUnlockedAbility(WhirldWindDanceAbility.INSTANCE);
                abilityData.addUnlockedAbility(WindAttackAbility.INSTANCE);
                break;
        }


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
