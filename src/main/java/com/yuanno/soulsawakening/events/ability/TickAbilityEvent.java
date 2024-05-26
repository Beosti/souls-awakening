package com.yuanno.soulsawakening.events.ability;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IContinuousAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IPassiveAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IDuringCooldownAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import net.minecraft.entity.player.PlayerEntity;
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
            if (ability instanceof IContinuousAbility)
            {
                if (ability.getState().equals(Ability.STATE.CONTINUOUS)) {
                    if (ability.getDependency().check(player) && (((IContinuousAbility) ability).getMaxTimer() == -1 || ability.getTimer() < ((IContinuousAbility) ability).getMaxTimer())) {
                        ability.alterTimer((int) 1);
                        ((IContinuousAbility) ability).duringContinuity(player, ability);
                    }
                    else {
                        ability.setTimer(0);
                        ((IContinuousAbility) ability).endContinuity(player, ability);
                    }
                }
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
