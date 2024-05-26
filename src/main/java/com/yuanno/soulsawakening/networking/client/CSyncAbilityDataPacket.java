package com.yuanno.soulsawakening.networking.client;

import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CSyncAbilityDataPacket {

    private INBT data;

    public CSyncAbilityDataPacket() {}

    public CSyncAbilityDataPacket(IAbilityData abilityData)
    {
        this.data = new CompoundNBT();
        this.data = AbilityDataCapability.INSTANCE.getStorage().writeNBT(AbilityDataCapability.INSTANCE, abilityData, null);
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeNbt((CompoundNBT) data);
    }

    public static CSyncAbilityDataPacket decode(PacketBuffer buffer)
    {
        CSyncAbilityDataPacket msg = new CSyncAbilityDataPacket();
        msg.data = buffer.readNbt();
        return msg;
    }

    public static void handle(CSyncAbilityDataPacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            ctx.get().enqueueWork(() ->
            {
                PlayerEntity player = ctx.get().getSender();
                IAbilityData abilityData = AbilityDataCapability.get(player);
                AbilityDataCapability.INSTANCE.getStorage().readNBT(AbilityDataCapability.INSTANCE, abilityData, null, message.data);
            });
        }
        ctx.get().setPacketHandled(true);
    }

}
