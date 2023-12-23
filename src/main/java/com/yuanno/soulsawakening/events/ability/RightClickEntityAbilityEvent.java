package com.yuanno.soulsawakening.events.ability;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IRightClickEmptyAbility;
import com.yuanno.soulsawakening.ability.api.IRightClickEntityAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.events.ability.RightClickEmptyEvent;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModResources;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CChangeZanpakutoStatePacket;
import com.yuanno.soulsawakening.networking.client.CRightClickEmptyPacket;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class RightClickEntityAbilityEvent {
    private static boolean entityInteractFired = false;
    @SubscribeEvent
    public static void onRightClickEntity(PlayerInteractEvent.RightClickItem.EntityInteract event)
    {
        PlayerEntity player = event.getPlayer();
        if (!(event.getTarget() instanceof  LivingEntity))
            return;
        if (player.level.isClientSide)
            return;
        LivingEntity target = (LivingEntity) event.getTarget();
        IEntityStats entityStats = EntityStatsCapability.get(player);
        IAbilityData abilityData = AbilityDataCapability.get(player);

        // do something when the player is a shinigami and has shikai in hand
        if (entityStats.getRace().equals(ModValues.SHINIGAMI) || entityStats.getRace().equals(ModValues.FULLBRINGER) && player.getMainHandItem().getItem().equals(ModItems.ZANPAKUTO.get()))
        {
            ZanpakutoItem zanpakutoItem = (ZanpakutoItem) player.getMainHandItem().getItem();
            if (zanpakutoItem.getZanpakutoState().equals(ModResources.STATE.SHIKAI)) // do stuff while in shikai state and right click
            {
                for (int i = 0; i < abilityData.getActiveAbilities().size(); i++)
                {
                    Ability ability = abilityData.getActiveAbilities().get(i);
                    if (!(ability instanceof IRightClickEntityAbility))
                        continue;
                    if (!ability.getZanpakutoState().equals(ModResources.STATE.SHIKAI))
                        continue;
                    if (!ability.getState().equals(Ability.STATE.READY))
                        continue;


                    ((IRightClickEntityAbility) ability).onRightClickEntity(target, player);
                    ability.setState(Ability.STATE.COOLDOWN);
                    ability.setCooldown(ability.getMaxCooldown() / 20);
                    entityInteractFired = true;
                    return;
                }
            }
            else if (zanpakutoItem.getZanpakutoState().equals(ModResources.STATE.BANKAI)) // do stuff while in bankai state and right click
            {
                for (Ability ability : abilityData.getActiveAbilities())
                {
                    if (!(ability instanceof IRightClickEntityAbility) || !ability.getZanpakutoState().equals(ModResources.STATE.BANKAI) || !ability.getState().equals(Ability.STATE.READY))
                        return;
                    ((IRightClickEntityAbility) ability).onRightClickEntity(target, player);
                    ability.setState(Ability.STATE.COOLDOWN);
                    ability.setCooldown(ability.getMaxCooldown() / 20);
                }
            }
        }
        // do something when the player is a hollow
        else if (entityStats.getRace().equals(ModValues.HOLLOW))
        {
            for (Ability ability : abilityData.getActiveAbilities())
            {
                if (!(ability instanceof IRightClickEntityAbility))
                    continue;
                if (!ability.getState().equals(Ability.STATE.READY) && !(ability.getPassive()))
                    continue;
                ((IRightClickEntityAbility) ability).onRightClickEntity(target, player);
                ability.setState(Ability.STATE.COOLDOWN);
                ability.setCooldown(ability.getMaxCooldown() / 20);
                entityInteractFired = true;
                return;
            }
        }
    }

    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event)
    {
        PlayerEntity player = event.getPlayer();
        if (player.level.isClientSide)
            return;

        if (entityInteractFired)
        {
            entityInteractFired = false;
            return;
        }
        IEntityStats entityStats = EntityStatsCapability.get(player);
        IAbilityData abilityData = AbilityDataCapability.get(player);
        //if (event instanceof PlayerInteractEvent.RightClickItem.EntityInteract)
       // {
            //
       // }
        // do something when the player is a shinigami and has shikai in hand

        if ((entityStats.getRace().equals(ModValues.SHINIGAMI) || entityStats.getRace().equals(ModValues.FULLBRINGER)) && player.getMainHandItem().getItem().equals(ModItems.ZANPAKUTO.get()))
        {
            ZanpakutoItem zanpakutoItem = (ZanpakutoItem) player.getMainHandItem().getItem();
            if (zanpakutoItem.getZanpakutoState().equals(ModResources.STATE.SHIKAI)) // do stuff while in shikai state and right click
            {
                for (int i = 0; i < abilityData.getActiveAbilities().size(); i++)
                {
                    Ability ability = abilityData.getActiveAbilities().get(i);
                    if (!ability.getZanpakutoState().equals(ModResources.STATE.SHIKAI))
                        continue;
                    if (!ability.getState().equals(Ability.STATE.READY))
                        continue;
                    if (!(ability instanceof IRightClickEmptyAbility))
                        continue;
                    Ability.ActivationType activationType = ability.getActivationType();
                    if (activationType.equals(Ability.ActivationType.SHIFT_RIGHT_CLICK) && player.isCrouching())
                        ((IRightClickEmptyAbility) ability).onShiftRightClick(player);
                    else if (activationType.equals(Ability.ActivationType.RIGHT_CLICK_EMPTY) && !player.isCrouching())
                        ((IRightClickEmptyAbility) ability).onRightClick(player);
                    else
                        continue;



                    ability.setState(Ability.STATE.COOLDOWN);
                    ability.setCooldown(ability.getMaxCooldown() / 20);
                    return;
                }
            }
            else if (zanpakutoItem.getZanpakutoState().equals(ModResources.STATE.BANKAI)) // do stuff while in bankai state and right click
            {
                for (Ability ability : abilityData.getActiveAbilities())
                {
                    if (!ability.getActivationType().equals(Ability.ActivationType.RIGHT_CLICK_EMPTY) || !ability.getZanpakutoState().equals(ModResources.STATE.BANKAI) || !ability.getState().equals(Ability.STATE.READY))
                        return;
                    ((IRightClickEmptyAbility) ability).onRightClick(player);
                    ability.setState(Ability.STATE.COOLDOWN);
                    ability.setCooldown(ability.getMaxCooldown() / 20);
                }
            }
        }
        /*
        else if (entityStats.getRace().equals(ModValues.HOLLOW))
        {
            for (Ability ability : abilityData.getActiveAbilities())
            {
                if (!(ability instanceof IRightClickEmptyAbility))
                    continue;
                if (!ability.getState().equals(Ability.STATE.READY) && !(ability.getPassive()))
                    continue;
                ((IRightClickEmptyAbility) ability).onRightClick(player);
                ability.setState(Ability.STATE.COOLDOWN);
                ability.setCooldown(ability.getMaxCooldown() / 20);

            }
        }

         */
    }

    /**
     * This event does actually nothing except throw this right click empty event through a packet so it can be used in a custom event used right after
     * @param event
     */
    @SubscribeEvent
    public static void onRightClickEmpty(PlayerInteractEvent.RightClickEmpty event)
    {
        PacketHandler.sendToServer(new CRightClickEmptyPacket());
    }

    /**
     * This is actually the event from above copy-pasted except that this server sided (which is needed for what it should do)
     * It just passes a short side through a packet to make it work
     * @param event
     */
    @SubscribeEvent
    public static void onEmptyRightClick(RightClickEmptyEvent event)
    {
        PlayerEntity player = event.getPlayer();
        if (player.level.isClientSide)
            return;
        if (entityInteractFired)
        {
            entityInteractFired = false;
            return;
        }
        IEntityStats entityStats = EntityStatsCapability.get(player);
        IAbilityData abilityData = AbilityDataCapability.get(player);
        if (entityStats.getRace().equals(ModValues.HOLLOW))
        {
            for (Ability ability : abilityData.getActiveAbilities())
            {
                if (!(ability instanceof IRightClickEmptyAbility))
                    continue;
                if (!ability.getState().equals(Ability.STATE.READY) && !(ability.getPassive()))
                    continue;
                if (ability.getActivationType().equals(Ability.ActivationType.SHIFT_RIGHT_CLICK) && player.isCrouching()) {
                    ability.setState(Ability.STATE.COOLDOWN);
                    ability.setCooldown(ability.getMaxCooldown() / 20);
                    ((IRightClickEmptyAbility) ability).onShiftRightClick(player);
                }
                else if (ability.getActivationType().equals(Ability.ActivationType.RIGHT_CLICK_EMPTY) && !player.isCrouching()) {
                    ability.setState(Ability.STATE.COOLDOWN);
                    ability.setCooldown(ability.getMaxCooldown() / 20);
                    ((IRightClickEmptyAbility) ability).onRightClick(player);
                }
                else
                    continue;

                return;
            }
        }
    }
}
