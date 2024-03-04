package com.yuanno.soulsawakening.events;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IPassiveAbility;
import com.yuanno.soulsawakening.ability.api.IDuringCooldownAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class TickAbilityEvent {

    @SubscribeEvent
    public static void onTickEventAbility(LivingEvent.LivingUpdateEvent event)
    {
        if (!(event.getEntityLiving() instanceof PlayerEntity))
            return;
        PlayerEntity player = (PlayerEntity) event.getEntityLiving();


        IAbilityData abilityData = AbilityDataCapability.get(player);
        for (Ability ability : abilityData.getUnlockedAbilities())
        {
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
