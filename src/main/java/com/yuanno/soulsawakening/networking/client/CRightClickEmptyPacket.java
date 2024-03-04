package com.yuanno.soulsawakening.networking.client;

import com.yuanno.soulsawakening.events.ability.CustomInteractionEvent;
import com.yuanno.soulsawakening.events.ability.RightClickEmptyEvent;
import com.yuanno.soulsawakening.events.zanpakuto.ZanpakutoChangeEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

// used as a packet to send the right hand empty event to a custom event but server side
public class CRightClickEmptyPacket {

    public CRightClickEmptyPacket()
    {

    }



    public void encode(PacketBuffer packetBuffer)
    {
    }

    public static CRightClickEmptyPacket decode(PacketBuffer packetBuffer)
    {
        CRightClickEmptyPacket msg = new CRightClickEmptyPacket();
        return msg;
    }

    public static void handle(CRightClickEmptyPacket message, final Supplier<NetworkEvent.Context> contextSupplier)
    {
        if (contextSupplier.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            contextSupplier.get().enqueueWork(() ->
            {
                PlayerEntity player = contextSupplier.get().getSender();
                Event customInteractionEvent = new CustomInteractionEvent(player);
                MinecraftForge.EVENT_BUS.post(customInteractionEvent);
            });
        }
        contextSupplier.get().setPacketHandled(true);
    }
}
