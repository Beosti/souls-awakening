package com.yuanno.soulsawakening.events.ability;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.IContinuousAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IReleaseArrow;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.events.ability.api.AbilityUseEvent;
import com.yuanno.soulsawakening.events.util.CustomArrowLooseEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Checks abilities that have {@link IReleaseArrow} and if they're {@link IContinuousAbility} and funnels it properly to {@link AbilityUseEvent}
 * Mostly quincies that only use this but can always be handy for zanpakuto, bankai etc.
 */
@Mod.EventBusSubscriber(modid = Main.MODID)
public class AbilityArrowLooseEvents {

    @SubscribeEvent
    public static void onAbilityArrowLoose(CustomArrowLooseEvent event)
    {
        PlayerEntity player = event.getPlayer();
        if (player.level.isClientSide)
            return;
        IAbilityData abilityData = AbilityDataCapability.get(player);
        for (int i = 0; i < abilityData.getUnlockedAbilities().size(); i++)
        {
            if (!(abilityData.getUnlockedAbilities().get(i) instanceof IReleaseArrow))
                continue;
            if (abilityData.getUnlockedAbilities().get(i) instanceof IContinuousAbility && !abilityData.getUnlockedAbilities().get(i).getState().equals(Ability.STATE.CONTINUOUS))
                continue;
            if (!(abilityData.getUnlockedAbilities().get(i) instanceof IContinuousAbility) && !abilityData.getUnlockedAbilities().get(i).getState().equals(Ability.STATE.READY))
                continue;
            AbilityUseEvent.Per abilityUseEvent = new AbilityUseEvent.Per(player, abilityData.getUnlockedAbilities().get(i), event.getProjectile(), event.getPower());
            MinecraftForge.EVENT_BUS.post(abilityUseEvent);
            if (abilityData.getUnlockedAbilities().get(i) instanceof IContinuousAbility && ((IContinuousAbility) abilityData.getUnlockedAbilities().get(i)).getEndAfterUse()) {
                AbilityUseEvent.Post abilityUseEventPost = new AbilityUseEvent.Post(player, abilityData.getUnlockedAbilities().get(i));
                MinecraftForge.EVENT_BUS.post(abilityUseEventPost);
            }
        }
    }
}
