package com.yuanno.soulsawakening.events.ability;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IContinuousAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IReleaseArrow;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.events.ability.api.AbilityUseEvent;
import com.yuanno.soulsawakening.events.util.CustomArrowLooseEvent;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
            if (!abilityData.getUnlockedAbilities().get(i).getState().equals(Ability.STATE.READY) && !(abilityData instanceof IContinuousAbility && !abilityData.getUnlockedAbilities().get(i).getState().equals(Ability.STATE.CONTINUOUS)))
                continue;
            IReleaseArrow releaseArrow = (IReleaseArrow) abilityData.getUnlockedAbilities().get(i);
            releaseArrow.onLooseArrow(player, event.getProjectile(), event.getPower());
            if (abilityData.getUnlockedAbilities().get(i) instanceof IContinuousAbility && ((IContinuousAbility) abilityData.getUnlockedAbilities().get(i)).getEndAfterUse());
            {
                AbilityUseEvent.Post abilityUseEvent = new AbilityUseEvent.Post(player, abilityData.getUnlockedAbilities().get(i));
                MinecraftForge.EVENT_BUS.post(abilityUseEvent);
            }
        }

    }
}
