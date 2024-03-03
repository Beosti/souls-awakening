package com.yuanno.soulsawakening.events.ability;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.*;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CRightClickEmptyPacket;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
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
        IAbilityData abilityData = AbilityDataCapability.get(player);
        for (int i = 0; i < abilityData.getUnlockedAbilities().size(); i++)
        {
            Ability ability = abilityData.getUnlockedAbilities().get(i);
            if (!(ability.getState().equals(Ability.STATE.READY))) // check if the ability is read
                return;
            if (!(ability instanceof IRightClickEntityAbility)) // check if the ability is an attack ability
                return;
            if (ability.getSubCategory() != null && ability.getSubCategory().equals(Ability.SubCategory.SHIKAI)) // check if the ability is shikai needing
            {
                ItemStack zanpakutoItem = player.getMainHandItem();
                String state = zanpakutoItem.getTag().getString("zanpakutoState");
                if (state.equals(ModValues.STATE.SHIKAI.name())) // if your item is in shikai state you can use it
                    continue;
                else // if not return
                    return;
            }
            IRightClickEntityAbility rightClickEntityAbility = (IRightClickEntityAbility) abilityData.getUnlockedAbilities().get(i);
            rightClickEntityAbility.onRightClickEntity(target, player);
            ability.setState(Ability.STATE.COOLDOWN);
            ability.setCooldown(ability.getMaxCooldown() / 20);

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
        IAbilityData abilityData = AbilityDataCapability.get(player);
        for (int i = 0; i < abilityData.getUnlockedAbilities().size(); i++)
        {
            Ability ability = abilityData.getUnlockedAbilities().get(i);
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
            System.out.println(ability.getName());
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
        for (int i = 0; i < abilityData.getUnlockedAbilities().size(); i++)
        {
            Ability ability = abilityData.getUnlockedAbilities().get(i);
            if (!(ability.getState().equals(Ability.STATE.READY))) // check if the ability is read
                return;
            if (!(ability instanceof IRightClickAbility)) // check if the ability is an attack ability
                return;
            IRightClickAbility rightClickEmptyAbility = (IRightClickAbility) abilityData.getUnlockedAbilities().get(i);
            //rightClickEmptyAbility.onRightClick(player);
            ability.setState(Ability.STATE.COOLDOWN);
            ability.setCooldown(ability.getMaxCooldown() / 20);
        }

        /*
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
         */
    }
}
