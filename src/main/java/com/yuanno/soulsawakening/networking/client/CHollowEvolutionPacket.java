package com.yuanno.soulsawakening.networking.client;

import com.yuanno.soulsawakening.events.hollow.HollowEvolutionEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

// used as a packet to send the right hand empty event to a custom event but server side
public class CHollowEvolutionPacket {

    public CHollowEvolutionPacket()
    {

    }

    public void encode(PacketBuffer packetBuffer)
    {
    }

    public static CHollowEvolutionPacket decode(PacketBuffer packetBuffer)
    {
        CHollowEvolutionPacket msg = new CHollowEvolutionPacket();
        return msg;
    }

    public static void handle(CHollowEvolutionPacket message, final Supplier<NetworkEvent.Context> contextSupplier)
    {
        if (contextSupplier.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            contextSupplier.get().enqueueWork(() ->
            {
                PlayerEntity player = contextSupplier.get().getSender();
                Event hollowEvolutionEvent = new HollowEvolutionEvent(player);
                MinecraftForge.EVENT_BUS.post(hollowEvolutionEvent);
            });
        }
        contextSupplier.get().setPacketHandled(true);
    }
}
