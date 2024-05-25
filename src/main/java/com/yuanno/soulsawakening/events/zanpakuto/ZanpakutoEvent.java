package com.yuanno.soulsawakening.events.zanpakuto;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.abilities.SoulSocietyKeyAbility;
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
import com.yuanno.soulsawakening.abilities.elements.thunder.ThunderStepAbility;
import com.yuanno.soulsawakening.abilities.elements.thunder.ThunderAttackAbility;
import com.yuanno.soulsawakening.abilities.elements.thunder.ThunderStrikeAbility;
import com.yuanno.soulsawakening.abilities.elements.water.AquaSlashAbility;
import com.yuanno.soulsawakening.abilities.elements.water.TidalWaveAbility;
import com.yuanno.soulsawakening.abilities.elements.water.WaterPrisonAbility;
import com.yuanno.soulsawakening.abilities.elements.wind.GaleForceAbility;
import com.yuanno.soulsawakening.abilities.elements.wind.WhirldWindDanceAbility;
import com.yuanno.soulsawakening.abilities.elements.wind.WindAttackAbility;
import com.yuanno.soulsawakening.abilities.shinso.LongRangeAbility;
import com.yuanno.soulsawakening.abilities.shinso.ShootAbility;
import com.yuanno.soulsawakening.abilities.shinso.WideShootAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.events.util.ZanpakutoChangeEvent;
import com.yuanno.soulsawakening.init.*;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SOpenChallengeScreenPacket;
import com.yuanno.soulsawakening.networking.server.SOpenCommandScreenPacket;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ZanpakutoEvent {


    /**
     * Cheks if the player is the rightful owner of the zanpakuto
     * if not cancels the basic attack event
     * @param event
     */
    @SubscribeEvent
    public static void basicAttack(LivingHurtEvent event)
    {
        if (event.getEntity().level.isClientSide)
            return;

        DamageSource damageSource = event.getSource();
        if (!(damageSource.getDirectEntity() instanceof LivingEntity))
            return;
        LivingEntity livingEntity = (LivingEntity) event.getSource().getDirectEntity();
        ItemStack item = livingEntity.getMainHandItem();
        if (!(item.getItem() instanceof ZanpakutoItem))
            return;
        if (item.getTag().getString("owner").isEmpty() || !item.getTag().getString("owner").equals(livingEntity.getDisplayName().getString()))
            event.setCanceled(true);
        else
            event.setCanceled(false);
    }

    /**
     * In shikai state when changing items it becomes sealed again
     * Whole ass turnaround instead of just taking event.getfrom() because of issues
     * @param event
     */
    @SubscribeEvent
    public static void onItemChange(LivingEquipmentChangeEvent event)
    {
        if (!(event.getEntity() instanceof PlayerEntity))
            return;
        PlayerEntity player = (PlayerEntity) event.getEntity();
        if (player.level.isClientSide)
            return;
        if (!(event.getSlot() == EquipmentSlotType.MAINHAND))
            return;
        if (!(event.getFrom().getItem().asItem() instanceof ZanpakutoItem))
            return;
        ItemStack zanpakutoStack = event.getFrom().getStack();
        String zanpakutoState = zanpakutoStack.getTag().getString("zanpakutoState");
        if (!(zanpakutoState.equals(ModValues.STATE.SHIKAI.name())))
            return;
        for (int i = 0; i < player.inventory.getContainerSize(); i++)
        {
            if (player.inventory.getItem(i).getItem() instanceof ZanpakutoItem)
            {
                CompoundNBT tagCompound = player.inventory.getItem(i).getTag();
                tagCompound.putString("zanpakutoState", ModValues.STATE.SEALED.name());
                player.inventory.getItem(i).setTag(tagCompound);
            }
        }
    }


    @SubscribeEvent
    public static void onZanpakutoChange(ZanpakutoChangeEvent event)
    {
        if (event.getPlayer().level.isClientSide)
            return;
        IEntityStats entityStats = EntityStatsCapability.get(event.getPlayer());
        ItemStack zanpakutoItem = event.getZanpakutoItem();
        if (zanpakutoItem.getTag().getString("zanpakutoState").equals(ModValues.STATE.ASAUCHI.name()))
        {
            event.getPlayer().sendMessage(new StringTextComponent("Cannot reach shikai in this state!"), Util.NIL_UUID);
            return;
        }
        if (entityStats.getShinigamiStats().getZanjutsuPoints() < ModConfig.zanpakuto_zanjutsu.get()) {
            String zanpakutoZanjutsuMessageString = String.format("Need to have at least %s Zanjutsu points!", ModConfig.zanpakuto_zanjutsu.get());
            event.getPlayer().sendMessage(new StringTextComponent(zanpakutoZanjutsuMessageString), Util.NIL_UUID);
            return;
        }
        if (ModConfig.zanpakuto_unlock.get().equals(ModConfig.ModConfigValues.KILLHOLLOW))
        {
            int elementalPoints = zanpakutoItem.getTag().getInt("element");
            if (elementalPoints < 5) {
                event.getPlayer().sendMessage(new StringTextComponent("Need to have at least 5 elemental points! You get them by killing hollows"), Util.NIL_UUID);
                return;
            }
        }
        String element = zanpakutoItem.getTag().getString("zanpakutoElement");
        if (element.isEmpty())
        {
            PacketHandler.sendTo(new SOpenCommandScreenPacket(), event.getPlayer());
            World world = event.getPlayer().level;
            Map<String, Integer> elementalPointsHash = new HashMap<>();

            int normalPoints = zanpakutoItem.getTag().getInt(ModValues.NORMAL);
            elementalPointsHash.put(ModValues.NORMAL, normalPoints);

            int darkPoints = zanpakutoItem.getTag().getInt(ModValues.DARK);
            elementalPointsHash.put(ModValues.DARK, darkPoints);

            int waterPoints = zanpakutoItem.getTag().getInt(ModValues.WATER);
            elementalPointsHash.put(ModValues.WATER, waterPoints);

            int airPoints = zanpakutoItem.getTag().getInt(ModValues.WIND);
            elementalPointsHash.put(ModValues.WIND, airPoints);

            int firePoints = zanpakutoItem.getTag().getInt(ModValues.FIRE);
            elementalPointsHash.put(ModValues.FIRE, firePoints);

            String elementChosen;
            if (ModConfig.zanpakuto_unlock.get().equals(ModConfig.ModConfigValues.KILLHOLLOW))
                elementChosen = calculateMostProbableElement(elementalPointsHash);
            else if (ModConfig.zanpakuto_unlock.get().equals(ModConfig.ModConfigValues.RANDOM))
                elementChosen = ModValues.ELEMENT.getRandomElementString();
            else
                elementChosen = ModValues.ELEMENT.getRandomElementString();
            CompoundNBT tagCompound = zanpakutoItem.getTag();
            tagCompound.putString("zanpakutoElement", elementChosen.toUpperCase());
            zanpakutoItem.setTag(tagCompound);
            IAbilityData abilityData = AbilityDataCapability.get(event.getPlayer());
            String uppercasedElement = elementChosen.toUpperCase();
            switch (uppercasedElement)
            {
                case ("DARK"):
                    abilityData.addUnlockedAbility(DarkStepAbility.INSTANCE);
                    abilityData.addUnlockedAbility(ShadowAttackAbility.INSTANCE);
                    abilityData.addUnlockedAbility(UmbralCloakAbility.INSTANCE);
                    break;
                case ("FIRE"):
                    abilityData.addUnlockedAbility(FireAttackAbility.INSTANCE);
                    abilityData.addUnlockedAbility(FireWaveAbility.INSTANCE);
                    abilityData.addUnlockedAbility(FireBallAbility.INSTANCE);
                    break;
                case ("HEAL"):
                    abilityData.addUnlockedAbility(HealingTouchingAbility.INSTANCE);
                    abilityData.addUnlockedAbility(RevitilazingAuraAbility.INSTANCE);
                    abilityData.addUnlockedAbility(SelfHealingAbility.INSTANCE);
                    break;
                case ("LIGHTNING"):
                    abilityData.addUnlockedAbility(ThunderStepAbility.INSTANCE);
                    abilityData.addUnlockedAbility(ThunderAttackAbility.INSTANCE);
                    abilityData.addUnlockedAbility(ThunderStrikeAbility.INSTANCE);
                    break;
                case ("LUNAR"):
                    abilityData.addUnlockedAbility(LunarBlessingAbility.INSTANCE);
                    abilityData.addUnlockedAbility(LunarCrescentAbility.INSTANCE);
                    abilityData.addUnlockedAbility(LunarWaveAbility.INSTANCE);
                    break;
                case ("NORMAL"):
                    abilityData.addUnlockedAbility(NormalBuffAbility.INSTANCE);
                    break;
                case ("POISON"):
                    abilityData.addUnlockedAbility(PoisonAttackAbility.INSTANCE);
                    abilityData.addUnlockedAbility(VenomousCloudAbility.INSTANCE);
                    abilityData.addUnlockedAbility(AdrenalineCloudAbility.INSTANCE);
                    break;
                case ("WATER"):
                    abilityData.addUnlockedAbility(AquaSlashAbility.INSTANCE);
                    abilityData.addUnlockedAbility(TidalWaveAbility.INSTANCE);
                    abilityData.addUnlockedAbility(WaterPrisonAbility.INSTANCE);
                    break;
                case ("WIND"):
                    abilityData.addUnlockedAbility(GaleForceAbility.INSTANCE);
                    abilityData.addUnlockedAbility(WhirldWindDanceAbility.INSTANCE);
                    abilityData.addUnlockedAbility(WindAttackAbility.INSTANCE);
                    break;
                case ("SHINSO"):
                    abilityData.addUnlockedAbility(ShootAbility.INSTANCE);
                    abilityData.addUnlockedAbility(WideShootAbility.INSTANCE);
                    abilityData.addUnlockedAbility(LongRangeAbility.INSTANCE);

            }
            abilityData.addUnlockedAbility(SoulSocietyKeyAbility.INSTANCE);
            ModAdvancements.SHIKAI.trigger((ServerPlayerEntity) event.getPlayer());

            PacketHandler.sendTo(new SSyncAbilityDataPacket(event.getPlayer().getId(), abilityData), event.getPlayer());

        }

        String state = zanpakutoItem.getTag().getString("zanpakutoState");
        if (state.equals(ModValues.STATE.SEALED.name()))
        {
            String command = entityStats.getShinigamiStats().getZanpakutoCommand();
            String name = entityStats.getShinigamiStats().getZanpakutoName();
            if (!command.isEmpty() && !name.isEmpty())
                event.getPlayer().sendMessage(new StringTextComponent(command.toUpperCase() + " " + name.toUpperCase()), Util.NIL_UUID);
            CompoundNBT tagCompound = zanpakutoItem.getTag();
            tagCompound.putString("zanpakutoState", ModValues.STATE.SHIKAI.name());
            zanpakutoItem.setTag(tagCompound);
        }
        else if(state.equals(ModValues.STATE.SHIKAI.name()))
        {
            CompoundNBT tagCompound = zanpakutoItem.getTag();
            tagCompound.putString("zanpakutoState", ModValues.STATE.SEALED.name());
            zanpakutoItem.setTag(tagCompound);
        }
    }

    public static String calculateMostProbableElement(Map<String, Integer> elementCounts) {
        // Calculate the total count
        int totalCount = elementCounts.values().stream().mapToInt(Integer::intValue).sum();

        // Special cases here
        // lightning
        if (elementCounts.get(ModValues.FIRE) >= 2 && elementCounts.get(ModValues.WIND) >= 2)
            return ModValues.LIGHTNING;
        else if (elementCounts.get(ModValues.DARK) >= 2 && elementCounts.get(ModValues.WATER) >= 2)
            return ModValues.POISON;
        else if (elementCounts.get(ModValues.NORMAL) >= 2 && elementCounts.get(ModValues.WATER) >= 2)
            return ModValues.HEAL;
        else if (elementCounts.get(ModValues.NORMAL) >= 2 && elementCounts.get(ModValues.DARK) >= 2)
            return ModValues.LUNAR;
        //else if (elementCounts.get(ModValues.WIND) >= 2 && elementCounts.get(ModValues.NORMAL) >= 2)
        //    return ModValues.SHINSO;
        // Initialize variables to track the most probable element and its probability
        String mostProbableElement = null;
        double maxProbability = 0.0;

        // Calculate the probability for each element and find the most probable one
        for (Map.Entry<String, Integer> entry : elementCounts.entrySet()) {
            String element = entry.getKey();
            int count = entry.getValue();

            // Calculate the probability
            double probability = (double) count / totalCount;

            // Update most probable element if needed
            if (probability > maxProbability) {
                mostProbableElement = element;
                maxProbability = probability;
            }
        }

        return mostProbableElement;
    }




}
