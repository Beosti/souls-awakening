package com.yuanno.soulsawakening.events.ability;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.IContinuousAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IDuringCooldownAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IPassiveAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.events.ability.api.AbilityUseEvent;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class TickAbilityEvent {
    private static float continueTime;
    @SubscribeEvent
    public static void onTickEventAbility(LivingEvent.LivingUpdateEvent event)
    {
        if (!(event.getEntityLiving() instanceof PlayerEntity))
            return;
        PlayerEntity player = (PlayerEntity) event.getEntityLiving();
        if (player.level.isClientSide)
            return;
        IAbilityData abilityData = AbilityDataCapability.get(player);
        for (Ability ability : abilityData.getUnlockedAbilities())
        {
            if (ability.getState().equals(Ability.STATE.COOLDOWN)) {
                ability.duringCooldown(player);
                continue;
            }
            if (ability instanceof IContinuousAbility)
            {
                if (((IContinuousAbility) ability).getPassive() && ability.getState().equals(Ability.STATE.READY) && ability.getDependency().check(player))
                {
                    AbilityUseEvent.Pre abilityUseEventPre = new AbilityUseEvent.Pre(player, ability);
                    MinecraftForge.EVENT_BUS.post(abilityUseEventPre);
                }
                if (ability.getState().equals(Ability.STATE.CONTINUOUS)) {
                    if (ability.getDependency().check(player))
                    {
                        if (!(((IContinuousAbility) ability).getMaxTimer() == -1))
                            ability.alterTimer(1);
                        if (ability.getTimer() >= ((IContinuousAbility) ability).getMaxTimer())
                        {
                            ability.setTimer(0);
                            ((IContinuousAbility) ability).endContinuity(player, ability);
                        }
                        else
                            ((IContinuousAbility) ability).duringContinuity(player);
                        // code
                    }
                    else
                    {
                        ability.setState(Ability.STATE.READY);
                        if (!(((IContinuousAbility) ability).getMaxTimer() == -1))
                            ability.setTimer(0);
                        if (!((IContinuousAbility) ability).getPassive())
                        {
                            ((IContinuousAbility) ability).endContinuity(player);
                        }
                    }

                }
                PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
            }
            if (!(ability instanceof IPassiveAbility))
            {
                if (ability.getState().equals(Ability.STATE.READY))
                    continue;
                if (ability instanceof IDuringCooldownAbility)
                    ((IDuringCooldownAbility) ability).onCooldown(player);
                ability.duringCooldown(player);
            }
            else if (ability instanceof IPassiveAbility)
            {
                if (player.tickCount % 20 == 0 && !player.level.isClientSide)
                    ((IPassiveAbility) ability).onContinuousAbility(player);
            }
        }
    }
}
