package com.yuanno.soulsawakening.events.ability;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.KidoAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.*;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.events.ability.api.AbilityUseEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = Main.MODID)
public class IncantationAbilityEvent {

    @SubscribeEvent
    public static void incantationAbilityEvent(ServerChatEvent event)
    {
        PlayerEntity player = event.getPlayer();
        IAbilityData abilityData = AbilityDataCapability.get(player);
        for (int i = 0; i < abilityData.getUnlockedAbilities().size(); i++)
        {
            if (!(abilityData.getUnlockedAbilities().get(i) instanceof KidoAbility))
                continue;
            KidoAbility ability = (KidoAbility) abilityData.getUnlockedAbilities().get(i);
            if (!event.getMessage().equals(ability.getIncantation()))
                continue;
            if ((ability instanceof IEntityRayTrace && !(((IEntityRayTrace) ability).gotTarget(player))))
                continue;
            AbilityUseEvent.Pre abilityUseEventPre = new AbilityUseEvent.Pre(player, ability);
            MinecraftForge.EVENT_BUS.post(abilityUseEventPre);
            if (!ability.getState().equals(Ability.STATE.READY)) {
                player.sendMessage(new TranslationTextComponent("This spell is on cooldown!"), Util.NIL_UUID);
                return;
            };
            AbilityUseEvent.Per abilityUseEvent = new AbilityUseEvent.Per(player, ability);
            MinecraftForge.EVENT_BUS.post(abilityUseEvent);
            AbilityUseEvent.Post abilityUsedEvent = new AbilityUseEvent.Post(player, ability);
            MinecraftForge.EVENT_BUS.post(abilityUsedEvent);
            return;
        }
    }
}
