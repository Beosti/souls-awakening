package com.yuanno.soulsawakening.networking.client;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IShootAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
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
                if (!abilityInUse.getState().equals(Ability.STATE.READY))
                    return;
                if (abilityInUse instanceof IShootAbility)
                {
                    ((IShootAbility) abilityInUse).onUse(player, abilityInUse);
                    abilityInUse.setState(Ability.STATE.COOLDOWN);
                    abilityInUse.setCooldown(abilityInUse.getMaxCooldown() / 20);
                    PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
                }

            });
        }

        contextSupplier.get().setPacketHandled(true);
    }
}
