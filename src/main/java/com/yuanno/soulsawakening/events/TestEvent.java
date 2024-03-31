package com.yuanno.soulsawakening.events;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.abilities.kido.bakudo.SaiAbility;
import com.yuanno.soulsawakening.abilities.kido.hado.ByakuraiAbility;
import com.yuanno.soulsawakening.abilities.kido.hado.ShakkahoAbility;
import com.yuanno.soulsawakening.abilities.kido.hado.ShoAbility;
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
            if (event.getMessage().equals(ability.getIncantation()))
            {
                if ((ability instanceof IEntityRayTrace && !(((IEntityRayTrace) ability).gotTarget(player))))
                    continue;
                if ((ability instanceof IDimensionTeleportAbility))
                    ((IDimensionTeleportAbility) ability).teleport(player);
                if (ability instanceof IEntityRayTrace && ((IEntityRayTrace) ability).gotTarget(player))
                    ((IEntityRayTrace) ability).onEntityRayTrace(player, ability);
                if (ability instanceof IShootAbility)
                    ((IShootAbility) ability).onUse(player, ability);
                if (ability instanceof IWaveAbility)
                    ((IWaveAbility) ability).onWave(player, ability);
                if (ability instanceof IBlockRayTrace)
                    ((IBlockRayTrace) ability).onBlockRayTrace(player, ability);
                if (ability instanceof IParticleEffect)
                    ((IParticleEffect) ability).spawnParticles(player);
                if (ability instanceof ISelfEffect)
                    ((ISelfEffect) ability).applyEffect(player, ability);
                AbilityUseEvent abilityUseEvent = new AbilityUseEvent(player, ability);
                MinecraftForge.EVENT_BUS.post(abilityUseEvent);
                ability.setState(Ability.STATE.COOLDOWN);
                ability.setCooldown(ability.getMaxCooldown() / 20);
                PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
                return;
            }
        }
    }


}
