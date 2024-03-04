package com.yuanno.soulsawakening.events.ability;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.*;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CRightClickEmptyPacket;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class RightClickEntityAbilityEvent {


    /*
    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event)
    {
        PlayerEntity player = event.getPlayer();
        if (player.level.isClientSide)
            return;
        IAbilityData abilityData = AbilityDataCapability.get(player);
        ArrayList <Ability> unlockedAbilities = (ArrayList<Ability>) abilityData.getUnlockedAbilities();
        unlockedAbilities.sort((ability1, ability2) -> {
            if (ability1 instanceof IEntityRayTrace && ability2 instanceof IEntityRayTrace) {
                return 0;
            } else if (ability1 instanceof IEntityRayTrace) {
                return -1;
            } else if (ability2 instanceof IEntityRayTrace) {
                return 1;
            } else {
                return 0;
            }
        });
        for (int i = 0; i < unlockedAbilities.size(); i++)
        {
            Ability ability = unlockedAbilities.get(i);
            if (!(ability.getState().equals(Ability.STATE.READY))) // check if the ability is read
                continue;
            if (!(ability instanceof IRightClickAbility)) // check if the ability is a right click ability
                continue;
            if (ability.getSubCategory() != null && ability.getSubCategory().equals(Ability.SubCategory.SHIKAI)) // check if the ability is shikai needing
            {
                ItemStack zanpakutoItem = player.getMainHandItem();
                String state = zanpakutoItem.getTag().getString("zanpakutoState");
                if (!state.equals(ModValues.STATE.SHIKAI.name())) // if your item is in shikai state you can use it
                    return;
            }
            IRightClickAbility rightClickEmptyAbility = (IRightClickAbility) abilityData.getUnlockedAbilities().get(i);
            if (player.isCrouching() ^ rightClickEmptyAbility.getShift())
                continue;
            if ((ability instanceof IEntityRayTrace && !(((IEntityRayTrace) ability).gotTarget(player))))
                continue;
            if (ability instanceof IEntityRayTrace && ((IEntityRayTrace) ability).gotTarget(player))
                ((IEntityRayTrace) ability).onEntityRayTrace(player);
            if (ability instanceof IShootAbility)
                ((IShootAbility) ability).onUse(player);
            if (ability instanceof IWaveAbility)
                ((IWaveAbility) ability).onWave(player);
            if (ability instanceof IBlockRayTrace)
                ((IBlockRayTrace) ability).onBlockRayTrace(player);
            if (ability instanceof IParticleEffect)
                ((IParticleEffect) ability).spawnParticles(player);
            if (ability instanceof ISelfEffect)
                ((ISelfEffect) ability).applyEffect(player);
            ability.setState(Ability.STATE.COOLDOWN);
            ability.setCooldown(ability.getMaxCooldown() / 20);
            PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
            return;
        }
    }

     */

    /**
     * This event does actually nothing except throw this right click empty event through a packet so it can be used in a custom event used right after
     * @param event
     */
    @SubscribeEvent
    public static void onRightClickEmpty(PlayerInteractEvent.RightClickEmpty event)
    {
        PacketHandler.sendToServer(new CRightClickEmptyPacket());
        //System.out.println("INTERACTING NOTHING");
    }

    @SubscribeEvent
    public static void onTestInteractAnother(PlayerInteractEvent.RightClickItem event)
    {
        PlayerEntity player = event.getPlayer();
        if (player.level.isClientSide)
            return;
        Event customInteractionEvent = new CustomInteractionEvent(player);
        MinecraftForge.EVENT_BUS.post(customInteractionEvent);
        return;
    }

    @SubscribeEvent
    public static void anotherTestInteraction(CustomInteractionEvent event)
    {
        PlayerEntity player = event.getPlayer();
        if (player.level.isClientSide)
            return;
        IAbilityData abilityData = AbilityDataCapability.get(player);
        ArrayList <Ability> unlockedAbilities = (ArrayList<Ability>) abilityData.getUnlockedAbilities();
        unlockedAbilities.sort((ability1, ability2) -> {
            if (ability1 instanceof IEntityRayTrace && ability2 instanceof IEntityRayTrace) {
                return 0;
            } else if (ability1 instanceof IEntityRayTrace) {
                return -1;
            } else if (ability2 instanceof IEntityRayTrace) {
                return 1;
            } else {
                return 0;
            }
        });
        for (int i = 0; i < unlockedAbilities.size(); i++)
        {
            Ability ability = unlockedAbilities.get(i);
            if (!(ability.getState().equals(Ability.STATE.READY))) // check if the ability is read
                continue;
            if (!(ability instanceof IRightClickAbility)) // check if the ability is a right click ability
                continue;
            if (ability.getSubCategory() != null && ability.getSubCategory().equals(Ability.SubCategory.SHIKAI)) // check if the ability is shikai needing
            {
                ItemStack zanpakutoItem = player.getMainHandItem();
                if (!zanpakutoItem.getItem().equals(ModItems.ZANPAKUTO.get().getItem()))
                    return;
                String state = zanpakutoItem.getTag().getString("zanpakutoState");
                if (!state.equals(ModValues.STATE.SHIKAI.name())) // if your item is in shikai state you can use it
                    return;
            }
            IRightClickAbility rightClickEmptyAbility = (IRightClickAbility) abilityData.getUnlockedAbilities().get(i);
            if (player.isCrouching() ^ rightClickEmptyAbility.getShift())
                continue;
            if ((ability instanceof IEntityRayTrace && !(((IEntityRayTrace) ability).gotTarget(player))))
                continue;
            if (ability instanceof IEntityRayTrace && ((IEntityRayTrace) ability).gotTarget(player))
                ((IEntityRayTrace) ability).onEntityRayTrace(player);
            if (ability instanceof IShootAbility)
                ((IShootAbility) ability).onUse(player);
            if (ability instanceof IWaveAbility)
                ((IWaveAbility) ability).onWave(player);
            if (ability instanceof IBlockRayTrace)
                ((IBlockRayTrace) ability).onBlockRayTrace(player);
            if (ability instanceof IParticleEffect)
                ((IParticleEffect) ability).spawnParticles(player);
            if (ability instanceof ISelfEffect)
                ((ISelfEffect) ability).applyEffect(player);
            ability.setState(Ability.STATE.COOLDOWN);
            ability.setCooldown(ability.getMaxCooldown() / 20);
            PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
            return;
        }
    }

    /**
     * This is actually the event from above copy-pasted except that this server sided (which is needed for what it should do)
     * It just passes a short side through a packet to make it work
     * @param event
     */
    /*
    @SubscribeEvent
    public static void onEmptyRightClick(RightClickEmptyEvent event)
    {
        PlayerEntity player = event.getPlayer();
        if (player.level.isClientSide)
            return;
        IAbilityData abilityData = AbilityDataCapability.get(player);
        ArrayList <Ability> unlockedAbilities = (ArrayList<Ability>) abilityData.getUnlockedAbilities();
        unlockedAbilities.sort((ability1, ability2) -> {
            if (ability1 instanceof IEntityRayTrace && ability2 instanceof IEntityRayTrace) {
                return 0;
            } else if (ability1 instanceof IEntityRayTrace) {
                return -1;
            } else if (ability2 instanceof IEntityRayTrace) {
                return 1;
            } else {
                return 0;
            }
        });
        for (int i = 0; i < unlockedAbilities.size(); i++)
        {
            Ability ability = unlockedAbilities.get(i);
            if (!(ability.getState().equals(Ability.STATE.READY))) // check if the ability is read
                continue;
            if (!(ability instanceof IRightClickAbility)) // check if the ability is a right click ability
                continue;
            if (ability.getSubCategory() != null && ability.getSubCategory().equals(Ability.SubCategory.SHIKAI)) // check if the ability is shikai needing
                continue;
            IRightClickAbility rightClickEmptyAbility = (IRightClickAbility) abilityData.getUnlockedAbilities().get(i);
            if (player.isCrouching() ^ rightClickEmptyAbility.getShift())
                continue;
            // TODO some very weird bug involving the bite ability
            if ((ability instanceof IEntityRayTrace && !(((IEntityRayTrace) ability).gotTarget(player))))
                continue;
            if (ability instanceof IEntityRayTrace && ((IEntityRayTrace) ability).gotTarget(player))
                ((IEntityRayTrace) ability).onEntityRayTrace(player);
            if (ability instanceof IShootAbility)
                ((IShootAbility) ability).onUse(player);
            if (ability instanceof IWaveAbility)
                ((IWaveAbility) ability).onWave(player);
            if (ability instanceof IBlockRayTrace)
                ((IBlockRayTrace) ability).onBlockRayTrace(player);
            if (ability instanceof IParticleEffect)
                ((IParticleEffect) ability).spawnParticles(player);
            if (ability instanceof ISelfEffect)
                ((ISelfEffect) ability).applyEffect(player);
            ability.setState(Ability.STATE.COOLDOWN);
            ability.setCooldown(ability.getMaxCooldown() / 20);
            PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
            return;
        }
    }

     */
}
