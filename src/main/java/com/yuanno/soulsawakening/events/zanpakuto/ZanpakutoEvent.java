package com.yuanno.soulsawakening.events.zanpakuto;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.abilities.SoulSocietyKeyAbility;
import com.yuanno.soulsawakening.abilities.elements.fire.*;
import com.yuanno.soulsawakening.abilities.elements.heal.*;
import com.yuanno.soulsawakening.abilities.elements.lunar.*;
import com.yuanno.soulsawakening.abilities.elements.normal.NormalBuffAbility;
import com.yuanno.soulsawakening.abilities.elements.poison.AdrenalineCloudAbility;
import com.yuanno.soulsawakening.abilities.elements.poison.PoisonAttackAbility;
import com.yuanno.soulsawakening.abilities.elements.poison.PoisonStingAbility;
import com.yuanno.soulsawakening.abilities.elements.poison.VenomousCloudAbility;
import com.yuanno.soulsawakening.abilities.elements.shadow.*;
import com.yuanno.soulsawakening.abilities.elements.thunder.*;
import com.yuanno.soulsawakening.abilities.elements.water.*;
import com.yuanno.soulsawakening.abilities.elements.wind.*;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.events.util.ZanpakutoChangeEvent;
import com.yuanno.soulsawakening.init.*;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import com.yuanno.soulsawakening.networking.PacketHandler;
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
        if (entityStats.getShinigamiStats().getZanjutsuPoints() < ModConfig.ZANPAKUTO_ZANJUTSU.get()) {
            String zanpakutoZanjutsuMessageString = String.format("Need to have at least %s Zanjutsu points!", ModConfig.ZANPAKUTO_ZANJUTSU.get());
            event.getPlayer().sendMessage(new StringTextComponent(zanpakutoZanjutsuMessageString), Util.NIL_UUID);
            return;
        }
        if (ModConfig.ZANPAKUTO_UNLOCK.get().equals(ModConfig.ModConfigValues.KILLHOLLOW))
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
            if (ModConfig.ZANPAKUTO_UNLOCK.get().equals(ModConfig.ModConfigValues.KILLHOLLOW))
                elementChosen = calculateMostProbableElement(elementalPointsHash);
            else if (ModConfig.ZANPAKUTO_UNLOCK.get().equals(ModConfig.ModConfigValues.RANDOM))
                elementChosen = ModValues.ELEMENT.getRandomElementString();
            else
                elementChosen = ModValues.ELEMENT.getRandomElementString();
            CompoundNBT tagCompound = zanpakutoItem.getTag();
            tagCompound.putString("zanpakutoElement", elementChosen.toUpperCase());
            zanpakutoItem.setTag(tagCompound);
            IAbilityData abilityData = AbilityDataCapability.get(event.getPlayer());
            String uppercasedElement = elementChosen.toUpperCase();
            Random random = new Random();
            boolean randomizerConfigFlag = ModConfig.ZANPAKUTO_ABILITIES_RANDOMIZER.get() && random.nextBoolean();
            switch (uppercasedElement)
            {
                case ("DARK"):
                    if (entityStats.getSpeedStat() > entityStats.getReiatsuPoints() || randomizerConfigFlag)
                        abilityData.addUnlockedAbility(DarkStepAbility.INSTANCE);
                    else
                        abilityData.addUnlockedAbility(ShadowCloneAbility.INSTANCE);
                    if (entityStats.getShinigamiStats().getZanjutsuPoints() - ModConfig.ZANPAKUTO_ZANJUTSU.get() < entityStats.getReiatsuPoints() || randomizerConfigFlag)
                        abilityData.addUnlockedAbility(DarkBallAbility.INSTANCE);
                    else
                        abilityData.addUnlockedAbility(ShadowAttackAbility.INSTANCE);
                    abilityData.addUnlockedAbility(UmbralCloakAbility.INSTANCE);
                    break;
                case ("FIRE"):
                    if (entityStats.getReiatsuPoints() > entityStats.getShinigamiStats().getZanjutsuPoints() - ModConfig.ZANPAKUTO_ZANJUTSU.get() || randomizerConfigFlag)
                        abilityData.addUnlockedAbility(FireProwessAbility.INSTANCE);
                    else
                        abilityData.addUnlockedAbility(FireAttackAbility.INSTANCE);
                    if (entityStats.getReiatsuPoints() >= entityStats.getShinigamiStats().getHakudaPoints() || randomizerConfigFlag)
                        abilityData.addUnlockedAbility(FireWaveAbility.INSTANCE);
                    else
                        abilityData.addUnlockedAbility(FireCoatAbility.INSTANCE);
                    abilityData.addUnlockedAbility(FireBallAbility.INSTANCE);
                    break;
                case ("HEAL"):
                    if (entityStats.getShinigamiStats().getZanjutsuPoints() - ModConfig.ZANPAKUTO_ZANJUTSU.get() > entityStats.getReiatsuPoints() || randomizerConfigFlag)
                        abilityData.addUnlockedAbility(HealingTouchAbility.INSTANCE);
                    else
                        abilityData.addUnlockedAbility(SecondChanceAbility.INSTANCE);
                    if (entityStats.getShinigamiStats().getHakudaPoints() >= entityStats.getReiatsuPoints() || randomizerConfigFlag)
                        abilityData.addUnlockedAbility(DamageHealAbility.INSTANCE);
                    else
                        abilityData.addUnlockedAbility(RevitilazingAuraAbility.INSTANCE);
                    abilityData.addUnlockedAbility(SelfHealingAbility.INSTANCE);
                    break;
                case ("LIGHTNING"):
                    if (entityStats.getSpeedStat() > entityStats.getReiatsuPoints() || randomizerConfigFlag)
                        abilityData.addUnlockedAbility(ThunderBoostAbility.INSTANCE);
                    else
                        abilityData.addUnlockedAbility(ThunderballAbility.INSTANCE);
                    if (entityStats.getSpeedStat() >= entityStats.getShinigamiStats().getZanjutsuPoints() - ModConfig.ZANPAKUTO_ZANJUTSU.get() || randomizerConfigFlag)
                        abilityData.addUnlockedAbility(ThunderStepAbility.INSTANCE);
                    else
                        abilityData.addUnlockedAbility(ThunderAttackAbility.INSTANCE);
                    abilityData.addUnlockedAbility(ThunderStrikeAbility.INSTANCE);
                    break;
                case ("LUNAR"):
                    if (entityStats.getReiatsuPoints() >= entityStats.getShinigamiStats().getHakudaPoints() || randomizerConfigFlag)
                        abilityData.addUnlockedAbility(LunarBlessingAbility.INSTANCE);
                    else
                        abilityData.addUnlockedAbility(MoonLightAbility.INSTANCE);
                    abilityData.addUnlockedAbility(LunarCrescentAbility.INSTANCE);
                    if (entityStats.getReiatsuPoints() >= entityStats.getShinigamiStats().getZanjutsuPoints() - ModConfig.ZANPAKUTO_ZANJUTSU.get() || randomizerConfigFlag)
                        abilityData.addUnlockedAbility(LunarWaveAbility.INSTANCE);
                    else
                        abilityData.addUnlockedAbility(LunarBlindnessAbility.INSTANCE);
                    break;
                case ("NORMAL"):
                    abilityData.addUnlockedAbility(NormalBuffAbility.INSTANCE);
                    break;
                case ("POISON"):
                    if (entityStats.getShinigamiStats().getZanjutsuPoints() - ModConfig.ZANPAKUTO_ZANJUTSU.get() > entityStats.getReiatsuPoints() || randomizerConfigFlag)
                        abilityData.addUnlockedAbility(PoisonAttackAbility.INSTANCE);
                    else
                        abilityData.addUnlockedAbility(PoisonStingAbility.INSTANCE);
                    if (entityStats.getShinigamiStats().getZanjutsuPoints() - ModConfig.ZANPAKUTO_ZANJUTSU.get() < entityStats.getShinigamiStats().getHakudaPoints() || randomizerConfigFlag)
                        abilityData.addUnlockedAbility(VenomousCloudAbility.INSTANCE);
                    else
                        abilityData.addUnlockedAbility(AdrenalineCloudAbility.INSTANCE);
                    break;
                case ("WATER"):
                    if (random.nextBoolean())
                        abilityData.addUnlockedAbility(WaterPressureAbility.INSTANCE);
                    else
                        abilityData.addUnlockedAbility(WaterSurgeAbility.INSTANCE);
                    if (random.nextBoolean())
                        abilityData.addUnlockedAbility(AquaSlashAbility.INSTANCE);
                    else
                        abilityData.addUnlockedAbility(TidalWaveAbility.INSTANCE);
                    abilityData.addUnlockedAbility(WaterPrisonAbility.INSTANCE);
                    break;
                case ("WIND"):
                    abilityData.addUnlockedAbility(GaleForceAbility.INSTANCE);
                    if (entityStats.getReiatsuPoints() >= entityStats.getSpeedStat() || randomizerConfigFlag)
                        abilityData.addUnlockedAbility(WhirldWindDanceAbility.INSTANCE);
                    else
                        abilityData.addUnlockedAbility(WindDodgeAbility.INSTANCE);
                    if (entityStats.getShinigamiStats().getZanjutsuPoints() - ModConfig.ZANPAKUTO_ZANJUTSU.get() >= entityStats.getReiatsuPoints() || randomizerConfigFlag)
                        abilityData.addUnlockedAbility(WindAttackAbility.INSTANCE);
                    else
                        abilityData.addUnlockedAbility(WindBladeAbility.INSTANCE);
                    break;
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
