package com.yuanno.soulsawakening.events.zanpakuto;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.SoulboundItemHelper;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModResources;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.COpenPlayerScreenPacket;
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

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
            int thunderPoints = zanpakutoItem.getTag().getInt("thunder");
            int normalPoints = zanpakutoItem.getTag().getInt("normal");
            int poisonPoints = zanpakutoItem.getTag().getInt("poison");
            int waterPoints = zanpakutoItem.getTag().getInt("water");
            int airPoints = zanpakutoItem.getTag().getInt("air");

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

    public static String calculateElements(Map<String, Integer> elementPoints) {
        int totalPoints = calculateTotalPoints(elementPoints);

        // Check if at least 50% points in one element guarantees that element
        for (Map.Entry<String, Integer> entry : elementPoints.entrySet()) {
            if ((double) entry.getValue() / totalPoints >= 0.5) {
                return entry.getKey();
            }
        }

        // Check if two elements have the exact same amount of points and together are over 50% of total points
        if (hasSamePoints(elementPoints.values())) {
            return "Combined Element";
        }

        // Calculate the element with the highest and second highest points
        String maxElement = getMaxElement(elementPoints);
        String secondMaxElement = getSecondMaxElement(elementPoints);

        // Check if the highest element point count is higher than the second highest
        double chance = calculateChance(elementPoints.get(maxElement), elementPoints.get(secondMaxElement), totalPoints);
        return String.format("%s (%.2f%% chance)", maxElement, chance);
    }

    private static int calculateTotalPoints(Map<String, Integer> elementPoints) {
        return elementPoints.values().stream().mapToInt(Integer::intValue).sum();
    }

    private static boolean hasSamePoints(Collection<Integer> points) {
        Set<Integer> uniquePoints = new HashSet<>(points);
        return uniquePoints.size() < points.size();
    }

    private static String getMaxElement(Map<String, Integer> elementPoints) {
        return elementPoints.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(RuntimeException::new); // Handle the case where the map is empty
    }

    private static String getSecondMaxElement(Map<String, Integer> elementPoints) {
        String maxElement = getMaxElement(elementPoints);
        return elementPoints.entrySet().stream()
                .filter(entry -> !entry.getKey().equals(maxElement))
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(RuntimeException::new); // Handle the case where the map is empty
    }

    private static double calculateChance(int highestPoints, int secondHighestPoints, int totalPoints) {
        // Replace this formula with your desired formula for calculating the percentage chance
        return ((double) highestPoints / totalPoints) * 100;
    }



}
