package com.yuanno.soulsawakening.events;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
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
        if (player.level.isClientSide)
            return;

        IAbilityData abilityData = AbilityDataCapability.get(player);
        IEntityStats entityStats = EntityStatsCapability.get(player);

        for (Ability ability : abilityData.getUnlockedAbilities())
        {
            if (ability.getPassive() || ability.getState().equals(Ability.STATE.READY))
                return;
            System.out.println(ability.getState());
            if (ability.getCooldown() <= 0)
                ability.setState(Ability.STATE.READY);
            if (ability.getState().equals(Ability.STATE.COOLDOWN))
                ability.setCooldown(ability.getCooldown() - 1);


        }
    }
}
