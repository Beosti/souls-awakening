package com.yuanno.soulsawakening.events.zanpakuto;

import com.yuanno.soulsawakening.Main;
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
import com.yuanno.soulsawakening.api.SoulboundItemHelper;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModResources;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.COpenPlayerScreenPacket;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
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

    @SubscribeEvent
    public static void onZanpakutoCraftin(PlayerEvent.ItemCraftedEvent event)
    {
        if (event.getCrafting().getItem() == ModItems.ZANPAKUTO.get())
        {
            SoulboundItemHelper.setOwner(event.getCrafting().getStack(), event.getPlayer());
            ZanpakutoItem zanpakutoItem = (ZanpakutoItem) event.getCrafting().getItem();
            zanpakutoItem.setOwner(event.getPlayer(), event.getCrafting());
            IEntityStats entityStats = EntityStatsCapability.get(event.getPlayer());
            if (entityStats.getRace().equals(ModValues.SPIRIT))
                entityStats.setRace(ModValues.SHINIGAMI);
        }
    }

    @SubscribeEvent
    public static void onZanpakutoChange(ZanpakutoChangeEvent event)
    {
        if (event.getPlayer().level.isClientSide)
            return;
        IEntityStats entityStats = EntityStatsCapability.get(event.getPlayer());
        ItemStack zanpakutoItem = event.getZanpakutoItem();


        if (entityStats.getZanjutsuPoints() < 20) {
            event.getPlayer().sendMessage(new StringTextComponent("Need to have at least 20 Zanjutsu points!"), Util.NIL_UUID);
            return;
        }
        int elementalPoints = zanpakutoItem.getTag().getInt("element");
        if (elementalPoints < 5)
        {
            event.getPlayer().sendMessage(new StringTextComponent("Need to have at least 5 elemental points! You get them by killing hollows"), Util.NIL_UUID);
            return;
        }
        String element = zanpakutoItem.getTag().getString("zanpakutoElement");
        if (element.isEmpty())
        {
            Map<String, Integer> elementalPointsHash = new HashMap<>();

            int thunderPoints = zanpakutoItem.getTag().getInt("lightning"); // TODO change this to modvalues instead of just string
            elementalPointsHash.put("lightning", thunderPoints);

            int normalPoints = zanpakutoItem.getTag().getInt("normal");
            elementalPointsHash.put("normal", normalPoints);

            int poisonPoints = zanpakutoItem.getTag().getInt("poison");
            elementalPointsHash.put("poison", poisonPoints);

            int waterPoints = zanpakutoItem.getTag().getInt("water");
            elementalPointsHash.put("water", waterPoints);

            int airPoints = zanpakutoItem.getTag().getInt("air");
            elementalPointsHash.put("air", airPoints);

            String elementChosen = calculateMostProbableElement(elementalPointsHash);
            CompoundNBT tagCompound = zanpakutoItem.getTag();
            tagCompound.putString("zanpakutoElement", elementChosen.toUpperCase());
            System.out.println(elementChosen);
            zanpakutoItem.setTag(tagCompound);
            IAbilityData abilityData = AbilityDataCapability.get(event.getPlayer());
            elementChosen.toUpperCase();

            switch (elementChosen)
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
                    abilityData.addUnlockedAbility(LightningStepAbility.INSTANCE);
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
            }

            PacketHandler.sendTo(new SSyncAbilityDataPacket(event.getPlayer().getId(), abilityData), event.getPlayer());

        }

        String state = zanpakutoItem.getTag().getString("zanpakutoState");
        if (state.equals(ModResources.STATE.SEALED.name()))
        {
            CompoundNBT tagCompound = zanpakutoItem.getTag();
            tagCompound.putString("zanpakutoState", ModResources.STATE.SHIKAI.name());
            zanpakutoItem.setTag(tagCompound);
        }
        else if(state.equals(ModResources.STATE.SHIKAI.name()))
        {
            CompoundNBT tagCompound = zanpakutoItem.getTag();
            tagCompound.putString("zanpakutoState", ModResources.STATE.SEALED.name());
            zanpakutoItem.setTag(tagCompound);
        }
    }

    public static String calculateMostProbableElement(Map<String, Integer> elementCounts) {
        // Calculate the total count
        int totalCount = elementCounts.values().stream().mapToInt(Integer::intValue).sum();

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
