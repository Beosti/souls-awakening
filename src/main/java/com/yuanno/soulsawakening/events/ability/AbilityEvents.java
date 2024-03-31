package com.yuanno.soulsawakening.events.ability;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.*;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.events.ability.api.AbilityUseEvent;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class AbilityEvents {

    /**
     * Called before the actual thing that should happen happens, mostly just checks if it's continuous or not
     * @param event that happens before the actual thing around the ability/spell happens
     */
    @SubscribeEvent
    public static void beforeAbilityUse(AbilityUseEvent.Pre event)
    {
        Ability ability = event.getAbility();
        PlayerEntity player = event.getPlayer();
        IAbilityData abilityData = AbilityDataCapability.get(player);
        if (ability instanceof IContinuousAbility)
        {
            if (ability.getState().equals(Ability.STATE.CONTINUOUS)) {
                ((IContinuousAbility) ability).endContinuity(player, ability);
            }
            if (ability.getState().equals(Ability.STATE.READY)) {
                ((IContinuousAbility) ability).startContinuity(player, ability);
                ability.setState(Ability.STATE.CONTINUOUS);
                PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
                return;
            }
        }
    }

    /**
     * All the different ways to cast a spell/ability (incantation, right click, scroll wheel, whatever there is to come) is all funneled here to handle all the logic
     * @param event that happens when an ability actually *does* something, starting a continuous ability or shooting smth etc.
     */
    @SubscribeEvent
    public static void onAbilityUse(AbilityUseEvent.Per event)
    {
        Ability ability = event.getAbility();
        PlayerEntity player = event.getPlayer();
        if (ability instanceof IContinuousAbility && !ability.getState().equals(Ability.STATE.CONTINUOUS))
            return;
        if ((ability instanceof IDimensionTeleportAbility))
            ((IDimensionTeleportAbility) ability).teleport(player);
        if (ability instanceof IEntityRayTrace && ((IEntityRayTrace) ability).gotTarget(player))
            ((IEntityRayTrace) ability).onEntityRayTrace(player, ability);
        if (ability instanceof IShootAbility)
            ((IShootAbility) ability).onUse(player, ability);
        if (ability instanceof IWaveAbility)
            ((IWaveAbility) ability).onWave(player, ability);
        if (ability instanceof IBlockRayTrace)
            ((IBlockRayTrace) ability).onBlockRayTrace(player, ability);
        if (ability instanceof IParticleEffect)
            ((IParticleEffect) ability).spawnParticles(player);
        if (ability instanceof ISelfEffect)
            ((ISelfEffect) ability).applyEffect(player, ability);
        if (ability instanceof IAttackAbility)
        {
            LivingEntity target = event.getTarget();
            ((IAttackAbility) ability).activateBack(player, target, ability);
        }
    }

    /**
     * All the end of abilities that there are (incantation, keybind etc.) are funneled to here to handle the basic logic
     * @param event happens when the ability that had to do something did the thing AFTER you shoot something or AFTER the continuous ability is ended
     */
    @SubscribeEvent
    public static void onAbilityUsed(AbilityUseEvent.Post event)
    {
        Ability ability = event.getAbility();
        PlayerEntity player = event.getPlayer();
        IAbilityData abilityData = AbilityDataCapability.get(player);
        ability.setState(Ability.STATE.COOLDOWN);
        ability.setCooldown(ability.getMaxCooldown() / 20);
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
    }
}
