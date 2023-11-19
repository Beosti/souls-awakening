package com.yuanno.soulsawakening.events;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModResources;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class RightClickEntityAbilityEvent {

    @SubscribeEvent
    public static void onRightClickEntity(PlayerInteractEvent.EntityInteract event)
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
                    System.out.println(ability);
                    if (!ability.getActivationType().equals(Ability.ActivationType.RIGHT_CLICK_ENTITY))
                        continue;
                    if (!ability.getZanpakutoState().equals(ModResources.STATE.SHIKAI))
                        continue;
                    if (!ability.getState().equals(Ability.STATE.READY))
                        continue;
                    ability.onRightClickEntity(target, player);
                    ability.setState(Ability.STATE.COOLDOWN);
                    ability.setCooldown(ability.getMaxCooldown() / 20);
                }
            }
            else if (zanpakutoItem.getZanpakutoState().equals(ModResources.STATE.BANKAI)) // do stuff while in bankai state and right click
            {
                for (Ability ability : abilityData.getActiveAbilities())
                {
                    if (!ability.getActivationType().equals(Ability.ActivationType.RIGHT_CLICK_ENTITY) || !ability.getZanpakutoState().equals(ModResources.STATE.BANKAI) || !ability.getState().equals(Ability.STATE.READY))
                        return;
                    ability.onRightClickEntity(target, player);
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
                if (!ability.getActivationType().equals(Ability.ActivationType.RIGHT_CLICK_ENTITY) || !ability.getState().equals(Ability.STATE.READY))
                    return;
                ability.onRightClickEntity(target, player);
                ability.setState(Ability.STATE.COOLDOWN);
                ability.setCooldown(ability.getMaxCooldown() / 20);

            }
        }
    }

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickItem event)
    {
        PlayerEntity player = event.getPlayer();

        if (player.level.isClientSide)
            return;

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
                    if (!ability.getActivationType().equals(Ability.ActivationType.RIGHT_CLICK_EMPTY) && !ability.getActivationType().equals(Ability.ActivationType.SHIFT_RIGHT_CLICK))
                        continue;
                    if (!ability.getZanpakutoState().equals(ModResources.STATE.SHIKAI))
                        continue;
                    if (!ability.getState().equals(Ability.STATE.READY))
                        continue;
                    if (ability.getActivationType().equals(Ability.ActivationType.SHIFT_RIGHT_CLICK) && !player.isCrouching())
                        continue;
                    if (ability.getActivationType().equals(Ability.ActivationType.RIGHT_CLICK_EMPTY) && player.isCrouching())
                        continue;
                    ability.onRightClick(player);
                    ability.setState(Ability.STATE.COOLDOWN);
                    ability.setCooldown(ability.getMaxCooldown() / 20);
                }
            }
            else if (zanpakutoItem.getZanpakutoState().equals(ModResources.STATE.BANKAI)) // do stuff while in bankai state and right click
            {
                for (Ability ability : abilityData.getActiveAbilities())
                {
                    if (!ability.getActivationType().equals(Ability.ActivationType.RIGHT_CLICK_EMPTY) || !ability.getZanpakutoState().equals(ModResources.STATE.BANKAI) || !ability.getState().equals(Ability.STATE.READY))
                        return;
                    ability.onRightClick(player);
                    ability.setState(Ability.STATE.COOLDOWN);
                    ability.setCooldown(ability.getMaxCooldown() / 20);
                }
            }
        }
    }
}
