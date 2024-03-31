package com.yuanno.soulsawakening.events;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.abilities.kido.bakudo.SaiAbility;
import com.yuanno.soulsawakening.abilities.kido.hado.ByakuraiAbility;
import com.yuanno.soulsawakening.abilities.kido.hado.ShakkahoAbility;
import com.yuanno.soulsawakening.abilities.kido.hado.ShoAbility;
import com.yuanno.soulsawakening.abilities.kido.hado.TsuzuriRaidenAbility;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.KidoAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.*;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.events.ability.AbilityUseEvent;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = Main.MODID)
public class TestEvent {

    @SubscribeEvent
    public static void chatEventServer(ServerChatEvent event)
    {
        if (event.getMessage().equals("sho"))
        {
            PlayerEntity player = event.getPlayer();
            IAbilityData abilityData = AbilityDataCapability.get(player);
            abilityData.addUnlockedAbility(ShoAbility.INSTANCE);
            abilityData.addUnlockedAbility(TsuzuriRaidenAbility.INSTANCE);
            /*
            abilityData.addAbilityToBar(ShoAbility.INSTANCE);
            abilityData.addUnlockedAbility(ByakuraiAbility.INSTANCE);
            abilityData.addAbilityToBar(ByakuraiAbility.INSTANCE);
            abilityData.addUnlockedAbility(ShakkahoAbility.INSTANCE);
            abilityData.addAbilityToBar(ShakkahoAbility.INSTANCE);
            abilityData.addUnlockedAbility(SaiAbility.INSTANCE);
            abilityData.addAbilityToBar(SaiAbility.INSTANCE);
            abilityData.setSelectedAbility(0);

             */
            PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        }

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
            if (!ability.getState().equals(Ability.STATE.READY))
                return;
            AbilityUseEvent.Per abilityUseEvent = new AbilityUseEvent.Per(player, ability);
            MinecraftForge.EVENT_BUS.post(abilityUseEvent);
            AbilityUseEvent.Post abilityUsedEvent = new AbilityUseEvent.Post(player, ability);
            MinecraftForge.EVENT_BUS.post(abilityUsedEvent);
            return;
        }
    }
}
