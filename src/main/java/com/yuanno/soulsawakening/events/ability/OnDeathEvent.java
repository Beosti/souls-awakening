package com.yuanno.soulsawakening.events.ability;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.IContinuousAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IGetHitAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IOnDeath;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.events.ability.api.AbilityUseEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class OnDeathEvent {

    @SubscribeEvent
    public static void onDeath(LivingDamageEvent event)
    {
        if (!(event.getEntityLiving() instanceof PlayerEntity))
            return;
        PlayerEntity player = (PlayerEntity) event.getEntityLiving();
        IAbilityData abilityData = AbilityDataCapability.get(player);
        for (int i = 0; i < abilityData.getUnlockedAbilities().size(); i++)
        {
            Ability ability = abilityData.getUnlockedAbilities().get(i);
            if (!(ability instanceof IOnDeath))
                continue;
            if (ability.getState().equals(Ability.STATE.COOLDOWN) || (ability instanceof IContinuousAbility && !ability.getState().equals(Ability.STATE.CONTINUOUS)))
                continue;
            if (!ability.getDependency().check(player))
                continue;
            if (event.getAmount() > event.getEntityLiving().getHealth())
            {
                AbilityUseEvent.Per abilityUseEvent = new AbilityUseEvent.Per(player, ability);
                MinecraftForge.EVENT_BUS.post(abilityUseEvent);
                event.setCanceled(((IOnDeath) ability).getCancelDeath());
            }


        }
    }
}
