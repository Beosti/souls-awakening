package com.yuanno.soulsawakening.networking.client;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IContinuousAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IEntityRayTrace;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.events.ability.api.AbilityUseEvent;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CUseSpellPacket {

    private int slot;

    public CUseSpellPacket()
    {

    }

    public CUseSpellPacket(int slot)
    {
        this.slot = slot;
    }

    public void encode(PacketBuffer buffer) {
        buffer.writeInt(this.slot);
    }

    public static CUseSpellPacket decode(PacketBuffer buffer)
    {
        CUseSpellPacket msg = new CUseSpellPacket();
        msg.slot = buffer.readInt();
        return msg;
    }

    public static void handle(CUseSpellPacket message, final Supplier<NetworkEvent.Context> contextSupplier)
    {
        if (contextSupplier.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            contextSupplier.get().enqueueWork(() ->
            {
                PlayerEntity player = contextSupplier.get().getSender();
                if (player.level.isClientSide)
                    return;
                IAbilityData abilityData = AbilityDataCapability.get(player);
                Ability abilityInUse = abilityData.getAbilitiesInBar().get(message.slot);
                if (!abilityInUse.getState().equals(Ability.STATE.READY) && !(abilityData instanceof IContinuousAbility && !abilityInUse.getState().equals(Ability.STATE.CONTINUOUS)))
                    return;
                if ((abilityInUse instanceof IEntityRayTrace && !(((IEntityRayTrace) abilityInUse).gotTarget(player))))
                    return;
                AbilityUseEvent.Pre abilityUseEventPre = new AbilityUseEvent.Pre(player, abilityInUse);
                MinecraftForge.EVENT_BUS.post(abilityUseEventPre);
                AbilityUseEvent.Per abilityUseEvent = new AbilityUseEvent.Per(player, abilityInUse);
                MinecraftForge.EVENT_BUS.post(abilityUseEvent);
                AbilityUseEvent.Post abilityUsedEvent;
                if (abilityInUse instanceof IEntityRayTrace)
                    abilityUsedEvent = new AbilityUseEvent.Post(player, abilityInUse, ((IEntityRayTrace) abilityInUse).getLivingEntity(player));
                else
                    abilityUsedEvent = new AbilityUseEvent.Post(player, abilityInUse);
                MinecraftForge.EVENT_BUS.post(abilityUsedEvent);
                abilityInUse.setState(Ability.STATE.COOLDOWN);
                abilityInUse.setCooldown(abilityInUse.getMaxCooldown() / 20);
                PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
            });
        }

        contextSupplier.get().setPacketHandled(true);
    }
}
